package com.example.parkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkapp.R;
import com.example.parkapp.util.CurrentUser;
import com.example.parkapp.util.JsonHandler;
import com.example.parkapp.util.MyAsyncTask;
import com.example.parkapp.util.MyTimer;
import com.example.parkapp.util.ParkingSpots;
import com.example.parkapp.util.ParkingSpotsAsyncTask;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class UserMainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    public CurrentUser currentUser;
    public TextView textView;
    public TextView textView1;
    public TextView spotsTxtView;
    public  TextView timerTextView;
    public Button btnGenerate;
    public Button btnCheckSpots;
    public JsonHandler jsonHandler;
    public UrlConfig urlConfig;
    JSONObject jsonObject;
    MyTimer myTimer;
    MyAsyncTask myAsyncTask;
    Thread myThread;
    ParkingSpotsAsyncTask parkingSpotsAsyncTask;
    JSONArray jsonArray;
    ParkingSpots parkingSpots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        textView = (TextView) findViewById(R.id.usetTextView);
        textView1 = (TextView) findViewById(R.id.txtCode);
        spotsTxtView = (TextView) findViewById(R.id.spotsTxtView);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        timerTextView = (TextView) findViewById(R.id.textTimer);
        btnCheckSpots = (Button)findViewById(R.id.btnCheckSpots);
        jsonHandler = new JsonHandler(this,textView1);
        currentUser = new CurrentUser();
        urlConfig = new UrlConfig();
        textView.setText("Hello " + currentUser.getUsername());
        jsonObject = new JSONObject();

        myTimer = new MyTimer(timerTextView,jsonHandler,urlConfig.getCodeUrl()+ currentUser.getId(), textView1);
        jsonHandler.requestDelete(urlConfig.getCodeUrl()+ currentUser.getId());

//        parkingSpotsAsyncTask = new ParkingSpotsAsyncTask(jsonHandler,spotsTxtView,this);
//        parkingSpotsAsyncTask.execute();
        String str = urlConfig.getSpotUrl();
        try {
            jsonArray = jsonHandler.requestJsonArray(str, spotsTxtView);
            parkingSpots = new ParkingSpots(3);
           // spotsTxtView.setText(jsonArray.length());

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void GenerateCode(View view) throws JSONException, InterruptedException {
//        if(myTimer.getStatus()){
//            String str = urlConfig.getCodeUrl()+ currentUser.getId();
//            jsonHandler.requestPost(urlConfig.getCodeUrl()+ currentUser.getId(),jsonObject) ;
//            myTimer.Timer();
//            jsonHandler.addQueue(jsonHandler.requestJSONObject(str));

//            textView1.setText(currentUser.getCode());
            //if()

//            jsonHandler.request(urlConfig.getSpotUrl());
//            jsonArray = jsonHandler.getJsonArray();
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                if ((jsonArray.getJSONObject(i).get("available").toString() == "true") && (parkingSpots.getSpotAvailabilityArray(i) == false)) {
//                parkingSpots.increaseAvailableSpots();
//                parkingSpots.setSpotAvailabilityArray(i, true);
//                } else if ((jsonArray.getJSONObject(i).get("available").toString() == "false") && (parkingSpots.getSpotAvailabilityArray(i) == true)) {
//                parkingSpots.decreaseAvailableSpots();
//                parkingSpots.setSpotAvailabilityArray(i, false);
//                }
//            parkingSpotTxtView.setText(parkingSpots.getAvailableSpotsNR() + "");
            String str = urlConfig.getCodeUrl()+ currentUser.getId();
            myAsyncTask = new MyAsyncTask( jsonHandler,str,jsonObject,timerTextView, textView1);
            myAsyncTask.execute();
            sendSMSMessage();
            parkingSpots.decreaseAvailableSpots();


//        }

    }

    public void CheckSpots(View view){

//        String str = urlConfig.getSpotUrl();
//        try {
//            jsonHandler.requestJsonArray(str, spotsTxtView);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if(jsonHandler.checkIfCodeWasUsed(urlConfig.getCodeUrl()+currentUser.getId())){
//            spotsTxtView.setText("used");
//        }else{
//            spotsTxtView.setText("not used");
//        }
//        try {
//            jsonHandler.request(urlConfig.getCodeUrl()+currentUser.getId());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        spotsTxtView.setText("Available Parking Spots : " + String.valueOf(parkingSpots.getAvailableSpotsNR()));
        parkingSpots.increaseAvailableSpots();
    }

    protected void sendSMSMessage() {
        String phoneNo = "0770116328";
//        phoneNo = txtphoneNo.getText().toString();
         String message = "";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0770116328", null,
                    "Access code is: "+ currentUser.getCode() +" The location is here: "+ " https://www.google.ro/maps/@44.4356384,24.369102,15z", null, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("0770116328", null,
                            "Access code is: "+ currentUser.getCode() +" The location is here: "+ " https://www.google.ro/maps/@44.4356384,24.369102,15z", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}