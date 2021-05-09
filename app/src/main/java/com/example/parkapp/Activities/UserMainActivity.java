package com.example.parkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parkapp.R;
import com.example.parkapp.util.CurrentUser;
import com.example.parkapp.util.JsonHandler;
import com.example.parkapp.util.UrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class UserMainActivity extends AppCompatActivity {

    public CurrentUser currentUser;
    public TextView textView;
    public TextView textView1;
    public Button btnGenerate;
    public JsonHandler jsonHandler;
    public UrlConfig urlConfig;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        textView = (TextView) findViewById(R.id.usetTextView);
        textView1 = (TextView) findViewById(R.id.txtCode);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        jsonHandler = new JsonHandler(this,textView1);
        currentUser = new CurrentUser();
        urlConfig = new UrlConfig();
        textView.setText("Hello " + currentUser.getUsername());
        jsonObject = new JSONObject();
      // txtCode.setText(currentUser.getCode());
    }


    public void GenerateCode(View view) throws JSONException {
        jsonHandler.requestPost(urlConfig.getCodeUrl()+ currentUser.getId(),jsonObject);
        String str = urlConfig.getCodeUrl()+ currentUser.getId();
        jsonHandler.requestJSONObject(str);

        return;
    }
}