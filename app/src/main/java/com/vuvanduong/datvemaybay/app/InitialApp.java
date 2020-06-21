package com.vuvanduong.datvemaybay.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.activity.MainActivity;
import com.vuvanduong.datvemaybay.activity.WelcomeActivity;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.config.SharedPrefs;

import java.util.Locale;

public class InitialApp extends Application {
    private static InitialApp sInstance;

    public static InitialApp self() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance=this;

        init();

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);

    }


        public void init() {
        //System.out.println("Chay vo day");
        MyVolley.init(this);

        }

}
