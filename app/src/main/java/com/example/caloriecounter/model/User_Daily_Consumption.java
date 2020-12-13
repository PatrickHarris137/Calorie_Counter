package com.example.caloriecounter.model;

import com.example.caloriecounter.sqlite.Identifiable;

import java.util.Date;

public class User_Daily_Consumption implements Identifiable<Long> {
    private long id;

    public User_Daily_Consumption(long user_id, Date date) {
        this.user_id = user_id;
        this.date = date;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    private long user_id;
    private Date date;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
