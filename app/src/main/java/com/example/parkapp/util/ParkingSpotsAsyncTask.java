package com.example.parkapp.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParkingSpotsAsyncTask extends AsyncTask<Void, Void, Void> {
    JsonHandler jsonHandler;
    UrlConfig urlConfig;
    JSONObject jsonObject;
    TextView parkingSpotTxtView;
    JSONArray jsonArray;
    JSONArray jsonArrayPrevious;
    ParkingSpots parkingSpots;

    public ParkingSpotsAsyncTask(JsonHandler jsonHandler, TextView parkingSpotTxtView, Context ctx) {
//        try {
//            this.jsonHandler = new JsonHandler(ctx);
//            this.jsonObject = jsonObject;
//            this.parkingSpotTxtView = parkingSpotTxtView;
//            this.parkingSpots = parkingSpots;
//            jsonArray = new JSONArray();
//            jsonArrayPrevious = new JSONArray();
//            urlConfig = new UrlConfig();
//            //jsonHandler.request(urlConfig.getUserUrl());
//            String str = urlConfig.getSpotUrl();
//            jsonArray = jsonHandler.requestJsonArray(str);
//            parkingSpots = new ParkingSpots(jsonArray.length());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        //while (true) {
            try {
                Thread.sleep(500);
                jsonHandler.request(urlConfig.getSpotUrl());
                jsonArray = jsonHandler.getJsonArray();

//                for (int i = 0; i < jsonArray.length(); i++) {
//                    if ((jsonArray.getJSONObject(i).get("available").toString() == "true") && (parkingSpots.getSpotAvailabilityArray(i) == false)) {
//                        parkingSpots.increaseAvailableSpots();
//                        parkingSpots.setSpotAvailabilityArray(i, true);
//                    } else if ((jsonArray.getJSONObject(i).get("available").toString() == "false") && (parkingSpots.getSpotAvailabilityArray(i) == true)) {
//                        parkingSpots.decreaseAvailableSpots();
//                        parkingSpots.setSpotAvailabilityArray(i, false);
//                    }
//                    parkingSpotTxtView.setText(parkingSpots.getAvailableSpotsNR() + "");
//                }
            } catch (JSONException | InterruptedException e) {
                e.printStackTrace();
            }
        //}
        return null;
    }


}

