package com.mehmetyurekli;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;


public class ProfilePanel extends JPanel {

    private User user;
    private MongoRepository<User> users;
    private ContentListener listener;

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

        JPanel panel = new JPanel(new MigLayout("fillx, wrap", "[][]"));

        JLabel name = new JLabel(user.getName() + " " + user.getSurname());
        name.setFont(new Font("Public Sans", Font.BOLD, 24));

        JLabel username = new JLabel(user.getUsername());

        JTextArea description = new JTextArea(user.getAbout());
        description.setLineWrap(true);
        Dimension d = description.getPreferredSize();
        d.height = 100;
        description.setPreferredSize(d);
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

        if(UserManager.getCurrentUser().getId().equals(user.getId())){
            panel.add(save, "span 2, align center");
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(900, 500));
        this.add(scrollPane);


        save.addActionListener(e -> {
            users.replace("_id", user.getId(), "about", description.getText());
            user = UserManager.getCurrentUser();
        });

        if(addFriend != null){
            addFriend.addActionListener(a -> {
                if(addFriend.getText().equals("INVITATION SENDED")){
                    users.pullFromArray("_id", user.getId(),
                            "invites", UserManager.getCurrentUser().getId());
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("ADD FRIEND");
                        this.revalidate();
                        this.repaint();
                    });
                }
                else if(addFriend.getText().equals("REMOVE FRIEND")){
                    users.pullFromArray("_id", user.getId(),
                            "friends", UserManager.getCurrentUser().getId());
                    users.pullFromArray("_id", UserManager.getCurrentUser().getId(),
                            "friends", user.getId());
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

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }
}
