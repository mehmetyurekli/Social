package com.mehmetyurekli.Views;

import javax.swing.*;
import java.awt.*;

public class SocialButton extends JButton {

    public SocialButton(){
        init();
    }

    private void init(){
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setText("Social");
        setFont(new Font("Public Sans", Font.BOLD, 50));
        ImageIcon icon = new ImageIcon("/Users/mehmet/IdeaProjects/Social/src/main/java/com/mehmetyurekli/icons/home.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon.setImage(newImage);
        //setIcon(icon);
        setIconTextGap(15);
    }
}
