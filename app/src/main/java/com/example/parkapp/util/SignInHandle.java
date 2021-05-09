package com.example.parkapp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInHandle {
    public CurrentUser currentUser;
    public SignInHandle() {
        currentUser = new CurrentUser();
    }

    public boolean SignIn(JSONArray fromDB, String username, String password) throws JSONException {

        for(int i = 0;i< fromDB.length(); i++){
            String userId = fromDB.getJSONObject(i).getString("id");
            String userDB = fromDB.getJSONObject(i).getString("username");
            String passDB = fromDB.getJSONObject(i).getString("pass");
            if (username.equals(userDB)){
                if (password.equals(passDB)){
                    currentUser.setAll(userId,userDB,passDB);
                    return true;
                }
            }
        }
        return false;
    }

}
