package com.mehmetyurekli;

import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import com.mehmetyurekli.Util.ContentChange;
import com.mehmetyurekli.Util.ContentListener;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.util.ArrayList;


public class NotificationMonitor implements Runnable {

    private ObjectId id;
    private MongoRepository<User> repository;
    private ArrayList<ObjectId> invites;
    private ArrayList<ObjectId> friends;
    private int inviteCount;
    private int friendCount;
    private ContentListener listener;

    private ArrayList<ObjectId> notifications;

    public NotificationMonitor(ObjectId id){
        repository = new MongoRepository<>("Social", "Users", User.class);
        this.id = id;
        invites = repository.getSingle("_id", id).getInvites();
        friends = repository.getSingle("_id", id).getFriends();
        inviteCount = invites.size();
        friendCount = friends.size();
        notifications = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            invites = repository.getSingle("_id", id).getInvites();
            if(invites.size() > inviteCount){
                for(int i = invites.size()-1; i >= inviteCount; i--){
                    notifications.add(invites.get(i));
                }
                listener.onContentChange(ContentChange.FRIEND_REQUEST);
            }
            friends = repository.getSingle("_id", id).getFriends();
            if(friends.size() != friendCount){
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

    public ArrayList<ObjectId> getNotifications(){
        return notifications;
    }
}
