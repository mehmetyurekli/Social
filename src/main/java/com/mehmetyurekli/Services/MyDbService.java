package com.mehmetyurekli.Services;

import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Util.MongoConnector;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;


public class MyDbService implements DatabaseService {
    private MongoClient mongoClient;
    private MongoDatabase db;

    @Override
    public MyDbService load(String database) {
        this.mongoClient = MongoConnector.getInstance().getMongo();
        this.db = mongoClient.getDatabase(database);
        return this;
    }

    @Override
    public User getUser(String username) {
        MongoCollection<User> users = db.getCollection("Users", User.class);
        return users.find(eq("username", username)).first();
    }

    @Override
    public void insertUser(User user) {
        MongoCollection<User> users = db.getCollection("Users", User.class);
        users.insertOne(user);
    }

    @Override
    public FindIterable<User> getVisibleUsers() {
        MongoCollection<User> users = db.getCollection("Users", User.class);
        return users.find(eq("visible", "true"));
    }
}
