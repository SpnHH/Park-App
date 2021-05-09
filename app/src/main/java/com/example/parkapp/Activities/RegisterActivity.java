package com.example.parkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parkapp.R;
import com.example.parkapp.util.JsonHandler;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmPassword;
    JsonHandler jsonRequestHandle;
    JSONArray currentDB;
    UrlConfig urlConfig;
    TextView textView;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.registerUsername);
        password = (EditText)findViewById(R.id.registerPassword);
        confirmPassword = (EditText)findViewById(R.id.registerConfirmPassword);
        urlConfig = new UrlConfig();
        jsonRequestHandle = new JsonHandler(this);
        try {
            jsonRequestHandle.request(urlConfig.getUserUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        currentDB = new JSONArray();
        textView = (TextView) findViewById(R.id.registerResult);
        textView.setText("");
        jsonObject = new JSONObject();
    }

    public void RegisterToDB(View view) throws JSONException {
        if(checkInDB(username.getText().toString(),"username")){
            textView.setText("Username is already taken");

        }else{
            if(password.getText().toString().equals(confirmPassword.getText().toString())){
                jsonRequestHandle.requestPost(urlConfig.getUserUrl(),makeJson());
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            }else{
                textView.setText("Passwords do not match");
            }
        }

    }

    //if str is find in BD return true
    public boolean checkInDB(String str, String nameOfField) throws JSONException {
        currentDB = jsonRequestHandle.getJsonArray();
        for(int i=0;i< currentDB.length();i++){
            if(currentDB.getJSONObject(i).getString(nameOfField).equals(str)){
                return true;
            }else{
                // do nothing
            }
        }
        return false;
    }

    private JSONObject makeJson(){
        try {
            jsonObject.put("id","");
            jsonObject.put("username", username.getText().toString());
            jsonObject.put("pass",password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return jsonObject;
        }



    }
