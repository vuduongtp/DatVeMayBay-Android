package com.vuvanduong.datvemaybay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.config.SharedPrefs;
import com.vuvanduong.datvemaybay.message.XemActivity;
import com.vuvanduong.datvemaybay.view.DialogLanguage;
import com.vuvanduong.datvemaybay.view.FragmentFamousPlace;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    Button btnSearchFlight;
    boolean isFirstLoad=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();

    }


    private void addEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_booking:
                        Intent intentBooking = new Intent(MainActivity.this, BookingActivity.class);
                        startActivity(intentBooking);
                        break;

                    case R.id.menu_notify:
                        Intent menu_notify = new Intent(MainActivity.this, XemActivity.class);
                        startActivity(menu_notify);
                        break;

                    case R.id.menu_checkin:
                        Intent menu_checkin = new Intent(MainActivity.this, CheckInActivity.class);
                        startActivity(menu_checkin);
                        finish();
                        break;

                    case R.id.menu_language:
                        DialogLanguage cdd=new DialogLanguage(MainActivity.this);
                        cdd.show();
                        break;
                }
                return true;
            }
        });

        btnSearchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBooking = new Intent(MainActivity.this, BookingActivity.class);
                startActivity(intentBooking);
            }
        });
    }

    private void addControl() {
        btnSearchFlight = findViewById(R.id.btnSearchFlight);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        FragmentFamousPlace fragmentFamousPlace = new FragmentFamousPlace();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_famousPlace,fragmentFamousPlace);
        fragmentTransaction.commit();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.drawerHome);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.about_me,R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.nav_home:

                    break;
                case R.id.nav_booking:
                    Intent intentBooking = new Intent(MainActivity.this, BookingActivity.class);
                    startActivity(intentBooking);
                    break;
                case R.id.nav_checkin:
                    Intent intentCheckin = new Intent(MainActivity.this, CheckInActivity.class);
                    startActivity(intentCheckin);
                    break;
                case R.id.nav_my_trip:
                    Intent intentFlight = new Intent(MainActivity.this, FlightActivity.class);
                    startActivity(intentFlight);
                    break;
            }


            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
