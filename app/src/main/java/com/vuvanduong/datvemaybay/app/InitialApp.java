package com.vuvanduong.datvemaybay.app;

import android.app.Application;
import android.content.Intent;

import com.vuvanduong.datvemaybay.activity.WelcomeActivity;
import com.vuvanduong.datvemaybay.mail.GMailSender;

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
