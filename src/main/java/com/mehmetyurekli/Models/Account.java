package com.mehmetyurekli.Models;

import com.mehmetyurekli.Util.MongoConnector;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Account {

    @BsonProperty("_id")
    private ObjectId id;
    private String email;
    private String password;

    public Account() {

    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private MongoCollection<Account> getAccounts() {
        MongoClient client = MongoConnector.getInstance().getMongo();
        MongoDatabase db = client.getDatabase("Social");
        MongoCollection<Account> students = db.getCollection("Accounts", Account.class);
        return students;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


