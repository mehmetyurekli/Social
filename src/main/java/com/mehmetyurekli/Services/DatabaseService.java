package com.mehmetyurekli.Services;

import com.mehmetyurekli.Models.User;
import com.mongodb.client.FindIterable;

public interface DatabaseService {

    public MyDbService load(String database);

    public User getUser(String username);

    public void insertUser(User user);

    public FindIterable<User> getVisibleUsers();
}
