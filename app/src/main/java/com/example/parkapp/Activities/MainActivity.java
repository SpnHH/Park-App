package com.example.parkapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.parkapp.R;
import com.example.parkapp.util.JsonHandler;
import com.example.parkapp.util.SignInHandle;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity{
    public TextView textView ;
    public RequestQueue queue;
    public JSONArray jsonArray;
    public SignInHandle signInHandle;
    public EditText username;
    public EditText pass;
    public JsonHandler jsonRequestHandle;
    UrlConfig urlConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        jsonArray = new JSONArray();
        signInHandle = new SignInHandle();
        username = (EditText) findViewById(R.id.txtUsename);
        pass = (EditText) findViewById(R.id.txtPass);

        queue = Volley.newRequestQueue(this);

        urlConfig = new UrlConfig();
        jsonRequestHandle = new JsonHandler(this);
        textView.setText(jsonRequestHandle.request(urlConfig.getUserUrl()));

    }

    public void clickSignIn(View view) throws JSONException {
        jsonArray = jsonRequestHandle.getJsonArray();
        if(signInHandle.SignIn(jsonArray, username.getText().toString(),pass.getText().toString())){
             Intent i = new Intent(this, UserMainActivity.class);
             startActivity(i);
        }else{
            textView.setText("Invalid username or password");
        }

    }


    public void clickRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}