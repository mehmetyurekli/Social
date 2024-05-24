package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class QueryResultPanel extends JPanel {

    /* lists all matching results from search engine in many QueryPanels */

    private User user;

    public QueryResultPanel(User u) {
        this.user = u;
        init();
    }

    private void init() {
        assert user != null;
        JPanel panel = new JPanel(new MigLayout("fill, insets 0 0 0 0", "[815, left]", "[60, center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        JLabel label = new JLabel("  " + user.getUsername() + " - " + user.getName() + " " + user.getSurname());
        label.setFont(new Font("Public Sans", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        panel.add(label);
        this.add(panel);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
