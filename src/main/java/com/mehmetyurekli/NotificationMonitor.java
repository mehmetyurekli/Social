package com.mehmetyurekli;

import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;


public class NotificationMonitor implements Runnable {

    private ObjectId id;
    private MongoRepository<User> repository;
    private ArrayList<ObjectId> invites;
    private int inviteCount;

    public NotificationMonitor(ObjectId id){
        repository = new MongoRepository<User>("Social", "Users", User.class);
        this.id = id;
        invites = repository.getSingle("_id", id).getInvites();
        inviteCount = invites.size();
    }

    @Override
    public void run() {
        while (true) {
            invites = repository.getSingle("_id", id).getInvites();
            if(invites.size() > inviteCount){
                System.out.println("new invite");
            }
            inviteCount = invites.size();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
