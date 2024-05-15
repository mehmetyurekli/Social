package com.mehmetyurekli;

import com.mehmetyurekli.Login.LoginManager;

import javax.swing.*;

public class SocialFrame extends JFrame {

    public SocialFrame() {
        init();
    }

    private void init() {
        setTitle("Social App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        LoginManager.getInstance().initApp(this);
    }
}
