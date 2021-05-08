package com.example.parkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkapp.R;
import com.example.parkapp.util.JsonRequestHandle;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONArray;
import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmPassword;
    JsonRequestHandle jsonRequestHandle;
    JSONArray currentDB;
    UrlConfig urlConfig;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.registerUsername);
        password = (EditText)findViewById(R.id.registerPassword);
        confirmPassword = (EditText)findViewById(R.id.registerConfirmPassword);
        urlConfig = new UrlConfig();
        jsonRequestHandle = new JsonRequestHandle(this);
        jsonRequestHandle.request(urlConfig.getUserUrl());
        currentDB = new JSONArray();
        textView = (TextView) findViewById(R.id.registerResult);
        textView.setText("");
    }

    public void RegisterToDB(View view) throws JSONException {
        if(checkInDB(username.getText().toString(),"username")){
            textView.setText("Username is already taken");
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
}