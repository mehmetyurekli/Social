package com.mehmetyurekli;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfilePanel extends JPanel {

    private User user;
    private MongoRepository<User> users;

    public ProfilePanel(){

    }

    public ProfilePanel(User user){
        this.user = user;
        init();
    }

    private void init(){
        users = new MongoRepository<>("Social", "Users", User.class);

        setLayout(new MigLayout("wrap", "[center]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        JPanel panel = new JPanel(new MigLayout("fillx, wrap", "[fill, 450][fill, 450]"));

        JLabel name = new JLabel(user.getName() + " " + user.getSurname());
        name.setFont(new Font("Public Sans", Font.BOLD, 24));

        JLabel username = new JLabel(user.getUsername());

        JTextArea description = new JTextArea(user.getAbout());
        description.setLineWrap(true);
        description.setPreferredSize(new Dimension(900, 100));
        description.setBackground(new Color(69, 69, 69));
        description.setEditable(false);
        description.getCaret().deinstall(description);

        JButton addFriend;
        JButton save = new JButton("Save Changes");
        if(UserManager.getCurrentUser().getUsername().equals(user.getUsername())){
            addFriend = null;
            description.setEditable(true);
            description.getCaret().install(description);
            panel.add(name, "span 2, wrap, align left");
            panel.add(username, "align left, gapbottom 5, wrap");

        }
        else{
            if(UserManager.getCurrentUser().isFriend(user.getId())){
                addFriend = new JButton("REMOVE FRIEND");
            }
            else{
                if(user.getInvites().contains(UserManager.getCurrentUser().getId())){
                    addFriend = new JButton("INVITATION SENDED");
                }
                else{
                    addFriend = new JButton("ADD FRIEND");
                }
            }
            panel.add(name, "span 2, wrap, align left");
            panel.add(username, "align left, gapbottom 5");
        }

        if(addFriend != null){
            panel.add(addFriend, "align right, gapbottom 5, wrap");
        }

        panel.add(new JLabel("About"), "wrap");
        panel.add(description, "grow, span 2, wrap");

        if(UserManager.getCurrentUser().getUsername().equals(user.getUsername())){
            panel.add(save, "span 2, align center");
        }

        this.add(panel);


        save.addActionListener(e -> {
            users.replace("username", user.getUsername(), "about", description.getText());
        });

        if(addFriend != null){
            addFriend.addActionListener(a -> {
                if(addFriend.getText().equals("INVITATION SENDED")){
                    users.pullFromArray("username", user.getUsername(),
                            "invites", UserManager.getCurrentUser().getId());
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("ADD FRIEND");
                        this.revalidate();
                        this.repaint();
                    });
                }
                else{
                    users.pushToArray("username", user.getUsername(),
                            "invites", UserManager.getCurrentUser().getId());
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("INVITATION SENDED");
                        this.revalidate();
                        this.repaint();
                    });
                }
            });
        }

    }

}
