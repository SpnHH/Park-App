package com.example.parkapp.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class MyTimer {
    TextView textView;
    TextView textView1;
    JsonHandler jsonHandler;
    String url;
    boolean isDone;
    public CurrentUser currentUser;

    public MyTimer(TextView textView, JsonHandler jsonHandler, String url, TextView textView1) {
        this.textView = textView;
        this.jsonHandler = jsonHandler;
        this.url = url;
        isDone = true;
        this.textView1 = textView1;
    }

    public void Timer() throws JSONException {
         //JSONObject jsonObject= jsonHandler.requestJSONObject(url);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
                isDone = false;
                try {
                    if(jsonHandler.checkIfCodeWasUsed(url)){
                        this.finalize();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }

            public void onFinish() {
                textView.setText("done!");
                jsonHandler.requestDelete(url);
                textView1.setText("Code expired");
                isDone = true;
            }
        }.start();

    }
    public boolean getStatus(){
        return isDone;
    }
}
