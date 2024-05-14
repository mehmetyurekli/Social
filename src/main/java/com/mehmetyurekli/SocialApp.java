package com.mehmetyurekli;

import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import com.mehmetyurekli.Util.NotificationType;
import com.mehmetyurekli.Util.SearchEngine;
import com.mehmetyurekli.Views.FriendsPanel;
import com.mehmetyurekli.Views.QueryPanel;
import com.mehmetyurekli.Views.SettingsPanel;
import com.mehmetyurekli.Views.TopBar;
import net.miginfocom.swing.MigLayout;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SocialApp extends JPanel implements ContentListener {

    private NotificationMonitor monitor;
    private TopBar bar;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private SettingsPanel settingsPanel;
    private QueryPanel queryPanel;
    private FriendsPanel friendsPanel;
    private NotificationsPanel notificationsPanel;
    private ProfilePanel profilePanel;
    private String lastIndex;
    private MongoRepository<User> users;

    public SocialApp(){
        init();
    }

    private void init(){
        users = new MongoRepository<>("Social", "Users", User.class);
        lastIndex = "0";

        contentPanel = new JPanel(new CardLayout());
        contentPanel.setPreferredSize(new Dimension(900, 500));

        settingsPanel = new SettingsPanel();
        settingsPanel.setListener(this);

        queryPanel = new QueryPanel();
        queryPanel.setListener(this);

        notificationsPanel = new NotificationsPanel(UserManager.getCurrentUser().getId());
        notificationsPanel.setListener(this);

        profilePanel = new ProfilePanel();
        profilePanel.setListener(this);

        friendsPanel = new FriendsPanel();
        friendsPanel.setListener(this);

        contentPanel.add(settingsPanel, "0");
        contentPanel.add(queryPanel, "1");
        contentPanel.add(profilePanel, "2");
        contentPanel.add(notificationsPanel, "3");


        bar = new TopBar();
        bar.setListener(this);

        mainPanel = new JPanel(new MigLayout("fill, insets 0 0 0 0", "[900][250]", "[][500]"));
        mainPanel.add(bar, "span 2, wrap");
        mainPanel.add(contentPanel, "cell 0 1, grow");
        mainPanel.add(friendsPanel, "cell 1 1, grow");
        this.add(mainPanel);
        if(!UserManager.getCurrentUser().getInvites().isEmpty()){
            JOptionPane.showMessageDialog(this, "You have " + UserManager.getCurrentUser().getInvites().size() + " invites.");
        }

    }

    @Override
    public void onContentChange(ContentChange changeType) {
        switch(changeType){
            case ContentChange.SETTINGS_ENTER:
                SwingUtilities.invokeLater(() -> {
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    cardLayout.show(contentPanel, "0");
                });
                break;


            case ContentChange.SETTINGS_EXIT:
                SwingUtilities.invokeLater(() -> {
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, lastIndex);
                });
                break;

            case ContentChange.QUERY_ENTER:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "1";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    queryPanel = new QueryPanel(SearchEngine.searchUsers(bar.getSearchBar().getText()));
                    queryPanel.setListener(this);
                    contentPanel.remove(1);
                    contentPanel.add(queryPanel, "1");
                    cardLayout.show(contentPanel, "1");

                });
                break;

            case PROFILE_ENTER:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "2";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    profilePanel = new ProfilePanel(UserManager.getCurrentUser());
                    profilePanel.setListener(this);
                    contentPanel.remove(2);
                    contentPanel.add(profilePanel, "2");
                    cardLayout.show(contentPanel, "2");
                });
                break;

            case ContentChange.OTHER_PROFILE_ENTER_QUERY:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "2";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    profilePanel = new ProfilePanel(users.getSingle("_id", queryPanel.getClickedUser()));
                    profilePanel.setListener(this);
                    contentPanel.remove(2);
                    contentPanel.add(profilePanel, "2");
                    cardLayout.show(contentPanel, "2");
                });
                break;
            case ContentChange.OTHER_PROFILE_ENTER_FRIENDS:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "2";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    profilePanel = new ProfilePanel(users.getSingle("_id", friendsPanel.getClickedUser()));
                    profilePanel.setListener(this);
                    contentPanel.remove(2);
                    contentPanel.add(profilePanel, "2");
                    cardLayout.show(contentPanel, "2");
                });
                break;

            case ContentChange.FRIEND_REQUEST:
                ArrayList<ObjectId> ids = monitor.getNotifications();
                for(ObjectId id : ids){
                    NotificationPanel notification =
                            new NotificationPanel(NotificationType.FRIEND_INVITATION, id);
                    SwingUtilities.invokeLater(() -> {
                        notificationsPanel.addNotification(notification);
                        notificationsPanel.revalidate();
                        notificationsPanel.repaint();
                    });
                }
                JOptionPane.showMessageDialog(this, "You got " + ids.size() + " new notifications.");

                break;

            case ContentChange.NOTIFICATIONS_ENTER, ContentChange.NOTIFICATION_CHANGE:
                SwingUtilities.invokeLater(() -> {
                    lastIndex = "3";
                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    notificationsPanel = new NotificationsPanel(UserManager.getCurrentUser().getId());
                    notificationsPanel.setListener(this);
                    contentPanel.remove(3);
                    contentPanel.add(notificationsPanel, "3");
                    cardLayout.show(contentPanel, "3");

                    mainPanel.remove(friendsPanel);
                    friendsPanel = new FriendsPanel();
                    friendsPanel.setListener(this);
                    mainPanel.add(friendsPanel, "cell 1 1, grow");
                    mainPanel.revalidate();
                    mainPanel.repaint();

                });
                break;

            case FRIEND_UPDATE:
                SwingUtilities.invokeLater(() -> {
                    mainPanel.remove(friendsPanel);
                    friendsPanel = new FriendsPanel();
                    friendsPanel.setListener(this);
                    mainPanel.add(friendsPanel, "cell 1 1, grow");
                    mainPanel.revalidate();
                    mainPanel.repaint();
                });
                break;

        }

    }

    public NotificationMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(NotificationMonitor monitor) {
        this.monitor = monitor;
    }
}
