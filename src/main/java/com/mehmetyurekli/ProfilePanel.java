package com.mehmetyurekli;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        description.setEditable(false);
        description.getCaret().deinstall(description);
        description.setBackground(new Color(69, 69, 69));

        JButton addFriend;
        if(UserManager.getCurrentUser().getUsername().equals(user.getUsername())){
            addFriend = null;
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

        this.add(panel);


        if(addFriend != null){
            addFriend.addActionListener(a -> {
                if(addFriend.getText().equals("INVITATION SENDED")){
                    ArrayList<ObjectId> oldList = user.getInvites();
                    oldList.remove(UserManager.getCurrentUser().getId());
                    user.setInvites(oldList);
                    users.replace("username", user.getUsername(), "invites", oldList);
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("ADD FRIEND");
                        this.revalidate();
                        this.repaint();
                    });
                }
                else{
                    ArrayList<ObjectId> oldList = user.getInvites();
                    oldList.add(UserManager.getCurrentUser().getId());
                    user.setInvites(oldList);
                    users.replace("username", user.getUsername(), "invites", oldList);
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
