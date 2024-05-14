package com.mehmetyurekli;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.NotificationType;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;

public class NotificationPanel extends JPanel {

    private NotificationType type;
    private ObjectId id;
    private MongoRepository<User> users;
    private ContentListener listener;

    public NotificationPanel(NotificationType type, ObjectId id){
        this.type = type;
        this.id = id;
        users = new MongoRepository<>("Social", "Users", User.class);
        init();
    }

    private void init(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 15 15 15 15", "[785, left]", "[center]"));

        panel.setBackground(new Color(69, 69, 69));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 25");

        JLabel title = new JLabel();
        title.setFont(new Font("Public Sans", Font.BOLD, 24));
        JLabel description = new JLabel();

        if(type.equals(NotificationType.FRIEND_INVITATION)){
            title.setText("Friend Invitation");
            description.setText(users.getSingle("_id", id).getUsername() + " wants to be your friend.");
        }
        else{
            title.setText("New Post");
            description.setText(users.getSingle("_id", id).getUsername() + " shared a new post.");
        }

        panel.add(title, "wrap");
        panel.add(description, "wrap");

        JButton accept = new JButton("Accept");
        accept.setMaximumSize(new Dimension(400, 40));
        accept.setBackground(new Color(45, 110, 0));

        JButton decline = new JButton("Decline");
        decline.setMaximumSize(new Dimension(400, 40));
        decline.setBackground(new Color(161, 9, 9));

        panel.add(decline, "grow, split 2");
        panel.add(accept, "grow");

        accept.addActionListener(e -> {
            users.pullFromArray("_id", UserManager.getCurrentUser().getId(), "invites", id);
            listener.onContentChange(ContentChange.NOTIFICATION_CHANGE);
            if(!users.getSingle("_id", UserManager.getCurrentUser().getId()).isFriend(id)){
                users.pushToArray("_id", UserManager.getCurrentUser().getId(), "friends", id);
                users.pushToArray("_id", id, "friends", UserManager.getCurrentUser().getId());
            }

        });

        decline.addActionListener(e -> {
            users.pullFromArray("_id", UserManager.getCurrentUser().getId(), "invites", id);
            listener.onContentChange(ContentChange.NOTIFICATION_CHANGE);
        });

        this.add(panel);

    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }
}