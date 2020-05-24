package com.vuvanduong.datvemaybay.app;

import android.app.Application;

public class InitialVolley extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }


    private void init() {
        MyVolley.init(this);
    }
}
