package com.mehmetyurekli;

import com.formdev.flatlaf.FlatClientProperties;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.NotificationType;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;

public class NotificationsPanel extends JPanel implements ContentListener{

    private JPanel panel;
    private ObjectId id;
    private MongoRepository<User> repository;
    private ContentListener listener;

    public NotificationsPanel(ObjectId id){
        this.id = id;
        repository = new MongoRepository<>("Social", "Users", User.class);
        init();
    }

    public NotificationsPanel(){
    }

    private void init(){
        setLayout(new MigLayout("wrap", "[center]", "[center]"));
        this.setBackground(new Color(69, 69, 69));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        panel = new JPanel(new MigLayout("wrap, insets 15 15 15 15, fillx", "[left]", "[center]"));

        for(ObjectId id : repository.getSingle("_id", this.id).getInvites()){
            NotificationPanel notification = new NotificationPanel(NotificationType.FRIEND_INVITATION, id);
            notification.setListener(this);
            panel.add(notification);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(69, 69, 69));
        scrollPane.setPreferredSize(new Dimension(900, 500));
        scrollPane.setForeground(Color.WHITE);

        this.add(scrollPane);

    }

    public void addNotification(NotificationPanel notification){
        notification.setListener(this);
        panel.add(notification);
    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onContentChange(ContentChange changeType) {
        listener.onContentChange(ContentChange.NOTIFICATION_CHANGE);
    }
}
