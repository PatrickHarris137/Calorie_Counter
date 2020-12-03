package com.example.caloriecounter;

import android.app.Application;

import com.example.caloriecounter.login.LoginManager;
import com.example.caloriecounter.login.LoginManagerStub;

public class NoteApplication extends Application {

    private LoginManager loginManager;

    @Override
    public void onCreate() {
        super.onCreate();
        loginManager = new LoginManagerStub(this);
    }

    public LoginManager getLoginManager() {
        return  loginManager;
    }
}
