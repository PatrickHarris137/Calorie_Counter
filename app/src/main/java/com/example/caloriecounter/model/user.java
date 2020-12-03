package com.example.caloriecounter.model;

import java.util.UUID;

public class user {
    private int id;
    private String UUID;
    private String username;
    private String password;
    private boolean is_login;


    public user(String username, String password) {
        this.username = username;
        this.UUID= java.util.UUID.randomUUID().toString();
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public boolean isIs_login() {
        return is_login;
    }

    public void setIs_login(boolean is_login) {
        this.is_login = is_login;
    }
}
