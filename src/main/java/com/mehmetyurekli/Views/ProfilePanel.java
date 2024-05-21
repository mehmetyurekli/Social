package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.Post;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


public class ProfilePanel extends JPanel {

    private User user;
    private MongoRepository<User> userRepository;
    private MongoRepository<Post> postRepository;
    private ArrayList<Post> posts;

    public ProfilePanel() {

    }

    public ProfilePanel(User user) {
        this.user = user;
        posts = new ArrayList<>();
        init();
    }

    private void init() {
        userRepository = new MongoRepository<>("Social", "Users", User.class);
        postRepository = new MongoRepository<>("Social", "Posts", Post.class);

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
        if (UserManager.getCurrentUser().getUsername().equals(user.getUsername())) {
            addFriend = null;
            description.setEditable(true);
            description.getCaret().install(description);
            panel.add(name, "span 2, wrap, align left");
            panel.add(username, "align left, gapbottom 5, wrap");

        } else {
            if (UserManager.getCurrentUser().isFriend(user.getId())) {
                addFriend = new JButton("REMOVE FRIEND");
            } else {
                if (user.getInvites().contains(UserManager.getCurrentUser().getId())) {
                    addFriend = new JButton("INVITATION SENDED");
                } else {
                    addFriend = new JButton("ADD FRIEND");
                }
            }
            panel.add(name, "span 2, wrap, align left");
            panel.add(username, "align left, gapbottom 5, wrap");
        }

        if (addFriend != null) {
            panel.add(addFriend, "align right, gapbottom 5, wrap");
        }

        panel.add(new JLabel("About"), "wrap");
        panel.add(description, "grow, wrap");

        if (UserManager.getCurrentUser().getId().equals(user.getId())) {
            panel.add(save, "span 2, align center");
        }

        JLabel postsTitle = new JLabel("User's posts");
        postsTitle.setFont(new Font("Public Sans", Font.BOLD, 24));
        panel.add(postsTitle, "gaptop 10, wrap");

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(900, 500));

        Thread queryThread = new Thread(() -> {
            posts.addAll(postRepository.getAll("owner", user.getId()));
            posts.sort(new Comparator<Post>() {
                @Override
                public int compare(Post o1, Post o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });

            for (Post p : posts) {
                panel.add(new PostView(p), "wrap");
            }

            SwingUtilities.invokeLater(() -> {
                scrollPane.revalidate();
                scrollPane.repaint();
            });
        });
        queryThread.start();

        this.add(scrollPane);

        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(0);
        });


        save.addActionListener(e -> {
            userRepository.replace("_id", user.getId(), "about", description.getText());
            JOptionPane.showMessageDialog(this, "Changes are saved.");
        });

        if (addFriend != null) {
            addFriend.addActionListener(a -> {
                if (addFriend.getText().equals("INVITATION SENDED")) {
                    userRepository.pullFromArray("_id", user.getId(), "invites", UserManager.getCurrentUser().getId());
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("ADD FRIEND");
                        this.revalidate();
                        this.repaint();
                    });
                } else if (addFriend.getText().equals("REMOVE FRIEND")) {
                    userRepository.pullFromArray("_id", user.getId(), "friends", UserManager.getCurrentUser().getId());
                    userRepository.pullFromArray("_id", UserManager.getCurrentUser().getId(), "friends", user.getId());
                    SwingUtilities.invokeLater(() -> {
                        addFriend.setText("ADD FRIEND");
                        this.revalidate();
                        this.repaint();
                    });
                } else {
                    userRepository.pushToArray("username", user.getUsername(), "invites", UserManager.getCurrentUser().getId());
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
