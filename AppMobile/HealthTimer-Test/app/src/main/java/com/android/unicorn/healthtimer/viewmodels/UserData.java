package com.android.unicorn.healthtimer.viewmodels;

public class UserData {
    private static final UserData ourInstance = new UserData();
    public static UserData getInstance() {
        return ourInstance;
    }
    private UserData() {

    }

    String phone;
    String username;
    String auth;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
