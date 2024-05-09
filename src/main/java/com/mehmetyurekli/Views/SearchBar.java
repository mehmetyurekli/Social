package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchBar extends JTextField {

    public SearchBar(){
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(300, 40));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        setText(" Search");
        setFont(new Font("Public Sans", Font.ITALIC, 30));
        setForeground(Color.GRAY);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(" Search")){
                    setText("");
                    setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()){
                    setForeground(Color.GRAY);
                    setText(" Search");
                }

            }
        });
    }

}
