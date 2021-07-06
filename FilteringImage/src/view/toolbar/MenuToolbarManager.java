package view.toolbar;

import java.awt.event.*;
import controller.Controller;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MenuToolbarManager {
    private final Controller controller;

    private final Map<String, AbstractButton> buttons = new HashMap<>();
    private final Map<String, JMenuItem> menuItems = new HashMap<>();

    public MenuToolbarManager(Controller controller) {
        this.controller = controller;
    }

    public void setupMenu() {
        try {
            controller.addSubMenu("File", KeyEvent.VK_F);
            menuItems.put("File/New", controller.addMenuItem("File/New", "New", KeyEvent.VK_N, "new.png", controller::onNew));
            menuItems.put("File/Open", controller.addMenuItem("File/Open", "Open", KeyEvent.VK_O, "open.png", controller::onOpen));
            menuItems.put("File/Save", controller.addMenuItem("File/Save", "Save", KeyEvent.VK_S, "save.png", controller::onSave));
            controller.addMenuSeparator("File");

            controller.addSubMenu("Edit", KeyEvent.VK_H);
            menuItems.put("Edit/Select", controller.addMenuItem("Edit/Select", "Select", KeyEvent.VK_S, "select.png", controller::onSelect));
            menuItems.put("Edit/Copy B to C", controller.addMenuItem("Edit/Copy B to C", "Copy B to C", KeyEvent.VK_B, "to_right.png", controller::onCopyToLeft));
            menuItems.put("Edit/Copy C to B", controller.addMenuItem("Edit/Copy C to B", "Copy C to B", KeyEvent.VK_C, "to_left.png", controller::onCopyToRight));

            controller.addSubMenu("Filter", KeyEvent.VK_H);
            menuItems.put("Filter/Grayscale", controller.addMenuItem("Filter/Grayscale", "Grayscale", KeyEvent.VK_I, "grayscale.png", controller::onGrayscale));
            menuItems.put("Filter/Invert", controller.addMenuItem("Filter/Invert", "Invert", KeyEvent.VK_I, "invert.png", controller::onInvert));     
            menuItems.put("Filter/Roberts Edge Detection", controller.addMenuItem("Filter/Roberts Edge Detection", "Roberts Edge Detection", KeyEvent.VK_I, "r_edges.png", controller::onRobertsEdgeDetection));          
            menuItems.put("Filter/Anti-Aliasing", controller.addMenuItem("Filter/Anti-Aliasing", "Anti-Aliasing", KeyEvent.VK_I, "blur.png", controller::onAntiAliasing));
            menuItems.put("Filter/Sharpening", controller.addMenuItem("Filter/Sharpening", "Sharpening", KeyEvent.VK_I, "sharpen.png", controller::onSharpening));
            menuItems.put("Filter/Embossing", controller.addMenuItem("Filter/Embossing", "Embossing", KeyEvent.VK_I, "emboss.png", controller::onEmbossing));
            menuItems.put("Filter/Watercolor", controller.addMenuItem("Filter/Watercolor", "Watercolor", KeyEvent.VK_I, "watercolor.png", controller::onWatercolor));          
            menuItems.put("Filter/Gamma Correction", controller.addMenuItem("Filter/Gamma Correction", "Gamma Correction", KeyEvent.VK_I, "gamma.png", controller::onGammaCorrection));

            controller.addSubMenu("Help", KeyEvent.VK_H);
            menuItems.put("Help/About", controller.addMenuItem("Help/About", "Shows copyright information", KeyEvent.VK_A, "about.png", controller::onAbout));

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setupToolbar() {
        try {
            buttons.put("File/New", controller.addToolBarRegularButton("File/New"));
            buttons.put("File/Open", controller.addToolBarRegularButton("File/Open"));
            buttons.put("File/Save", controller.addToolBarRegularButton("File/Save"));

            controller.addToolBarSeparator();
            buttons.put("Edit/Select", controller.addToolBarToggleButton("Edit/Select"));
            buttons.put("Edit/Copy B to C", controller.addToolBarRegularButton("Edit/Copy B to C"));
            buttons.put("Edit/Copy C to B", controller.addToolBarRegularButton("Edit/Copy C to B"));

            controller.addToolBarSeparator();
            buttons.put("Filter/Grayscale", controller.addToolBarRegularButton("Filter/Grayscale"));
            buttons.put("Filter/Invert", controller.addToolBarRegularButton("Filter/Invert"));
            buttons.put("Filter/Watercolor", controller.addToolBarRegularButton("Filter/Watercolor"));
            buttons.put("Filter/Gamma Correction", controller.addToolBarRegularButton("Filter/Gamma Correction"));
            controller.addToolBarSeparator();

            buttons.put("Filter/Roberts Edge Detection", controller.addToolBarRegularButton("Filter/Roberts Edge Detection"));

            controller.addToolBarSeparator();
            buttons.put("Filter/Anti-Aliasing", controller.addToolBarRegularButton("Filter/Anti-Aliasing"));
            buttons.put("Filter/Sharpening", controller.addToolBarRegularButton("Filter/Sharpening"));
            buttons.put("Filter/Embossing", controller.addToolBarRegularButton("Filter/Embossing"));

            controller.addToolBarSeparator();
            buttons.put("Help/About", controller.addToolBarRegularButton("Help/About"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setEnabled(String key, boolean value) {
        buttons.get(key).setEnabled(value); //разблокировать или заблокировать пункт меню
        menuItems.get(key).setEnabled(value);
    }

    private void setSelected(String key, boolean value) {
        buttons.get(key).setSelected(value); //yстанавливает выбранное состояние кнопки
        menuItems.get(key).setSelected(value);
    }

    public void setFiltersEnabled(boolean value) {
        setEnabled("Filter/Grayscale", value);
        setEnabled("Filter/Invert", value);
        setEnabled("Filter/Watercolor", value);
        setEnabled("Filter/Gamma Correction", value);
        setEnabled("Filter/Roberts Edge Detection", value);
        setEnabled("Filter/Anti-Aliasing", value);
        setEnabled("Filter/Sharpening", value);
        setEnabled("Filter/Embossing", value);
    }

    public void setSelectEnabled(boolean value) {
        setEnabled("Edit/Select", value);
    }

    public void setCopyToRightEnabled(boolean value) {
        setEnabled("Edit/Copy B to C", value);
    }

    public void setCopyToLeftEnabled(boolean value) {
        setEnabled("Edit/Copy C to B", value);
    }

    public void setStatusLabelListeners(JLabel statusLabel){
        buttons.values().forEach(b -> b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                statusLabel.setText(b.getToolTipText());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statusLabel.setText("");
            }
        }));

    }

    public void setSelectSelected(boolean value) {
        setSelected("Edit/Select", value);
    }


    public void setSaveEnabled(boolean value) {
        setEnabled("File/Save", value);
    }

    public void clear() {
        setSelectEnabled(false);
        setSelectSelected(false);
        setCopyToLeftEnabled(false);
        setCopyToRightEnabled(false);
        setFiltersEnabled(false);
    }
}