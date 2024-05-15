package com.mehmetyurekli.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class User {

    @BsonProperty("_id")
    private ObjectId id;
    private String name;
    private String surname;
    private String username;
    private String gender;
    private String email;
    private String password;
    private ArrayList<ObjectId> friends = new ArrayList<>();
    private ArrayList<ObjectId> invites = new ArrayList<>();
    private String about;
    private boolean visible = true;

    public User() {

    }

    public boolean isFriend(ObjectId id) {
        return friends.contains(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public ArrayList<ObjectId> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<ObjectId> friends) {
        this.friends = friends;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ArrayList<ObjectId> getInvites() {
        return invites;
    }

    public void setInvites(ArrayList<ObjectId> invites) {
        this.invites = invites;
    }
}
