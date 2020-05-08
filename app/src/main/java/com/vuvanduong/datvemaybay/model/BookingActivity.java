package com.vuvanduong.datvemaybay.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.config.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BookingActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    LinearLayout layoutFrom, layoutTo, layoutDateGo, layoutDateArrival;
    TextView txtNumAdult, txtNumChildren, txtNumBaby, txtError, txtDateGo, txtFrom, txtTo, txtDateArrival;
    ImageView btnSubAdult, btnAddAdult, btnSubChildren, btnAddChildren, btnSubBaby, btnAddBaby;
    Button btnSearch, btnKhuHoi, btnMotChieu;

    Animation animation = null;
    boolean isRoundTrip = true;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    Date dateGo = null;
    Date dateArrival = null;

    String idPlaceFrom = "";
    String idPlaceTo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        addControl();
        addEvent();
    }

    private void addEvent() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(BookingActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_booking:

                        break;
                    case R.id.nav_checkin:
                        Intent intentCheckin = new Intent(BookingActivity.this, CheckInActivity.class);
                        startActivity(intentCheckin);
                        break;
                    case R.id.nav_my_trip:
                        Intent intentFlight = new Intent(BookingActivity.this, FlightActivity.class);
                        startActivity(intentFlight);
                        break;
                }
                return true;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_booking:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_home:
                        Intent intentHome = new Intent(BookingActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                }
                return true;
            }
        });

        btnKhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRoundTrip) {
                    btnKhuHoi.setBackground(getResources().getDrawable(R.drawable.button_round_trip));
                    btnMotChieu.setBackground(getResources().getDrawable(R.drawable.button_round_trip_unclick));
                    btnKhuHoi.setTextColor(getResources().getColor(R.color.white));
                    btnMotChieu.setTextColor(getResources().getColor(R.color.gray));
                    animation = AnimationUtils.loadAnimation(BookingActivity.this, R.anim.xoay_layout_vao);
                    layoutDateArrival.startAnimation(animation);
                    layoutDateArrival.setVisibility(View.VISIBLE);
                    isRoundTrip = true;
                }
            }
        });

        btnMotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRoundTrip) {
                    btnMotChieu.setBackground(getResources().getDrawable(R.drawable.button_round_trip));
                    btnKhuHoi.setBackground(getResources().getDrawable(R.drawable.button_round_trip_unclick));
                    btnMotChieu.setTextColor(getResources().getColor(R.color.white));
                    btnKhuHoi.setTextColor(getResources().getColor(R.color.gray));
                    animation = AnimationUtils.loadAnimation(BookingActivity.this, R.anim.xoay_layout_ra);
                    layoutDateArrival.startAnimation(animation);
                    layoutDateArrival.setVisibility(View.INVISIBLE);
                    isRoundTrip = false;
                    dateArrival = null;
                    txtDateArrival.setText("");
                }
            }
        });

        layoutFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(BookingActivity.this, SelectAirportActivity.class);
                startActivityForResult(intentSelectAirport, Constant.REQUEST_CODE_FROM);
            }
        });

        layoutTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(BookingActivity.this, SelectAirportActivity.class);
                startActivityForResult(intentSelectAirport, Constant.REQUEST_CODE_TO);
            }
        });

        layoutDateGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dateGo = calendar.getTime();
                        txtDateGo.setText(sdf1.format(calendar.getTime()));
                        calendar = Calendar.getInstance();
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, callBack,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);
                if (dateArrival != null) {
                    datePickerDialog.getDatePicker().setMaxDate(dateArrival.getTime() - 1000);
                }
                datePickerDialog.show();
            }
        });

        layoutDateArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dateArrival = calendar.getTime();
                        txtDateArrival.setText(sdf1.format(calendar.getTime()));
                        calendar = Calendar.getInstance();
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, callBack,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                if (dateGo != null) {
                    datePickerDialog.getDatePicker().setMinDate(dateGo.getTime() - 1000);
                } else {
                    datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);
                }
                datePickerDialog.show();
            }
        });

        btnAddAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumAdult,btnAddAdult,btnSubAdult,Constant.LIMIT_ADULT,true);
            }
        });

        btnSubAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumAdult,btnAddAdult,btnSubAdult,Constant.LIMIT_ADULT,false);
            }
        });

        btnAddChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumChildren,btnAddChildren,btnSubChildren,Constant.LIMIT_CHILDREN,true);
            }
        });

        btnSubChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumChildren,btnAddChildren,btnSubChildren,Constant.LIMIT_CHILDREN,false);
            }
        });

        btnAddBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumBaby,btnAddBaby,btnSubBaby,Constant.LIMIT_BABY,true);
            }
        });

        btnSubBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumPassenger(txtNumBaby,btnAddBaby,btnSubBaby,Constant.LIMIT_BABY,false);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFrom.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText(R.string.error_from);
                }
                else if (txtTo.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText(R.string.error_to);
                }
                else if (txtDateGo.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText(R.string.error_date_go);
                }
                else if (txtDateArrival.getText().toString().trim().equalsIgnoreCase("")&&isRoundTrip){
                    txtError.setText(R.string.error_date_arrival);
                }
                else if (Integer.parseInt(txtNumBaby.getText().toString())>Integer.parseInt(txtNumAdult.getText().toString())){
                    txtError.setText(R.string.error_num);
                }
                else if (txtNumAdult.getText().toString().equalsIgnoreCase("0")
                        &&txtNumChildren.getText().toString().equalsIgnoreCase("0")
                        &&txtNumBaby.getText().toString().equalsIgnoreCase("0")){
                    txtError.setText(R.string.error_num);
                }else {
                    txtError.setText("");
                    Toast.makeText(BookingActivity.this, "gui thoi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControl() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_booking);
        bottomNavigationView.setSelectedItemId(R.id.nav_booking);

        toolbar = findViewById(R.id.toolbar_booking);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerBooking);
        navigationView = findViewById(R.id.nav_view_Booking);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(BookingActivity.this, drawerLayout, toolbar, R.string.about_me, R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        txtNumAdult = findViewById(R.id.txtNumAdult);
        txtNumChildren = findViewById(R.id.txtNumChildren);
        txtNumBaby = findViewById(R.id.txtNumBaby);
        txtError = findViewById(R.id.txtErrorBooking);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        txtDateGo = findViewById(R.id.txtDateGo);
        txtDateArrival = findViewById(R.id.txtDateArrival);

        btnAddAdult = findViewById(R.id.btnAddAdult);
        btnSubAdult = findViewById(R.id.btnSubAdult);
        btnAddChildren = findViewById(R.id.btnAddChildren);
        btnSubChildren = findViewById(R.id.btnSubChildren);
        btnAddBaby = findViewById(R.id.btnAddBaby);
        btnSubBaby = findViewById(R.id.btnSubBaby);
        btnSearch = findViewById(R.id.btnSearchBooking);
        btnKhuHoi = findViewById(R.id.btnRoundTrip);
        btnMotChieu = findViewById(R.id.btnOneWay);

        layoutFrom = findViewById(R.id.layoutFrom);
        layoutTo = findViewById(R.id.layoutTo);
        layoutDateGo = findViewById(R.id.layoutDateGo);
        layoutDateArrival = findViewById(R.id.layoutDateArrival);

        Intent intentPlaceFragment = getIntent();
        txtTo.setText(intentPlaceFragment.getStringExtra("placeArrival"));
        idPlaceTo = intentPlaceFragment.getStringExtra("idPlaceArrival");

        dateGo=calendar.getTime();
        txtDateGo.setText(sdf1.format(dateGo));

        txtNumAdult.setText("1");
        checkCountPassenger(txtNumAdult.getText().toString(),btnAddAdult,btnSubAdult,Constant.LIMIT_ADULT);
        checkCountPassenger(txtNumChildren.getText().toString(),btnAddChildren,btnSubChildren,Constant.LIMIT_CHILDREN);
        checkCountPassenger(txtNumBaby.getText().toString(),btnAddBaby,btnSubBaby,Constant.LIMIT_BABY);
    }

    private void checkCountPassenger(String number_string, ImageView add,ImageView sub,int limit){
        int number = Integer.parseInt(number_string);
        if (number<=0){
            sub.setImageResource(R.drawable.ic_remove_circle_black_24dp);
        }
        else if (number>=limit){
            add.setImageResource(R.drawable.ic_add_circle_black_24dp);
        }else {
            sub.setImageResource(R.drawable.ic_remove_circle_orange_24dp);
            add.setImageResource(R.drawable.ic_add_circle_orange_24dp);
        }
    }

    private  void setNumPassenger(TextView txt,ImageView add,ImageView sub,int limit,boolean isAdd){
        int number = Integer.parseInt(txt.getText().toString());
        if (isAdd && number<limit){
            checkCountPassenger(String.valueOf(number+1),add,sub,limit);
            txt.setText(String.valueOf(number+1));
        }else if (!isAdd && number>0){
            checkCountPassenger(String.valueOf(number-1),add,sub,limit);
            txt.setText(String.valueOf(number-1));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == Constant.REQUEST_CODE_FROM && resultCode == RESULT_OK && data != null) {
            String placeFrom = data.getStringExtra("place");
            idPlaceFrom = data.getStringExtra("idplace");
            txtFrom.setText(placeFrom);
        }

        if (requestCode == Constant.REQUEST_CODE_TO && resultCode == RESULT_OK && data != null) {
            String placeFrom = data.getStringExtra("place");
            idPlaceTo = data.getStringExtra("idplace");
            txtTo.setText(placeFrom);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
