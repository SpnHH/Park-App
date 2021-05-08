package com.example.parkapp.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class JsonHandler {
    public RequestQueue queue;
    public  JSONArray jsonArray;
    String err = null;
    String url = null;

    public JsonHandler(Context ctx) {
        queue = Volley.newRequestQueue(ctx);
        this.jsonArray = new JSONArray();
        this.url = null;
        err = null;
    }

    public String request(String url) {
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

    public JSONArray getJsonArray(){
        return jsonArray;
    }

}
