package ru.nsu.g16312.Grivcova;

import ru.nsu.g16312.Grivcova.MyBiFunction.FunctionDomain;
import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;
import ru.nsu.g16312.Grivcova.View.LegendInterpolation;
import ru.nsu.g16312.Grivcova.View.LegendPanel;
import ru.nsu.g16312.Grivcova.View.PortraitPanel;

import java.awt.*;

public class GlobalState {
    private static MyBiFunction function = new MyBiFunction();
    private static float[] legendValues = new float[]{-0.8f, -0.4f, 0f, 0.4f, 0.8f};
    private static Color[] legendColors = new Color[]{
            new Color(0, 0, 50),
            new Color(0, 20, 150),
            new Color(0, 100, 200),
            new Color(0, 200, 100),
            new Color(0, 250, 100),
            new Color(0, 250, 250)};
    private static LegendInterpolation legendInterpolation = new LegendInterpolation(legendValues, legendColors, LegendPanel.getLegendHeight());

    public static void setFunction(MyBiFunction f) {
        function = f;
    }

    public static void setLegend(float[] legendValues, Color[] legendColors) {
        GlobalState.legendValues = legendValues;
        GlobalState.legendColors = legendColors;
        legendInterpolation = new LegendInterpolation(legendValues, legendColors, LegendPanel.getLegendHeight());
    }

    private static FunctionDomain getZFunctionDomain(int imageWidth, int imageHeight) {
        float min = Float.POSITIVE_INFINITY, max = Float.NEGATIVE_INFINITY;
        for (int u = 0; u < imageWidth; ++u) {
            for (int v = 0; v < imageHeight; ++v) {
                float x = (function.getxDomain().b - function.getxDomain().a) * u / imageWidth + function.getxDomain().a;
                float y = (function.getyDomain().b - function.getyDomain().a) * v / imageHeight + function.getyDomain().a;
                float z = function.getOriginalFunction().apply(x, y);
                if (z > max) max = z;
                if (z < min) min = z;
            }
        }
        return new FunctionDomain(min, max);
    }

    public static void setLegend(Color[] legendColors) {
        GlobalState.legendColors = legendColors;
        legendValues = new float[legendColors.length - 1];

        int imageWidth = PortraitPanel.getImageWidth();
        int imageHeight = PortraitPanel.getImageHeight();

        FunctionDomain zDomain = getZFunctionDomain(imageWidth, imageHeight);

        float dz = (zDomain.b - zDomain.a) / legendColors.length;
        float offset = zDomain.a + dz;

        for (int i = 0; i < legendValues.length; i++) {
            legendValues[i] = offset;
            offset += dz;
        }

        legendInterpolation = new LegendInterpolation(legendValues, legendColors, LegendPanel.getLegendHeight());
    }

    public static Color[] getLegendColors() {
        return legendColors;
    }

    public static float[] getLegendValues() {
        return legendValues;
    }

    public static LegendInterpolation getLegendInterpolation() {
        return legendInterpolation;
    }

    public static MyBiFunction getFunction() {
        return function;
    }

}
