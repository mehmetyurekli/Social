package com.mehmetyurekli.Builders;

import com.mehmetyurekli.Models.Account;
import com.mehmetyurekli.Models.User;

public class UserBuilder {
    User user;
    Account account;

    public UserBuilder(){
        user = new User();
        account = new Account();
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
        account.setEmail(email);
        return this;
    }
    public UserBuilder withPassword(String password){
        account.setPassword(password);
        return this;
    }

    public User build(){
        user.setAccount(account);
        return user;
    }

}
