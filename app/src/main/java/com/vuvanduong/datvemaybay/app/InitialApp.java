package com.vuvanduong.datvemaybay.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vuvanduong.datvemaybay.WelcomeActivity;
import com.vuvanduong.datvemaybay.activity.BookingActivity;
import com.vuvanduong.datvemaybay.activity.SelectAirportActivity;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.object.SanBay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class InitialApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);

    }



    public void init() {
        System.out.println("Chay vo day");
        MyVolley.init(this);
    }
}
