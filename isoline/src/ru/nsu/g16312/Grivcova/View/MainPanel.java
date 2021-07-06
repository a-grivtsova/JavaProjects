package ru.nsu.g16312.Grivcova.View;

import ru.nsu.g16312.Grivcova.GlobalState;
import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    static final int SPACING = 20;
    LegendPanel legendPanel = new LegendPanel();
    PortraitPanel portraitPanel = new PortraitPanel();

    public MainPanel() {
        setPreferredSize(new Dimension(PortraitPanel.WIDTH + LegendPanel.WIDTH + SPACING * 3,
                PortraitPanel.HEIGHT + 2 * SPACING));
        setLayout(null);

        add(portraitPanel);
        portraitPanel.setLocation(SPACING, SPACING);

        add(legendPanel);
        legendPanel.setLocation(PortraitPanel.WIDTH + 2 * SPACING, SPACING);

        redrawAll();

        setVisible(true);
    }

    public void redrawAll() {
        GlobalState.setLegend(GlobalState.getLegendColors());
        portraitPanel.setFunction(GlobalState.getFunction(), GlobalState.getLegendValues(),
                GlobalState.getLegendColors(), GlobalState.getLegendInterpolation());
        legendPanel.drawLegend(GlobalState.getLegendValues(), GlobalState.getLegendColors());
    }

    public void clearCustomIsolines() {
        portraitPanel.clearCustomIsolines(GlobalState.getLegendValues());
    }

    public void changeInterpolationState() {
        portraitPanel.setColorsInterpolated(!portraitPanel.areColorsInterpolated());
        if (portraitPanel.areColorsInterpolated())
            legendPanel.drawInterpolatedLegend(GlobalState.getLegendInterpolation(), GlobalState.getLegendValues(), GlobalState.getLegendColors());
        else legendPanel.drawLegend(GlobalState.getLegendValues(), GlobalState.getLegendColors());
    }

    public void changeGridEnabled() {
        portraitPanel.setNeedToDrawGrid(!portraitPanel.isNeedToDrawGrid());
    }

    public void changeDotsEnabled() {
        portraitPanel.setNeedToDrawDots(!portraitPanel.isNeedToDrawDots());
    }

}
