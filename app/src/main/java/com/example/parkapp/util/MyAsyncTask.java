package com.example.parkapp.util;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;


public class MyAsyncTask extends AsyncTask<Void, Void, Void>
{
     JsonHandler jsonHandler;
     String url;
     JSONObject jsonObject;
     TextView timerTxtView;
     TextView txtView;
     MyTimer myTimer;
     CountDownTimer countDownTimer;

    public MyAsyncTask(JsonHandler jsonHandler, String url, JSONObject jsonObject, TextView timerTxtView, TextView txtView) {
        this.jsonHandler = jsonHandler;
        this.url = url;
        this.jsonObject = jsonObject;
        this.timerTxtView = timerTxtView;
        this.txtView = txtView;
        //myTimer = new MyTimer(timerTxtView,jsonHandler,url,CodeTxtView);
        countDownTimer = new CountDownTimer(60*15*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timerTxtView.setText( hms);
                checkDB();
                //isDone = false;
            }

            public void onFinish() {
                timerTxtView.setText("done!");
                jsonHandler.requestDelete(url);
                txtView.setText("Code expired");
                //isDone = true;
//                jsonHandler.addQueue(jsonHandler.requestDelete(url));
            }
        };
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            jsonHandler.requestPost(url, jsonObject);
            Thread.sleep(200);
            jsonHandler.requestJSONObject(url);
            Thread.sleep(200);

            Thread.sleep(200);
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        countDownTimer.start();

        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
            cancel(true);
    }

    public void checkDB(){
        try {
            if(jsonHandler.checkIfCodeWasUsed(url)){
                countDownTimer.cancel();
                timerTxtView.setText("done!");
                jsonHandler.requestDelete(url);
                txtView.setText("Code was already used");
            }
    } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


