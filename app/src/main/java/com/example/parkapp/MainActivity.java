package com.example.parkapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parkapp.util.SignInHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    public TextView textView ;
    public RequestQueue queue;
    public  JSONArray jsonArray;
    public SignInHandle signInHandle;
    public EditText username;
    public EditText pass;

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
        String url = "https://5611301eb55e.ngrok.io/api/v1/user" ;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                jsonArray = response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                textView.setText("error: " + error.toString());

            }
        });

        queue.add(jsonArrayRequest);
    }

    public void clickSignIn(View view) throws JSONException {
        if(signInHandle.SignIn(jsonArray, username.getText().toString(),pass.getText().toString())){
            textView.setText("success ");
        }else{
            textView.setText("failed ");
        }

    }


    public void clickRegister(View view) {
    }
}