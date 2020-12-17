package com.example.caloriecounter.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginManagerStub implements LoginManager {

    public static final String PREFERENCES_NAME = "login-manager";
    public static final String PREFERENCE_USERNAME = "username";
    public static final String PREFERENCE_UUID = "uuid";
    private final DatabaseHandler dbh;



    private long userId;
    private static class Account{
        public String uuid;
        public String password;

        public Account(String uuid, String password) {
            this.uuid = uuid;
            this.password = password;
        }
    }
    public long getUserId() {
        return userId;
    }
    private static Map<String,Account> accounts;

    static {
        accounts = new HashMap<>();
        accounts.put("Pat", new Account(UUID.randomUUID().toString(),"password"));
        accounts.put("Ian", new Account(UUID.randomUUID().toString(),"emacs"));
    }

    private boolean loggedIn;
    private String username;
    private String uuid;

    private OnLoginListener onLoginListener;

    private Context context;

    public LoginManagerStub(Context context) {
        this.context = context;
        dbh = new DatabaseHandler(context);
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        if(preferences.contains(PREFERENCE_USERNAME)){
            loggedIn=true;
            username=preferences.getString(PREFERENCES_NAME,"");
            uuid=preferences.getString(PREFERENCE_UUID,"");
        }
        else
            loggedIn=false;
    }

    @Override
    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    @Override
    public void login(String username, String password) {
        user tempUser= dbh.get_UserForLogin(username,password);
        if(tempUser!=null){

            loggedIn=true;
            userId=tempUser.getId();
            this.username=username;
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
            preferences.edit()
                    .putString(PREFERENCE_USERNAME,username)
                    .putString(PREFERENCE_UUID,tempUser.getUUID())
                    .apply();
            if(onLoginListener!=null)
                onLoginListener.onLogin(tempUser.getUUID());
        }
        else{
            if(onLoginListener!=null)
                onLoginListener.onError("Invalid username or password");
            loggedIn=false;
        }

    }

    @Override
    public void logout() {
        loggedIn=false;
        username="";
        uuid="";
        userId=0;
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        preferences.edit()
                .remove(PREFERENCE_USERNAME)
                .remove(PREFERENCE_UUID)
                .apply();

        if(onLoginListener!=null)
            onLoginListener.onLogout();
    }

    @Override
    public void register(String username, String password, String passwordCheck) {

        LoginDialogFragment dialogFragment = new LoginDialogFragment();
        if(!password.equals(passwordCheck)){
            if(onLoginListener!=null)
                onLoginListener.onError("Passwords do not match");

        }
        if(dbh.userNameExists(username)){
            if(onLoginListener!=null)
                onLoginListener.onError("Username in use");
        }

        uuid=UUID.randomUUID().toString();
        loggedIn=true;
        this.username=username;
        try {
            dbh.get_User_table().create(new user(username,password));
        }
        catch (Exception e){

        }
        if(onLoginListener!=null)
            onLoginListener.onRegister(uuid);
        return;
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public String getUsername() {
        if(!loggedIn)
            throw new IllegalStateException("Not logged in");
        return username;
    }

    @Override
    public String getUuid() {
        if(!loggedIn)
            throw new IllegalStateException("Not logged in");
        return uuid;
    }
}
