package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class FriendPanel extends JPanel {

    private User user;

    public FriendPanel(User user){
        this.user = user;
        init();
    }

    private void init(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0 0 0 0", "[200, left]", "[40, center]"));
        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        JLabel label = new JLabel("  " + user.getUsername());
        label.setFont(new Font("Public Sans", Font.ITALIC, 15));
        label.setForeground(Color.WHITE);
        panel.add(label, "gapbottom 0");
        this.add(panel);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
