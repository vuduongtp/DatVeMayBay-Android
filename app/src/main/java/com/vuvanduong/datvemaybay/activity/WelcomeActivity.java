package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.config.SharedPrefs;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private void setLanguage(String local, String country)  {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(local.toLowerCase(),country)); // API 17+ only.
        //Toast.makeText(this, local+"-"+country, Toast.LENGTH_SHORT).show();
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Locale current = getResources().getConfiguration().locale;
        String language = SharedPrefs.getInstance().get(Constant.LANGUAGE_CODE, String.class);
        String country = "";
        if (!language.equalsIgnoreCase(current.toString())) {
            if (language.equalsIgnoreCase("vi")) {
                country = "VN";
                setLanguage(language, country);
                Intent refresh = new Intent(this, MainActivity.class);
                finish();
                startActivity(refresh);
            } else if (language.equalsIgnoreCase("en")) {
                country = "EN";
                setLanguage(language, country);
                Intent refresh = new Intent(this, MainActivity.class);
                finish();
                startActivity(refresh);
            } else return;
        }
        //startService(new Intent().setClass(this, NotifyService.class));

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
       // queue.add(jsonobj);
       // queue.add(jsonobj1);

        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
