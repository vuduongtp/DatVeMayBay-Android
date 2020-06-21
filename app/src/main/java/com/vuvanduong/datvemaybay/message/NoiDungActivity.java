package com.vuvanduong.datvemaybay.message;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vuvanduong.datvemaybay.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoiDungActivity extends AppCompatActivity {

    TextView txtTieuDeND,txtThoiGianND,txtNoiDungND;
    String tieude,noidung,thoigian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);

        txtNoiDungND =findViewById(R.id.txtNoiDungND);
        txtThoiGianND =findViewById(R.id.txtThoiGianND);
        txtTieuDeND =findViewById(R.id.txtTieuDeND);

        Intent intent = getIntent();
       // final String id = intent.getStringExtra("id");
        tieude = intent.getStringExtra("tieude");
        noidung = intent.getStringExtra("noidung");
        thoigian = intent.getStringExtra("thoigian");
       // System.out.println(id);


        txtTieuDeND.setText(tieude);
        txtNoiDungND.setText(noidung);
        txtThoiGianND.setText(thoigian);



    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
