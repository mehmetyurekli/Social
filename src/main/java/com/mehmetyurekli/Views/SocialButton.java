package com.mehmetyurekli.Views;

import javax.swing.*;
import java.awt.*;

public class SocialButton extends JButton {

    // It's the text button on TopBar

    public SocialButton() {
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setText("Social");
        setFont(new Font("Public Sans", Font.BOLD, 50));
    }
}
