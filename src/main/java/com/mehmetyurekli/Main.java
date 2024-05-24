package com.mehmetyurekli;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mehmetyurekli.Login.LoginPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(new FlatMacDarkLaf());
        UIManager.put("TextComponent.arc", 15);
        UIManager.put("Component.focusWidth", 0);
        SwingUtilities.invokeLater(() -> new SocialFrame().add(new LoginPage())); //init new login page

    }
}