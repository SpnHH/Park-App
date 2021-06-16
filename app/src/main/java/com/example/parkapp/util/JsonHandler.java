package com.example.parkapp.util;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler {
    public RequestQueue queue;
    public  JSONArray jsonArray;
    public volatile JSONObject jsonObject;
    String err = null;
    String url = null;
    CurrentUser currentUser;
    TextView textView;

    public JsonHandler(Context ctx) {
        queue = Volley.newRequestQueue(ctx);
        this.jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        this.url = null;
        err = null;
        currentUser = new CurrentUser();
    }
    public JsonHandler(Context ctx, TextView textView) {
        queue = Volley.newRequestQueue(ctx);
        this.jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        this.url = null;
        err = null;
        currentUser = new CurrentUser();
        this.textView = textView;
    }


    public JSONArray getJsonArray(){
        return jsonArray;
    }

    public String request(String url) throws JSONException {
        //String url = "https://3e456f32a1ad.ngrok.io/api/v1/user";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                jsonArray = response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                err = error.toString();
            }
        });

        queue.add(jsonArrayRequest);
        return err;
    }

    String resultPost;
    public String requestPost(String url, JSONObject jsonToPost){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonToPost, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                resultPost = "success";
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultPost = "failed";
            }
        });
        queue.add(jsonObjectRequest);
            return resultPost;
    }


    public StringRequest requestJSONObject(String url) throws JSONException {

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                err = response;
                try {
                    jsonObject = new JSONObject(response);
                    currentUser.setCode(jsonObject.getString("pass").toString());
                    textView.setText(currentUser.getCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            err=error.toString();
            }
        }) ;

        queue.add(jsonObjectRequest);
        //currentUser.setCode(jsonObject.getString("password").toString());
//        textView.setText(currentUser.getCode());
        currentUser.setCode(jsonObject.getString("pass").toString());
        return jsonObjectRequest;
    }

    public StringRequest requestDelete(String url){
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                err = response;
//                textView.setText("Code expired");

            }
        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error) {
                err=error.toString();
            }
        });
        queue.add(deleteRequest);
        return deleteRequest;
    }

    public void addQueue(StringRequest request){
        queue.add(request);
    }

    public boolean checkIfCodeWasUsed(String url) throws JSONException {

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                err = response;
                try {
                    jsonObject = new JSONObject(response);
                    currentUser.setCode(jsonObject.getString("pass").toString());
                    //textView.setText(currentUser.getCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "error";
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // err = "error";
            }
        }) ;

        queue.add(jsonObjectRequest);
        if(err.toString() == "error"){
            return true;
        }else{
            return false;
        }
    }
}
