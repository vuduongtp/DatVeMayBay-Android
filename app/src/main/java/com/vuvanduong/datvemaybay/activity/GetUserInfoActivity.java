package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.R;

public class GetUserInfoActivity extends AppCompatActivity {

    String maChuyenBayDi="";
    String maChuyenBayVe="";
    Button btnConfirm;
    EditText txtFirstName,txtLastName,txtIDNumber,txtEmail;
    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);
        Intent intent = getIntent();
        maChuyenBayDi = intent.getStringExtra("maChuyenBayDi");
        maChuyenBayVe = intent.getStringExtra("maChuyenBayVe");

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFirstName.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText("Họ không được để trống.");
                }else if (txtLastName.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText("Tên không được để trống.");
                }else if (txtIDNumber.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText("Số CMND không được để trống.");
                }else if (txtEmail.getText().toString().trim().equalsIgnoreCase("")){
                    txtError.setText("Email không được để trống.");
                }else {
                    //Toast.makeText(GetUserInfoActivity.this, "Đặt vé thành công.", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(GetUserInfoActivity.this, BookingSucessActivity.class);
                    startActivity(myIntent);
                    //finishAffinity();
                }
            }
        });
    }

    private void addControl() {
        btnConfirm= findViewById(R.id.btnConfirmBooking);
        txtFirstName=findViewById(R.id.txtFirstName);
        txtLastName=findViewById(R.id.txtLastName);
        txtIDNumber=findViewById(R.id.txtIDCode);
        txtEmail=findViewById(R.id.txtEmail1);
        txtError=findViewById(R.id.txtErrorUserInfo);
    }
}
