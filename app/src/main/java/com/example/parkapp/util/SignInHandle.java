package com.example.parkapp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInHandle {

    public SignInHandle() {
    }

    public boolean SignIn(JSONArray fromDB, String username, String password) throws JSONException {

        for(int i = 0;i< fromDB.length(); i++){
            String userDB = fromDB.getJSONObject(i).getString("username");
            String passDB = fromDB.getJSONObject(i).getString("pass");
            if (username.equals(userDB)){
                if (password.equals(passDB)){
                    return true;
                }
            }
        }
        return false;
    }

}
