package com.mehmetyurekli.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public class Post {

    @BsonProperty("_id")
    private ObjectId id;
    private String title;
    private String content;
    private ObjectId owner;
    private Date date;

    public Post() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectId getOwner() {
        return owner;
    }

    public void setOwner(ObjectId owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
