package ru.nsu.g16312.Grivcova.View;

import java.awt.*;
import java.util.function.Function;

public class LegendInterpolation {
    float[] knownValues;
    Color[] knownColors;
    float h;
    int legendHeight;

    public LegendInterpolation(float[] legendValues, Color[] legendColors, int legendHeight) {
        h = (legendValues[1] - legendValues[0]);
        float offset = legendValues[0] + h / 2;
        knownColors = legendColors;

        knownValues = new float[knownColors.length];

        for (int i = 0; i < knownValues.length; i++) {
            knownValues[i] = offset;
            offset += h;
        }
        this.legendHeight = legendHeight;
    }

	private int interpolateComponent(float z, float z1, float z2, Color c1, Color c2, Function<Color, Integer> getComponent) {
        int comp1 = getComponent.apply(c1), comp2 = getComponent.apply(c2);
        int result = Math.round((comp1 * (z2 - z) + comp2 * (z - z1)) / (z2 - z1));
        if (result < 0) result = 0;
        if (result > 255) result = 255;
        return result;
    }

    public Color getColorForLegend(int y) {
        float z = ((knownValues[knownValues.length - 1] - knownValues[0]) * y / legendHeight + knownValues[0]);
        return getFunctionColor(z);
    }

    public Color getFunctionColor(float z) 
    {
        if (z < knownValues[0]) return knownColors[0];
        for (int i = 1; i < knownValues.length; i++) {
            if (z >= knownValues[i - 1] && z < knownValues[i]) {
                int r = interpolateComponent(z, knownValues[i - 1], knownValues[i], knownColors[i - 1], knownColors[i], c -> c.getRed());
                int g = interpolateComponent(z, knownValues[i - 1], knownValues[i], knownColors[i - 1], knownColors[i], c -> c.getGreen());
                int b = interpolateComponent(z, knownValues[i - 1], knownValues[i], knownColors[i - 1], knownColors[i], c -> c.getBlue());
                return new Color(r, g, b);
            }
        }
        return knownColors[knownColors.length - 1];
    }
}
