package com.mehmetyurekli.Login;

import com.mehmetyurekli.SocialFrame;

import javax.swing.*;

public class LoginManager {
    private static LoginManager instance;
    private SocialFrame socialFrame;

    private LoginManager() {

    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public void initApp(SocialFrame app) {
        this.socialFrame = app;
    }

    public void showPage(JComponent page) {
        SwingUtilities.invokeLater(() -> {
            socialFrame.setContentPane(page);
            socialFrame.revalidate();
            socialFrame.repaint();
        });
    }

}
