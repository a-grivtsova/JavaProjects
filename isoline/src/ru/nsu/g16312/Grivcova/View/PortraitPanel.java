package ru.nsu.g16312.Grivcova.View;

import javax.swing.*;

import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public class PortraitPanel extends JPanel {
    static final int WIDTH = 600, HEIGHT = 450;

    private boolean needToDrawGrid = false, areColorsInterpolated = false, needToDrawDots = false;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage colorInterpolatedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage isolinesImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage dotsImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage gridImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

    private MyBiFunction function;
    private IsolinePlotter plotter;

    public PortraitPanel() {
        super();
        setSize(new Dimension(WIDTH + 2, HEIGHT + 2));
        setPreferredSize(new Dimension(WIDTH + 2, HEIGHT + 2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawIsoline(functionForImageCoords(e.getX(), image.getHeight() - e.getY() - 1));
            }
        });
    }

    public void setFunction(MyBiFunction f, float[] legendValues, Color[] legendColors, LegendInterpolation legendInterpolation) {
        function = f;
        clearIsolines();
        plotter = new IsolinePlotter(function, isolinesImage, dotsImage);
        drawFunction(legendValues, legendColors);
        drawInterpolatedPortrait(legendInterpolation);
        drawBasicIsolines(legendValues);
        drawGrid();
        repaint();
    }

    private void clearIsolines() {
        Graphics2D g = isolinesImage.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, isolinesImage.getWidth(), isolinesImage.getHeight());
        g.dispose();

        g = dotsImage.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, dotsImage.getWidth(), dotsImage.getHeight());
        g.dispose();
    }

    private void drawGrid() {
        gridImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = gridImage.createGraphics();
        g.setColor(Color.BLACK);

        float dx = (float) gridImage.getWidth() / (function.getK() - 1);
        float dy = (float) gridImage.getHeight() / (function.getM() - 1);

        float xOffset = dx;
        float yOffset = dy;

        if (dx > 10 && dy > 10) {
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{4, 2}, 3);
            g.setStroke(dashed);
        }

        int x = Math.round(xOffset), y = Math.round(yOffset);
        for (int i = 0; i < function.getK() - 1; i++) {
            g.drawLine(x, 0, x, gridImage.getHeight() - 1);
            xOffset += dx;
            x = Math.round(xOffset);
        }

        for (int i = 0; i < function.getM() - 1; i++) {
            g.drawLine(0, y, gridImage.getWidth() - 1, y);
            yOffset += dy;
            y = Math.round(yOffset);
        }

        g.dispose();
    }

    private void drawInterpolatedPortrait(LegendInterpolation legendInterpolation) {
        int width = colorInterpolatedImage.getWidth();
        int height = colorInterpolatedImage.getHeight();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                colorInterpolatedImage.setRGB(x, height - y - 1, legendInterpolation.getFunctionColor(functionForImageCoords(x, y)).getRGB());
            }
        }
    }

    private float functionForImageCoords(int u, int v) {
        float x = (function.getxDomain().b - function.getxDomain().a) * u / image.getWidth() + function.getxDomain().a;
        float y = (function.getyDomain().b - function.getyDomain().a) * v / image.getHeight() + function.getyDomain().a;
        return function.getOriginalFunction().apply(x, y);
    }

    public void drawBasicIsolines(float[] legendValues) {
        for (float legendValue : legendValues) {
            drawIsoline(legendValue);
        }
    }

    private void drawIsoline(float z) {
        plotter.drawIsoline(z);
        repaint();
    }

    private void drawFunction(float[] legendValues, Color[] legendColors) {
        int width = image.getWidth();
        int height = image.getHeight();

        Function<Float, Color> mapColor = (z) -> {
            if (z < legendValues[0]) return legendColors[0];
            for (int i = 1; i < legendValues.length; i++) {
                if (z >= legendValues[i - 1] && z < legendValues[i]) return legendColors[i];
            }
            return legendColors[legendColors.length - 1];
        };

        for (int u = 0; u < width; ++u) {
            for (int v = 0; v < height; ++v) {
                image.setRGB(u, height - v - 1, mapColor.apply(functionForImageCoords(u, v)).getRGB());
            }
        }
    }

    public boolean areColorsInterpolated() {
        return areColorsInterpolated;
    }

    public boolean isNeedToDrawDots() {
        return needToDrawDots;
    }

    public boolean isNeedToDrawGrid() {
        return needToDrawGrid;
    }

    public void setNeedToDrawGrid(boolean flag) {
        needToDrawGrid = flag;
        repaint();
    }

    public void setNeedToDrawDots(boolean flag) {
        needToDrawDots = flag;
        repaint();
    }

    public void setColorsInterpolated(boolean flag) {
        areColorsInterpolated = flag;
        repaint();
    }

    public void clearCustomIsolines(float[] legendValues) {
        clearIsolines();
        drawBasicIsolines(legendValues);
    }

    public static int getImageWidth() {
        return WIDTH;
    }

    public static int getImageHeight() {
        return HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (areColorsInterpolated) {
            g.drawImage(colorInterpolatedImage, 1, 1, null);
        } else {
            g.drawImage(image, 1, 1, null);
        }

        if (needToDrawGrid) {
            g.setXORMode(Color.WHITE);
            g.drawImage(gridImage, 1, 0, null);
            g.setPaintMode();
        }
        
        g.drawImage(isolinesImage, 1, 1, null);

        if (needToDrawDots) {
            g.drawImage(dotsImage, 1, 1, null);
        }
    }
}
