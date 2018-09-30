package com.chocksaway.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
    @Id
    private String id;
    @Indexed
    private String username;
    private String password;

    public Account(){}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return String.format(
                "Account[id=%s, username='%s', password='%s']",
                id, username, password);
    }
}
