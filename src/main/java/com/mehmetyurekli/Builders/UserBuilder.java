package com.mehmetyurekli.Builders;

import com.mehmetyurekli.Models.User;

public class UserBuilder {
    private User user;

    public UserBuilder(){
        user = new User();
    }
    public UserBuilder withName(String name){
        user.setName(name);
        return this;
    }
    public UserBuilder withSurname(String surname){
        user.setSurname(surname);
        return this;
    }
    public UserBuilder withGender(String gender){
        user.setGender(gender);
        return this;
    }
    public UserBuilder withUsername(String username){
        user.setUsername(username);
        return this;
    }
    public UserBuilder withEmail(String email){
        user.setEmail(email);
        return this;
    }
    public UserBuilder withPassword(String password){
        user.setPassword(password);
        return this;
    }

    public User build(){
        return user;
    }

}
