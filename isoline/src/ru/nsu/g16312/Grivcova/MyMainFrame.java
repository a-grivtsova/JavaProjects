package ru.nsu.g16312.Grivcova;

import ru.nsu.cg.MainFrame;
import ru.nsu.g16312.Grivcova.View.MainPanel;
import ru.nsu.g16312.Grivcova.View.SettingsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class MyMainFrame extends MainFrame {

    private static final int WIDTH = 800, HEIGHT = 600;

    private MainPanel mainPanel = new MainPanel();

    private void addMenuItem(String path, String tooltip, String actionMethod) throws NoSuchMethodException {
        super.addMenuItem(path, tooltip, 0, null, actionMethod);
    }

    private void addToolBarButton(String path, String icon) {
        JButton b = super.createToolBarButton(path);
        b.setIcon(new ImageIcon(getClass().getResource("resources/" + icon)));
        toolBar.add(b);
    }

    private void addToolBarToggledButton(String menuPath, String icon) {
        JMenuItem item = (JMenuItem) getMenuElement(menuPath);
        JToggleButton b = new JToggleButton();
        b.setIcon(new ImageIcon(getClass().getResource("resources/" + icon)));
        for (ActionListener listener : item.getActionListeners())
            b.addActionListener(listener);
        b.setToolTipText(item.getToolTipText());
        toolBar.add(b);
    }

    private void addToolBarButtonWithText(String path, String text) {
        JButton b = super.createToolBarButton(path);
        b.setPreferredSize(new Dimension(24, 24));

        b.setFont(new Font("Times New Roman", Font.PLAIN & Font.CENTER_BASELINE, 22));
        b.setText(text);
        toolBar.add(b);
    }

    private void addSubMenu(String title) {
        super.addSubMenu(title, 0);
    }

    public MyMainFrame() {
        super(WIDTH, HEIGHT, "FTI_16312_Grivcova_Isolines");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);

        try {

            addSubMenu("File");
            addMenuItem("File/Open", "Open file", "onFileOpen");
            addMenuItem("File/Settings", "Settings", "onSettings");
            addSubMenu("Portrait");
            addMenuItem("Portrait/Clear isolines", "Clear isolines", "onClear");
            addMenuItem("Portrait/Grid", "Enable/disable grid", "onGrid");//
            addMenuItem("Portrait/Interpolation", "Enable/disable interpolation", "onInterpolation");//
            addMenuItem("Portrait/Nodes", "Enable/disable isoline nodes", "onNodes");//
            addSubMenu("Help");
            addMenuItem("Help/About", "About", "onAbout");

            addToolBarButton("File/Open", "open.png");
            addToolBarButton("File/Settings", "settings.png");
            addToolBarSeparator();
            addToolBarButton("Portrait/Grid", "grid.png");
            addToolBarButton("Portrait/Interpolation", "area.png");
            addToolBarButton("Portrait/Nodes", "blockchain.png");
            addToolBarButton("Portrait/Clear isolines", "eraser.png");
            addToolBarSeparator();
            addToolBarButton("Help/About", "about.png");

            add(mainPanel);

            pack();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void onFileOpen() {
        File f = getOpenFileName("txt", "Text file");
        try {
            if (f == null) return;
            FileParser.parseFileAndChangeGlobalState(f);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void onSettings() {
        new SettingsDialog(this, mainPanel);
    }

    public void onGrid() {
        mainPanel.changeGridEnabled();
    }

    public void onInterpolation() {
        mainPanel.changeInterpolationState();
    }

    public void onNodes() {
        mainPanel.changeDotsEnabled();
    }

    public void onClear() {
        mainPanel.clearCustomIsolines();
    }

    public void onAbout() {
        JOptionPane.showMessageDialog(this, "Isolines, version 1.0\nCopyright (c) 2019 Grivcova Sasha, FTI, group 16312", "About FTI_16312_Grivcova_Isolines", JOptionPane.INFORMATION_MESSAGE);
    }
}
