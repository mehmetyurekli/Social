package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.Icons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchBar extends JPanel {

    private JTextField field;
    private ContentListener listener;

    public SearchBar() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 40");

        JPanel panel = new JPanel(new MigLayout("fillx, insets 0 0 0 0", "[540, left][right]", "[center]"));

        field = new JTextField("Search");
        field.setFont(new Font("Public Sans", Font.ITALIC, 30));
        field.setForeground(Color.GRAY);
        field.setBackground(null);
        field.setBorder(null);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals("Search")) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText("Search");
                }
            }
        });

        JButton searchBtn = new JButton();
        ImageIcon icon = new ImageIcon(Icons.SEARCH.getPath());
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        icon.setImage(newImage);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        searchBtn.setIcon(icon);

        panel.add(field, "grow");
        panel.add(searchBtn);

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SwingUtilities.invokeLater(() -> {
                        listener.onContentChange(ContentChange.QUERY_ENTER);
                    });
                }
            }
        });

        searchBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                listener.onContentChange(ContentChange.QUERY_ENTER);
            });
        });

        this.add(panel);
    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public String getText() {
        return field.getText();
    }
}
