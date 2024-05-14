package com.mehmetyurekli.Views;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollPaneTest extends JPanel {

    private JPanel contentPanel;
    private JScrollPane scrollPane;

    public ScrollPaneTest() {
        init();
    }

    private void init() {
        JPanel mainline = new JPanel(new MigLayout("debug"));

        mainline.add(new JLabel("test 1"), "dock south");
        mainline.add(new JLabel("test 2"), "dock south");
        mainline.add(new JLabel("test 3"), "dock south");
        mainline.add(new JLabel("test 4"), "dock south");

        this.add(mainline);
    }
}
