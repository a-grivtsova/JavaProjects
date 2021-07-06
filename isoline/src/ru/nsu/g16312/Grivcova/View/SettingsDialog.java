package ru.nsu.g16312.Grivcova.View;

import ru.nsu.g16312.Grivcova.GlobalState;
import ru.nsu.g16312.Grivcova.MyBiFunction.FunctionType;
import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SettingsDialog extends JDialog {
    private TextField xMaxTextField, xMinTextField, yMaxTextField, yMinTextField, kTextField, mTextField;
    private int MAX_VALUE = 150, MIN_VALUE = 2;
    private JButton okButton = new JButton();
    private JButton cancelButton = new JButton();
    private MainPanel mainPanel;
    private FunctionType chosenType = GlobalState.getFunction().getFunctionType();

    public SettingsDialog(Component parent, MainPanel mainPanel) {
        setTitle("Settings");
        setPreferredSize(new Dimension(450,340));
        setResizable(false);
        setLayout(new GridLayout(5, 1));

        this.mainPanel = mainPanel;

        xMinTextField = new TextField(Float.toString(GlobalState.getFunction().getxDomain().a));
        xMaxTextField = new TextField(Float.toString(GlobalState.getFunction().getxDomain().b));
        yMinTextField = new TextField(Float.toString(GlobalState.getFunction().getyDomain().a));
        yMaxTextField = new TextField(Float.toString(GlobalState.getFunction().getyDomain().b));
        kTextField = new TextField(Integer.toString(GlobalState.getFunction().getK()));
        mTextField = new TextField(Integer.toString(GlobalState.getFunction().getM()));

        okButton.setText("Ok");
        cancelButton.setText("Cancel");
        okButton.addActionListener(e -> onOk());
        cancelButton.addActionListener(e -> dispose());

        JPanel xDomainPanel = new JPanel();
        xDomainPanel.add(new JLabel("Xmin: "));
        xDomainPanel.add(xMinTextField);
        xDomainPanel.add(new JLabel("Xmax: "));
        xDomainPanel.add(xMaxTextField);
        xDomainPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "X domain"));

        JPanel yDomainPanel = new JPanel();
        yDomainPanel.add(new JLabel("Ymin: "));
        yDomainPanel.add(yMinTextField);
        yDomainPanel.add(new JLabel("Ymax: "));
        yDomainPanel.add(yMaxTextField);
        yDomainPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Y domain"));

        JPanel gridPanel = new JPanel();
        gridPanel.add(new JLabel("k: "));
        gridPanel.add(kTextField);
        gridPanel.add(new JLabel("m: "));
        gridPanel.add(mTextField);
        gridPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Grid sizes (x*y)"));

        JPanel typePanel = new JPanel();
        typePanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Function"));

        JRadioButton hyperbolaRb = new JRadioButton("x*y");
        JRadioButton sphereRb = new JRadioButton("x^2 + y^2 - 1");
        JRadioButton sincosRb = new JRadioButton("sin(x) * cos(y)");

        hyperbolaRb.addActionListener(e -> chosenType = FunctionType.HYPERBOLA);
        sphereRb.addActionListener(e -> chosenType = FunctionType.SPHERE);
        sincosRb.addActionListener(e -> chosenType = FunctionType.SIN_AND_COS);

        ButtonGroup bg = new ButtonGroup();
        bg.add(hyperbolaRb);
        bg.add(sphereRb);
        bg.add(sincosRb);

        if (chosenType == FunctionType.HYPERBOLA) hyperbolaRb.setSelected(true);
        if (chosenType == FunctionType.SPHERE) sphereRb.setSelected(true);
        if (chosenType == FunctionType.SIN_AND_COS) sincosRb.setSelected(true);

        typePanel.add(hyperbolaRb);
        typePanel.add(sphereRb);
        typePanel.add(sincosRb);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(xDomainPanel);
        add(yDomainPanel);
        add(gridPanel);
        add(typePanel);
        add(buttonPanel);

        pack();
        setVisible(true);
        setLocationRelativeTo(parent);
    }

    private void onOk() {
        float xMax, xMin, yMax, yMin;
        int   k, m;
        try {
            xMax = Float.parseFloat(xMaxTextField.getText());
            xMin = Float.parseFloat(xMinTextField.getText());
            yMax = Float.parseFloat(yMaxTextField.getText());
            yMin = Float.parseFloat(yMinTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad float format " + e.getMessage().toLowerCase());
            return;
        }
        try {
            k = Integer.parseInt(kTextField.getText());
            m = Integer.parseInt(mTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad integer format " + e.getMessage().toLowerCase());
            return;
        }
        if (k > MAX_VALUE || k < MIN_VALUE || m > MAX_VALUE || m < MIN_VALUE ) {
            JOptionPane.showMessageDialog(this, "Grid sizes values must be in range ("  + MIN_VALUE + ", " + MAX_VALUE + ")");
            return;
        }

        if (xMax <= xMin || yMax <= yMin) {
            JOptionPane.showMessageDialog(this, "Max values must be greater than min values");
            return;
        }

        GlobalState.setFunction(new MyBiFunction(chosenType, xMin, xMax, yMin, yMax, k, m));
        mainPanel.redrawAll();
        dispose();
    }
}
