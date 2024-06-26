package com.mehmetyurekli.Util;

import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;


public class NotificationMonitor implements Runnable {

    /* This monitor checks every second for a new notification or change of friend number.
    * notifies its listener(SocialApp) */

    private final MongoRepository<User> repository;
    private ArrayList<ObjectId> invites;
    private ArrayList<ObjectId> friends;
    private int inviteCount;
    private int friendCount;
    private ContentListener listener;

    private final ArrayList<ObjectId> notifications;

    public NotificationMonitor() {
        repository = new MongoRepository<>("Social", "Users", User.class);
        invites = repository.getSingle("_id", UserManager.getCurrentUser().getId()).getInvites();
        friends = repository.getSingle("_id", UserManager.getCurrentUser().getId()).getFriends();
        inviteCount = invites.size();
        friendCount = friends.size();
        notifications = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            if(UserManager.getCurrentUser() == null){
                return;
            }
            invites = repository.getSingle("_id", UserManager.getCurrentUser().getId()).getInvites();
            if (invites.size() > inviteCount) {
                for (int i = invites.size() - 1; i >= inviteCount; i--) {
                    notifications.add(invites.get(i));
                }
                listener.onContentChange(ContentChange.FRIEND_REQUEST);
            }
            friends = repository.getSingle("_id", UserManager.getCurrentUser().getId()).getFriends();
            if (friends.size() != friendCount) {
                listener.onContentChange(ContentChange.FRIEND_UPDATE);
            }
            friendCount = friends.size();
            inviteCount = invites.size();
            notifications.clear();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public ArrayList<ObjectId> getNotifications() {
        return notifications;
    }
}
