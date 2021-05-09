package com.example.parkapp.util;

public class CurrentUser {

    private static String id;
    private static String username;
    private static String password;
    private volatile String code;

    public CurrentUser() {

    }

    public void setAll(String id, String username , String password){
        this.id = id;
        this.username = username;
        this.password = password;
        code = " ";
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
