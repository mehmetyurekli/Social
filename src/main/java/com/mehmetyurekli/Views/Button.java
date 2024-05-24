package com.mehmetyurekli.Views;

import com.mehmetyurekli.Util.Icons;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton { //top bar buttons (image icons)

    ImageIcon icon;


    public Button(Icons icon) {
        this.icon = new ImageIcon(icon.getPath());
        Image image = this.icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.icon.setImage(newImage);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setIcon(icon);
        setForeground(Color.WHITE);
    }

}
