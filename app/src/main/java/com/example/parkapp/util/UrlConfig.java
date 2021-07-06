package com.example.parkapp.util;

public class UrlConfig {
    private String serverUrl;
    private String userUrl;
    private String codeUrl;
    private String spotUrl;

    public UrlConfig() {
        this.serverUrl = "https://1586ecf49099.ngrok.io";
        this.userUrl = "/api/v1/user";
        this.codeUrl= "/api/v1/code/";
        this.spotUrl= "/api/v1/spot/";
    }

    public String getUserUrl() {
        return serverUrl + userUrl;
    }

    public String getCodeUrl() {
        return serverUrl + codeUrl;
    }

    public String getSpotUrl() {
        return serverUrl + spotUrl;
    }


}
