package com.mehmetyurekli.Login;

import com.mehmetyurekli.SocialFrame;

import javax.swing.*;

public class LoginManager {
    private SocialFrame socialFrame;
    private static LoginManager instance;

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
