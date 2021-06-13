package com.example.parkapp.util;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyTimer {
    TextView textView;

    public MyTimer(TextView textView) {
        this.textView = textView;

    }

    public void Timer(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();

    }
}
