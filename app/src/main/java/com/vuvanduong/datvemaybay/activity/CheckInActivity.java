package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.vuvanduong.datvemaybay.R;

public class CheckInActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        addControl();
        addEvent();
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar_checkin);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.drawerCheckin);
        navigationView = findViewById(R.id.nav_view_Checkin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(CheckInActivity.this,drawerLayout,toolbar,R.string.about_me,R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void addEvent() {
    }
}
