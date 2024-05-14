package com.mehmetyurekli.Login;

import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;
import org.bson.types.ObjectId;

public class UserManager {

    private static ObjectId currentUser;
    private static final MongoRepository<User> users = new MongoRepository<User>("Social", "Users", User.class);

    public static User getCurrentUser(){
        return users.getSingle("_id", currentUser);
    }

    public static void setCurrentUser(ObjectId id){
        currentUser = id;
    }
}
