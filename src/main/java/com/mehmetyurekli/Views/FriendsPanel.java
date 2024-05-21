package com.mehmetyurekli.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FriendsPanel extends JPanel {

    private final User user;
    private ContentListener listener;
    private ObjectId clickedUser;

    public FriendsPanel() {
        this.user = UserManager.getCurrentUser();
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap", "[center]", "[][]"));
        JPanel panel = new JPanel(new MigLayout("wrap, insets 5 5 5 5", "[left]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        MongoRepository<User> users = new MongoRepository<>("Social", "Users", User.class);

        ArrayList<ObjectId> friends = user.getFriends();

        for (ObjectId id : friends) {
            User friend = users.getSingle("_id", id);
            FriendPanel friendPanel = new FriendPanel(friend);
            friendPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickedUser = id;
                    listener.onContentChange(ContentChange.OTHER_PROFILE_ENTER_FRIENDS);
                }
            });
            friendPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.add(friendPanel);
        }


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

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public ObjectId getClickedUser() {
        return clickedUser;
    }
}
