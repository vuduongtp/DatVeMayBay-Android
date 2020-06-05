package com.vuvanduong.datvemaybay.mail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.activity.BookingSucessActivity;
import com.vuvanduong.datvemaybay.activity.CheckInActivity;
import com.vuvanduong.datvemaybay.activity.GetUserInfoActivity;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.object.Ve;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ConfirmCodeActivity extends AppCompatActivity {

    EditText txtConfirmCode;
    TextView txtErrorConfirmCode;
    Button btnConfirmCode;
    String code,ho,ten,SDT,email,CMND,maVe,maChuyenBayDi,maChuyenBayVe;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);
        
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtConfirmCode.getText().toString().trim().equalsIgnoreCase("")){
                    txtErrorConfirmCode.setText("Vui lòng nhập mã xác nhận.");
                }else {
                    if (txtConfirmCode.getText().toString().trim().equalsIgnoreCase(code)){
                        dialog.show();

                        HashMap<String, String> data = new HashMap<>();
                        data.put("cmnd",CMND);
                        data.put("thanhtien","990000");
                        data.put("macb",maChuyenBayDi);
                        String url = Constant.DOMAIN_NAME+ "api/ve/ticket-booking";

                        RequestQueue queue = MyVolley.getRequestQueue();
                        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data),
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Ve ve =new Ve();
                                        ve.setEmail(email);
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
                                                if (hoadon.has("thanhtien")) {
                                                    ve.setGia(hoadon.getString("thanhtien"));
                                                }
                                            }

                                            // txtTicketInfo.setText(ve.toString());
                                            //txtTicketInfo.setBackground(getResources().getDrawable(R.drawable.button_round_trip_unclick));
                                            dialog.dismiss();

                                            Intent myIntent = new Intent(ConfirmCodeActivity.this, BookingSucessActivity.class);
                                            myIntent.putExtra("ve",ve);
                                            startActivity(myIntent);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        dialog.dismiss();
                                        Toast.makeText(ConfirmCodeActivity.this, "Đã có lỗi xảy ra.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queue.add(jsonobj);

                    }else {
                        txtErrorConfirmCode.setText("Mã xác nhận không đúng. Vui lòng nhập lại.");
                    }
                }
            }
        });
    }


    private String addZeroToNumber(String input) {
        if (input.length() < 2) {
            return "0" + input;
        } else return input;
    }

    private void setControl() {
        txtConfirmCode=findViewById(R.id.txtConfirmCode);
        txtErrorConfirmCode=findViewById(R.id.txtErrorConfirmCode);
        btnConfirmCode=findViewById(R.id.btnConfirmCode);

        code = this.getIntent().getStringExtra("code");
        ho = this.getIntent().getStringExtra("ho");
        ten = this.getIntent().getStringExtra("ten");
        SDT = this.getIntent().getStringExtra("SDT");
        email = this.getIntent().getStringExtra("email");
        CMND = this.getIntent().getStringExtra("CMND");
        maChuyenBayDi = this.getIntent().getStringExtra("maChuyenBayDi");
        maChuyenBayVe = this.getIntent().getStringExtra("maChuyenBayVe");

        dialog = new ProgressDialog(ConfirmCodeActivity.this);
        dialog.setTitle("");
        dialog.setMessage(getResources().getString(R.string.please_wait));
        dialog.setCanceledOnTouchOutside(false);

    }
}
