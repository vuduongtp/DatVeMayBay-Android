package com.vuvanduong.datvemaybay.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.navigation.NavigationView;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.FlightAdapter;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.notify.XemActivity;
import com.vuvanduong.datvemaybay.object.ChuyenBay;
import com.vuvanduong.datvemaybay.object.SanBay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ListView lvFlight;
    LinearLayout from, to, date;
    TextView txtFrom, txtTo, txtDate;
    Button btnCheck;

    ArrayList<SanBay> dsSanBay;
    Calendar calendar = Calendar.getInstance();
    Date dateGo = null;
    String idPlaceFrom = "";
    String idPlaceTo = "";

    ProgressDialog dialog;
    ArrayList<ChuyenBay> chuyenBayDi;
    FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        addControl();
        addEvent();
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar_status_flight);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerFlight);
        navigationView = findViewById(R.id.nav_view_StatusFlight);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(FlightActivity.this, drawerLayout, toolbar, R.string.about_me, R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        dialog = new ProgressDialog(FlightActivity.this);
        dialog.setTitle("");
        dialog.setMessage(getResources().getString(R.string.please_wait));
        dialog.setCanceledOnTouchOutside(false);

        from = findViewById(R.id.layoutFromStatusFlight);
        to = findViewById(R.id.layoutToStatusFlight);
        date = findViewById(R.id.layoutDateGoStatusFlight);

        txtFrom = findViewById(R.id.txtFromStatusFlight);
        txtTo = findViewById(R.id.txtToStatusFlight);
        txtDate = findViewById(R.id.txtDateGoStatusFlight);
        btnCheck = findViewById(R.id.btnCheckStatusFlight);
        lvFlight = findViewById(R.id.lvStatusFlight);
        dsSanBay = new ArrayList<>();
        chuyenBayDi=new ArrayList<>();
        flightAdapter = new FlightAdapter(FlightActivity.this,R.layout.flight_item,chuyenBayDi,false);
        lvFlight.setAdapter(flightAdapter);

        getAirportStatus getAirport = new getAirportStatus();
        getAirport.execute();
    }

    private void addEvent() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_booking:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_home:
                        Intent intentHome = new Intent(FlightActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        finish();
                        break;

                    case R.id.menu_notify:
                        Intent menu_notify = new Intent(FlightActivity.this, XemActivity.class);
                        startActivity(menu_notify);
                        break;

                    case R.id.menu_checkin:
                        Intent menu_checkin = new Intent(FlightActivity.this, CheckInActivity.class);
                        startActivity(menu_checkin);
                        finish();
                        break;

                    case R.id.menu_my_trip:
                        Intent menu_my_trip = new Intent(FlightActivity.this, FlightActivity.class);
                        startActivity(menu_my_trip);
                        finish();
                        break;
                }
                return true;
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(FlightActivity.this, SelectAirportActivity.class);
                intentSelectAirport.putParcelableArrayListExtra("dssanbay", dsSanBay);
                startActivityForResult(intentSelectAirport, Constant.REQUEST_CODE_FROM);
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(FlightActivity.this, SelectAirportActivity.class);
                intentSelectAirport.putParcelableArrayListExtra("dssanbay", dsSanBay);
                startActivityForResult(intentSelectAirport, Constant.REQUEST_CODE_TO);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dateGo = calendar.getTime();
                        txtDate.setText(Constant.sdf.format(calendar.getTime()));
                        calendar = Calendar.getInstance();
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(FlightActivity.this, callBack,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);

                datePickerDialog.show();
            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_demo = Constant.DOMAIN_NAME+ "api/chuyen-bay/get-by-query?ngayDi=" + getNumberOfDate(txtDate.getText().toString()) + "&diemDi=" + idPlaceFrom + "&diemDen=" + idPlaceTo;
                System.out.println(url_demo);
                dialog.show();
                chuyenBayDi.clear();
                RequestQueue queue = MyVolley.getRequestQueue();
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url_demo,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                try{
                                    // Loop through the array elements
                                    for(int i=0;i<response.length();i++){

                                        JSONObject jsonObject = response.getJSONObject(i);
                                        ChuyenBay chuyenBay = new ChuyenBay();
                                        if (jsonObject.has("macb")) {
                                            chuyenBay.setMaChuyenBay(jsonObject.getString("macb"));
                                            System.out.println(chuyenBay.getMaChuyenBay());
                                        }
                                        if (jsonObject.has("gioidi")&&jsonObject.has("phutdi")) {
                                            chuyenBay.setThoiGianDiDuKien(addZeroToNumber(jsonObject.getString("gioidi"))+" : "+addZeroToNumber(jsonObject.getString("phutdi")));
                                        }
                                        if (jsonObject.has("gioden")&&jsonObject.has("phutden")) {
                                            chuyenBay.setThoiGianDenDuKien(addZeroToNumber(jsonObject.getString("gioden"))+" : "+addZeroToNumber(jsonObject.getString("phutden")));
                                        }
                                        if (jsonObject.has("sbdi")) {
                                            chuyenBay.setSanBayDi(jsonObject.getJSONObject("sbdi").getString("tp"));
                                        }
                                        if (jsonObject.has("sbden")) {
                                            chuyenBay.setSanBayDen(jsonObject.getJSONObject("sbden").getString("tp"));
                                        }
                                        if (jsonObject.has("trangthai")) {
                                            chuyenBay.setTrangThai(jsonObject.getInt("trangthai"));
                                        }
                                        if (jsonObject.has("ghichu")) {
                                            chuyenBay.setGhiChu(jsonObject.getString("ghichu"));
                                        }
                                        if (jsonObject.has("maybay")) {
                                            chuyenBay.setMaMayBay(jsonObject.getJSONObject("maybay").getString("mamb"));
                                        }
                                        if (jsonObject.has("giave")) {
                                            if (jsonObject.getString("giave").equalsIgnoreCase("null")){
                                                chuyenBay.setGiaVe(990000);
                                            }else {
                                                chuyenBay.setGiaVe((float) jsonObject.getInt("giave"));
                                            }
                                        }
                                        if (jsonObject.has("ves")) {
                                            chuyenBay.setSoLuongVe(jsonObject.getJSONArray("ves").length());
                                        }
                                        chuyenBayDi.add(chuyenBay);
                                        System.out.println("Size 2:" + chuyenBayDi.size());

                                        if (chuyenBayDi.size()==response.length() && response.length()>0) {
                                            dialog.dismiss();


                                            if (!chuyenBayDi.isEmpty()) {
                                               flightAdapter.notifyDataSetChanged();
                                            }
                                        }

                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                    dialog.dismiss();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                dialog.dismiss();

                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                queue.add(jsonArrayRequest);
            }
        });
    }

    private String addZeroToNumber(String input){
        if (input.length()<2){
            return "0"+input;
        }else return input;
    }

    private String getNumberOfDate(String date){
        String[] date_cut=date.split("/");
        StringBuilder result = new StringBuilder();
        for (String s : date_cut) {
            result.append(s);
        }
        return result.toString();
    }

    private class getAirportStatus extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            RequestQueue queue = MyVolley.getRequestQueue();
            dsSanBay.clear();
            String url = Constant.DOMAIN_NAME + "api/san-bay/get-all";
            final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    SanBay sanBay = new SanBay();
                                    if (jsonObject.has("masb")) {
                                        sanBay.setMaSanBay(jsonObject.getString("masb"));
                                        System.out.println(sanBay.getMaSanBay());
                                    }
                                    if (jsonObject.has("tensb")) {
                                        sanBay.setTenSanBay(jsonObject.getString("tensb"));
                                    }
                                    if (jsonObject.has("tp")) {
                                        sanBay.setThanhPho(jsonObject.getString("tp"));
                                    }
                                    if (jsonObject.has("quocgia")) {
                                        sanBay.setQuocGia(jsonObject.getString("quocgia"));
                                    }
                                    if (jsonObject.has("ghichu")) {
                                        sanBay.setGhiChu(jsonObject.getString("ghichu"));
                                    }
                                    dsSanBay.add(sanBay);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("loi", error.toString());
                        }
                    });

            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(jsonArrayRequest);
            return null;
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
