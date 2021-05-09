package com.example.parkapp.util;

public class UrlConfig {
    private String serverUrl;
    private String userUrl;
    private String codeUrl;

    public UrlConfig() {
        this.serverUrl = "https://e0eac5c6a7bc.ngrok.io";
        this.userUrl = "/api/v1/user";
        this.codeUrl= "/api/v1/code/";
    }

    public String getUserUrl() {
        return serverUrl + userUrl;
    }

    public String getCodeUrl() {
        return serverUrl + codeUrl;
    }


}
