package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.app.InitialApp;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.notify.NotifyService;

import org.json.JSONObject;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        startService(new Intent().setClass(this, NotifyService.class));

        HashMap<String, String> data = new HashMap<>();
        data.put("mave",String.valueOf(System.currentTimeMillis()).substring(7,13));
        data.put("soghe","A40");
        data.put("macb","VN01");
        String url = Constant.DOMAIN_NAME+ "api/ve/insert";

        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        HashMap<String, String> data1 = new HashMap<>();
        data1.put("mave",String.valueOf(System.currentTimeMillis()).substring(7,13));
        data1.put("soghe","A40");
        data1.put("macb","VN04");

        JsonObjectRequest jsonobj1 = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data1),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(jsonobj);
        queue.add(jsonobj1);

        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
