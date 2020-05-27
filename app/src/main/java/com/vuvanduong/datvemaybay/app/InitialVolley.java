package com.vuvanduong.datvemaybay.app;

import android.app.Application;

public class InitialVolley extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }


    public void init() {
        System.out.println("Chay vo day");
        MyVolley.init(this);
    }
}
