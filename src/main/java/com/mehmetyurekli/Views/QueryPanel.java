package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class QueryPanel extends JPanel {

    private User[] users;
    private JScrollPane scrollPane;
    private boolean init;

    public QueryPanel(User[] users){
        if(users != null){
            this.users = users;
        }
        init();
    }
    public QueryPanel(){
        init = true;
        init();
    }

    private void init(){
        setLayout(new MigLayout("wrap", "[center]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JPanel panel = new JPanel(new MigLayout("wrap", "[left]", "[center]"));

        if(users == null){
            if(!init){
                JOptionPane.showMessageDialog(this, "No matches are found!");
            }
            init = false;
        }
        else{
            for(User u : users){
                panel.add(new UserPanel(u));
            }
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(900, 500));
        scrollPane.setForeground(Color.WHITE);

        this.add(scrollPane);
    }

}
