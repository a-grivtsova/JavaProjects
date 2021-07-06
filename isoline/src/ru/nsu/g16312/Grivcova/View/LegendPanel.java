package ru.nsu.g16312.Grivcova.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LegendPanel extends JPanel {
    static final int WIDTH = 100, HEIGHT = 450, OFFSET = 10;
    private BufferedImage image = new BufferedImage((WIDTH - 4 * OFFSET) / 2, HEIGHT - 2 * OFFSET, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage labelImage = new BufferedImage((WIDTH - 2 * OFFSET) / 2, HEIGHT - 2 * OFFSET, BufferedImage.TYPE_INT_ARGB);

    public LegendPanel() {
        super();
        setSize(new Dimension(WIDTH + 2, HEIGHT + 2));
        setPreferredSize(new Dimension(WIDTH + 2, HEIGHT + 2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
    }

    public void drawLegend(float[] legendValues, Color[] legendColors) {
        float rectHeight = image.getHeight() / legendColors.length;
        float yOffset = 0;
        Graphics2D g = image.createGraphics();

        Graphics2D labelGraphics = labelImage.createGraphics();
        labelGraphics.setColor(Color.WHITE);
        labelGraphics.fillRect(0, 0, labelImage.getWidth(), labelImage.getHeight());
        labelGraphics.setColor(Color.BLACK);
        labelGraphics.setFont(new Font("default", Font.BOLD, 15));
        int labelFontHeight = labelGraphics.getFontMetrics().getHeight();

        for (int i = 0; i < legendColors.length; i++) {
            g.setColor(legendColors[i]);
            g.fillRect(0, Math.round(yOffset), image.getWidth(), Math.round(rectHeight));
            yOffset += rectHeight;
            if (i != legendValues.length) {
                labelGraphics.drawString(Float.toString(legendValues[i]), 0, Math.round(yOffset + labelFontHeight * 0.5));
            }
        }

        g.dispose();
        repaint();
    }

    public void drawInterpolatedLegend(LegendInterpolation legendInterpolation, float[] legendValues, Color[] legendColors) {
        Graphics2D labelGraphics = labelImage.createGraphics();
        labelGraphics.setColor(Color.WHITE);
        labelGraphics.fillRect(0, 0, labelImage.getWidth(), labelImage.getHeight());
        labelGraphics.setColor(Color.BLACK);
        labelGraphics.setFont(new Font("default", Font.BOLD, 15));
        int labelFontHeight = labelGraphics.getFontMetrics().getHeight();

        float rectHeight = image.getHeight() / legendColors.length;
        float yOffset = 0;
        for (int i = 0; i < legendColors.length - 1; i++) {
            yOffset += rectHeight;
            if (i != legendValues.length) {
                labelGraphics.drawString(Float.toString(legendValues[i]), 0, Math.round(yOffset + labelFontHeight * 0.5));
            }
        }

        labelGraphics.dispose();

        Graphics2D g = image.createGraphics();

        for (int y = 0; y < image.getHeight(); y++) {
            g.setColor(legendInterpolation.getColorForLegend(y));
            g.drawLine(0, y, image.getWidth(), y);
        }

        g.dispose();
        repaint();
    }

    public static int getLegendHeight() {
        return HEIGHT - 2 * OFFSET;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, OFFSET, OFFSET, null);
        g.drawImage(labelImage, image.getWidth() + 2 * OFFSET, OFFSET, null);
    }

}
