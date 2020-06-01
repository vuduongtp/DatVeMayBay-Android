package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
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
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.TicketAdapter;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
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
    EditText txtMaVe;
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

        drawerLayout =findViewById(R.id.drawerCheckin);
        navigationView = findViewById(R.id.nav_view_Checkin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(CheckInActivity.this,drawerLayout,toolbar,R.string.about_me,R.string.cheap_ticket);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        txtMaVe = findViewById(R.id.txtTicketCode);
        btnCheckVe = findViewById(R.id.btnCheckInTicket);
        //txtTicketInfo = findViewById(R.id.txtTicketInfo);
        txtError = findViewById(R.id.txtErrorCheckIn);

        dialog = new ProgressDialog(CheckInActivity.this);
        dialog.setTitle(getResources().getString(R.string.loading));
        dialog.setMessage(getResources().getString(R.string.please_wait));
        dialog.setCanceledOnTouchOutside(false);

        lvTicket = findViewById(R.id.lvTicketCheckin);
        dsVe = new ArrayList<>();
        Ve ve = new Ve("V1","23/06/2020","25A","VJ001","1000000",0,"Vu Van Duong","abc","Ha Noi","Vinh","17:15","20:15");
        dsVe.add(ve);
        ticketAdapter = new TicketAdapter(CheckInActivity.this,R.layout.ticket_success_item,dsVe);
        lvTicket.setAdapter(ticketAdapter);
    }

    private void addEvent() {
        btnCheckVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mave=txtMaVe.getText().toString().trim();
                if (mave.equalsIgnoreCase("")){
                    txtError.setText("Mã vé không được để trống.");
                }
                else {
                   // txtTicketInfo.setText("");
                   // txtTicketInfo.setBackground(null);

                    dialog.show();
                    // Hide after some seconds
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    };

                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            handler.removeCallbacks(runnable);
                            Toast.makeText(CheckInActivity.this, "Không tìm thấy vé có mã " + mave + ".", Toast.LENGTH_SHORT).show();
                        }
                    });

                    handler.postDelayed(runnable, 5000);

                        String url = Constant.DOMAIN_NAME + "api/ve/get-by-id?mave=" + mave;
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
                                            if (response.has("ngaydat")) {
                                                ve.setNgayBay(response.getString("ngaydat"));
                                            }
                                            if (response.has("soghe")) {
                                                ve.setSoGhe(response.getString("soghe"));
                                            }
                                            if (response.has("gia")) {
                                                ve.setGia(response.getString("gia"));
                                            }
                                            if (response.has("trangthai")) {
                                                ve.setTrangThai(response.getInt("trangthai"));
                                            }
                                            if (response.has("chuyenbay")) {
                                                ve.setChuyenBay(response.getJSONObject("chuyenbay").getString("macb"));
                                            }
                                            if (response.has("hoadon")) {
                                                ve.setNguoiDat(response.getJSONObject("hoadon").getJSONObject("khachhang").getString("ho") + " " + response.getJSONObject("hoadon").getJSONObject("khachhang").getString("ten"));
                                                ve.setEmail(response.getJSONObject("hoadon").getJSONObject("khachhang").getString("email"));
                                            }

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

                                    }
                                }
                        );

                        queue.add(jsonObjectRequest);

                }
            }
        });

    }

}
