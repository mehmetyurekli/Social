package com.mehmetyurekli.Builders;

import com.mehmetyurekli.Models.Post;
import org.bson.types.ObjectId;

import java.util.Date;

public class PostBuilder { //post builder class
    private final Post post;

    public PostBuilder() {
        post = new Post();
    }

    public PostBuilder withTitle(String title) {
        post.setTitle(title);
        return this;
    }

    public PostBuilder withContent(String content) {
        post.setContent(content);
        return this;
    }

    public PostBuilder withOwner(ObjectId owner) {
        post.setOwner(owner);
        return this;
    }

    public Post build() {
        post.setDate(new Date());
        return post;
    }
}
