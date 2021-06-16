package com.example.parkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parkapp.R;
import com.example.parkapp.util.CurrentUser;
import com.example.parkapp.util.JsonHandler;
import com.example.parkapp.util.MyAsyncTask;
import com.example.parkapp.util.MyTimer;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class UserMainActivity extends AppCompatActivity {

    public CurrentUser currentUser;
    public TextView textView;
    public TextView textView1;
    public  TextView timerTextView;
    public Button btnGenerate;
    public JsonHandler jsonHandler;
    public UrlConfig urlConfig;
    JSONObject jsonObject;
    MyTimer myTimer;
    MyAsyncTask myAsyncTask;
    Thread myThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        textView = (TextView) findViewById(R.id.usetTextView);
        textView1 = (TextView) findViewById(R.id.txtCode);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        timerTextView = (TextView) findViewById(R.id.textTimer);

        jsonHandler = new JsonHandler(this,textView1);
        currentUser = new CurrentUser();
        urlConfig = new UrlConfig();
        textView.setText("Hello " + currentUser.getUsername());
        jsonObject = new JSONObject();

        myTimer = new MyTimer(timerTextView,jsonHandler,urlConfig.getCodeUrl()+ currentUser.getId(), textView1);
        jsonHandler.requestDelete(urlConfig.getCodeUrl()+ currentUser.getId());

    }


    public void GenerateCode(View view) throws JSONException, InterruptedException {
//        if(myTimer.getStatus()){
//            String str = urlConfig.getCodeUrl()+ currentUser.getId();
//            jsonHandler.requestPost(urlConfig.getCodeUrl()+ currentUser.getId(),jsonObject) ;
//            myTimer.Timer();
//            jsonHandler.addQueue(jsonHandler.requestJSONObject(str));

//            textView1.setText(currentUser.getCode());
            //if()
            String str = urlConfig.getCodeUrl()+ currentUser.getId();
            myAsyncTask = new MyAsyncTask( jsonHandler,str,jsonObject,timerTextView, textView1);
            myAsyncTask.execute();


//        }




    }

}