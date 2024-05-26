package com.example.appdev_finalproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private String userID;
    private String userName;


    public SessionManager() {
    }

    public void saveUserSession(String userId, String userName) {
        this.userID = userId;
        this.userName = userName;
    }

    public void clearUserSession() {
        this.userID = null;
        this.userName = null;
    }

    public String getUserId() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
}