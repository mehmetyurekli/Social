package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Builders.UserBuilder;
import com.mehmetyurekli.Models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendsPanel extends JPanel {

    public FriendsPanel(){ //TODO USERS AS PARAMETER
        init();
    }

    private void init(){
        setLayout(new MigLayout("wrap", "[center]", "[][]"));
        JPanel panel = new JPanel(new MigLayout("wrap, insets 5 0 5 0", "[left]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        User user = new UserBuilder().withUsername("USER 1").build();
        User user2 = new UserBuilder().withUsername("USER 2").build();
        User user3 = new UserBuilder().withUsername("USER 3").build();
        User user4 = new UserBuilder().withUsername("USER 4").build();
        User user5 = new UserBuilder().withUsername("USER 5").build();
        User user6 = new UserBuilder().withUsername("USER 6").build();
        User user7 = new UserBuilder().withUsername("USER 7").build();
        User user8 = new UserBuilder().withUsername("USER 8").build();
        User user9 = new UserBuilder().withUsername("USER 9").build();
        User user10 = new UserBuilder().withUsername("USER 10").build();
        User user11 = new UserBuilder().withUsername("USER 11").build();
        User user12 = new UserBuilder().withUsername("USER 12").build();


        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);


        users.forEach((u) -> panel.add(new FriendPanel(u)));

        JLabel scrollLabel = new JLabel();
        scrollLabel.setText("FRIENDS");
        scrollLabel.setFont(new Font("Public Sans", Font.BOLD, 20));
        scrollLabel.setBackground(new Color(69, 69, 69));
        JPanel friendsPanel = new JPanel();
        friendsPanel.setBackground(new Color(69, 69, 69));
        friendsPanel.add(scrollLabel);
        this.add(friendsPanel, "align center, grow");

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(250, 500));
        scrollPane.setForeground(Color.WHITE);

        this.add(scrollPane);
    }
}
