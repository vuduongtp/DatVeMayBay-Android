package com.vuvanduong.datvemaybay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.TicketAdapter;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.notify.XemActivity;
import com.vuvanduong.datvemaybay.object.ChuyenBay;
import com.vuvanduong.datvemaybay.object.Ve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CheckInActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText txtMaVe, txtEmail;
    Button btnCheckVe;
    TextView txtError;
    ProgressDialog dialog;
    ListView lvTicket;
    TicketAdapter ticketAdapter;
    ArrayList<Ve> dsVe;

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

        drawerLayout = findViewById(R.id.drawerCheckin);
        navigationView = findViewById(R.id.nav_view_Checkin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(CheckInActivity.this, drawerLayout, toolbar, R.string.about_me, R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        txtMaVe = findViewById(R.id.txtTicketCode);
        txtEmail = findViewById(R.id.txtEmailCheckin);
        btnCheckVe = findViewById(R.id.btnCheckInTicket);
        //txtTicketInfo = findViewById(R.id.txtTicketInfo);
        txtError = findViewById(R.id.txtErrorCheckIn);

        dialog = new ProgressDialog(CheckInActivity.this);
        dialog.setTitle("");
        dialog.setMessage(getResources().getString(R.string.please_wait));
        dialog.setCanceledOnTouchOutside(false);

        lvTicket = findViewById(R.id.lvTicketCheckin);
        dsVe = new ArrayList<>();
        //Ve ve = new Ve("V1","23/06/2020","25A","VJ001","1000000",0,"Vu Van Duong","abc","Ha Noi","Vinh","17:15","20:15");
        //dsVe.add(ve);
        ticketAdapter = new TicketAdapter(CheckInActivity.this, R.layout.ticket_success_item, dsVe);
        lvTicket.setAdapter(ticketAdapter);
    }

    private String addZeroToNumber(String input) {
        if (input.length() < 2) {
            return "0" + input;
        } else return input;
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
                        Intent intentHome = new Intent(CheckInActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        finish();
                        break;

                    case R.id.menu_notify:
                        Intent menu_notify = new Intent(CheckInActivity.this, XemActivity.class);
                        startActivity(menu_notify);
                        finish();
                        break;

                    case R.id.menu_checkin:
                        Intent menu_checkin = new Intent(CheckInActivity.this, CheckInActivity.class);
                        startActivity(menu_checkin);
                        break;

                    case R.id.menu_my_trip:
                        Intent menu_my_trip = new Intent(CheckInActivity.this, FlightActivity.class);
                        startActivity(menu_my_trip);
                        finish();
                        break;
                }
                return true;
            }
        });

        btnCheckVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsVe.clear();
                final String mave = txtMaVe.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                if (mave.equalsIgnoreCase("")) {
                    txtError.setText("Mã vé không được để trống.");
                } else if (email.equalsIgnoreCase("")) {
                    txtError.setText("Email không được để trống.");
                } else {
                    // txtTicketInfo.setText("");
                    // txtTicketInfo.setBackground(null);

                    dialog.show();
                    // Hide after some seconds
//                    final Handler handler = new Handler();
//                    final Runnable runnable = new Runnable() {
//                        @Override
//                        public void run() {
//                            if (dialog.isShowing()) {
//                                dialog.dismiss();
//                            }
//                        }
//                    };
//
//                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                            handler.removeCallbacks(runnable);
//                        }
//                    });
//
//                    handler.postDelayed(runnable, 5000);

                    String url = Constant.DOMAIN_NAME + "api/ve/look-up-ticket?mave=" + mave + "&email=" + email;
                    final Ve ve = new Ve();
                    RequestQueue queue = MyVolley.getRequestQueue();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            url,
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if (response.has("mave")) {
                                            ve.setMaVe(response.getString("mave"));
                                        }
                                        if (response.has("soghe")) {
                                            ve.setSoGhe(response.getString("soghe"));
                                        }
                                        if (response.has("chuyenbay")) {
                                            JSONObject chuyenbay = response.getJSONObject("chuyenbay");
                                            if (chuyenbay.has("macb")) {
                                                ve.setChuyenBay(chuyenbay.getString("macb"));
                                                ve.setNgayBay(addZeroToNumber(chuyenbay.getString("ngaydi")) + "/" + addZeroToNumber(chuyenbay.getString("thangdi")) + "/" + addZeroToNumber(chuyenbay.getString("namdi")));
                                                ve.setGioKhoiHanh(addZeroToNumber(chuyenbay.getString("gioidi")) + ":" + addZeroToNumber(chuyenbay.getString("phutdi")));
                                                ve.setGioKetThuc(addZeroToNumber(chuyenbay.getString("gioden")) + ":" + addZeroToNumber(chuyenbay.getString("phutden")));
                                            }
                                            if (chuyenbay.has("sbdi")) {
                                                JSONObject sbdi = chuyenbay.getJSONObject("sbdi");
                                                ve.setBayTu(sbdi.getString("tp"));
                                            }
                                            if (chuyenbay.has("sbden")) {
                                                JSONObject sbden = chuyenbay.getJSONObject("sbden");
                                                ve.setBayDen(sbden.getString("tp"));
                                            }
                                        }
                                        if (response.has("hoadon")) {
                                            JSONObject hoadon = response.getJSONObject("hoadon");
                                            if (hoadon.has("khachhang")) {
                                                JSONObject khachhang = hoadon.getJSONObject("khachhang");
                                                ve.setNguoiDat(khachhang.getString("ho") + " " + khachhang.getString("ten"));
                                            }
                                        }
                                        dsVe.add(ve);
                                        ticketAdapter.notifyDataSetChanged();

                                        // txtTicketInfo.setText(ve.toString());
                                        //txtTicketInfo.setBackground(getResources().getDrawable(R.drawable.button_round_trip_unclick));
                                        dialog.dismiss();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Do something when error occurred
                                    Toast.makeText(CheckInActivity.this, "Không tìm thấy vé có mã " + mave + ".", Toast.LENGTH_SHORT).show();

                                }
                            }
                    );

                    queue.add(jsonObjectRequest);

                }
            }
        });

    }

}
