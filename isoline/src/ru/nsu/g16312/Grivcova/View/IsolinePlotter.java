package ru.nsu.g16312.Grivcova.View;

import java.awt.*;
import java.awt.image.BufferedImage;

import ru.nsu.g16312.Grivcova.MyBiFunction.GridNode;
import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;

public class IsolinePlotter {
    BufferedImage image;
    BufferedImage dotsImage;
    MyBiFunction function;

    IsolinePlotter(MyBiFunction function, BufferedImage imageToDraw, BufferedImage imageToDrawDots) 
    {
        this.function = function;
        image = imageToDraw;
        dotsImage = imageToDrawDots;
    }

    private int getCellNumber(float z, GridNode n1, GridNode n2, GridNode n3, GridNode n4) {
        int result = 0;
        if (n2.z < z) result |= (0x01 << 3); //result = result | 1000
        if (n3.z < z) result |= (0x01 << 2); //result = result | 0100
        if (n4.z < z) result |= (0x01 << 1); //result = result | 0010
        if (n1.z < z) result |= (0x01);      //result = result | 0001
        return result;
    }

    private float functionForImageCoords(float u, float v) {
        float x = (function.getxDomain().b - function.getxDomain().a) * u / image.getWidth() + function.getxDomain().a;
        float y = (function.getyDomain().b - function.getyDomain().a) * v / image.getHeight() + function.getyDomain().a;
        return function.myapply(x, y);
    }

    private int xToImage(float x) {
        return Math.round((image.getWidth() * (x - function.getxDomain().a) /
                (function.getxDomain().b - function.getxDomain().a)));
    }

    private int yToImage(float y) {
        return image.getHeight() - Math.round((image.getHeight() * (y - function.getyDomain().a) /
                (function.getyDomain().b - function.getyDomain().a))) - 1;
    }

    float getDivRatio(float z, float z1, float z2) { //z1 < z < z2
        float ratio = (z1 < z2) ? (z - z1) / (z2 - z1) : (z - z2) / (z1 - z2);
        return ratio;
    }

    float getX(float z, GridNode node1, GridNode node2) {
        float ratio = getDivRatio(z, node1.z, node2.z);
        float dx = Math.abs(node1.x - node2.x);
        if (node1.z < node2.z) return node1.x + ratio * dx;
        return node2.x - ratio * dx;
    }

    float getY(float z, GridNode node1, GridNode node2) {
        float ratio = getDivRatio(z, node1.z, node2.z);
        float dy = Math.abs(node1.y - node2.y);
        if (node1.z < node2.z) return node1.y + ratio * dy;
        return node2.y - ratio * dy;
    }

    private void drawIsolineInCell(Graphics2D g, Graphics2D dotsG, float z, GridNode node1, GridNode node2, GridNode node3, GridNode node4) {
        
        int n = getCellNumber(z, node1, node2, node3, node4);
        if (n == 0 || n == 15) return;

        if (n != 5 && n != 10) {
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            if (n == 1 || n == 14) {
                x1 = xToImage(node1.x);
                y1 = yToImage(getY(z, node1, node2));

                x2 = xToImage(getX(z, node1, node4));
                y2 = yToImage(node1.y);
            }

            if (n == 2 || n == 13) {
                x1 = xToImage(node4.x);
                y1 = yToImage(getY(z, node4, node3));

                x2 = xToImage(getX(z, node1, node4));
                y2 = yToImage(node4.y);
            }

            if (n == 3 || n == 12) {
                x1 = xToImage(node1.x);
                x2 = xToImage(node4.x);

                y1 = yToImage(getY(z, node1, node2));
                y2 = yToImage(getY(z, node4, node3));
            }

            if (n == 4 || n == 11) {
                x1 = xToImage(node4.x);
                y1 = yToImage(getY(z, node4, node3));

                x2 = xToImage(getX(z, node2, node3));
                y2 = yToImage(node3.y);
            }

            if (n == 6 || n == 9) {
                y1 = yToImage(node1.y);
                y2 = yToImage(node2.y);

                x1 = xToImage(getX(z, node1, node4));
                x2 = xToImage(getX(z, node2, node3));
            }

            if (n == 7 || n == 8) {
                x1 = xToImage(node1.x);
                y1 = yToImage(getY(z, node1, node2));

                x2 = xToImage(getX(z, node2, node3));
                y2 = yToImage(node2.y);
            }
            g.drawLine(x1, y1, x2, y2);
            dotsG.setColor(Color.BLACK);
            dotsG.fillOval(x1 - 2, y1 - 2, 4, 4);
            dotsG.fillOval(x2 - 2, y2 - 2, 4, 4);
            dotsG.setColor(Color.WHITE);
            dotsG.drawOval(x1 - 2, y1 - 2, 4, 4);
            dotsG.drawOval(x2 - 2, y2 - 2, 4, 4);
        } else {
            int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;

            float centerX = (node4.x - node1.x) / 2 + node1.x;
            float centerY = (node2.y - node1.y) / 2 + node1.y;
            float centerZ = function.myapply(centerX, centerY);

            if ((n == 5 && centerZ < z) || (n == 10 && centerZ > z)) {
                x1 = xToImage(node1.x);
                y1 = yToImage(getY(z, node1, node2));

                x2 = xToImage(getX(z, node1, node4));
                y2 = yToImage(node1.y);

                x3 = xToImage(getX(z, node2, node3));
                y3 = yToImage(node2.y);

                x4 = xToImage(node4.x);
                y4 = yToImage(getY(z, node4, node3));
            } else {
                x1 = xToImage(node1.x);
                y1 = yToImage(getY(z, node1, node2));

                x2 = xToImage(getX(z, node2, node3));
                y2 = yToImage(node2.y);

                x3 = xToImage(getX(z, node1, node4));
                y3 = yToImage(node1.y);

                x4 = xToImage(node4.x);
                y4 = yToImage(getY(z, node4, node3));
            }
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x3, y3, x4, y4);
            dotsG.setColor(Color.BLACK);
            dotsG.fillOval(x1 - 2, y1 - 2, 4, 4);
            dotsG.fillOval(x2 - 2, y2 - 2, 4, 4);
            dotsG.fillOval(x3 - 2, y3 - 2, 4, 4);
            dotsG.fillOval(x4 - 2, y4 - 2, 4, 4);
            dotsG.setColor(Color.WHITE);
            dotsG.drawOval(x1 - 2 , y1 - 2, 4, 4);
            dotsG.drawOval(x2 - 2 , y2 - 2, 4, 4);
            dotsG.drawOval(x3 - 2 , y3 - 2, 4, 4);
            dotsG.drawOval(x4 - 2 , y4 - 2, 4, 4);
        }
    }


    void drawIsoline(float z) {
        int m = function.getM(), k = function.getK();
        GridNode[][] grid = function.getGrid();

        Graphics2D g = image.createGraphics();
        Graphics2D dotsG = dotsImage.createGraphics();
        g.setColor(Color.WHITE);

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < k - 1; j++) {
                int i2 = i + 1;
                int j2 = j + 1;

                GridNode node1 = grid[i][j];
                GridNode node2 = grid[i2][j];
                GridNode node3 = grid[i2][j2];
                GridNode node4 = grid[i][j2];

                drawIsolineInCell(g, dotsG, z, node1, node2, node3, node4);
            }
        }

        g.dispose();
        dotsG.dispose();

    }
}
