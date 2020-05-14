package com.vuvanduong.datvemaybay.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.object.ChuyenBay;
import com.vuvanduong.datvemaybay.view.FragmentFamousPlace;
import com.vuvanduong.datvemaybay.view.FragmentSelectFlightGo;

import java.util.ArrayList;

public class SelectFlightActivity extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<ChuyenBay> chuyenBays;
    String noiDi="";
    String noiDen="";
    String ngayDi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight);

        addControl();
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar_flight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chuyenBays = new ArrayList<>();
        chuyenBays = this.getIntent().getParcelableArrayListExtra("dschuyenbay");
        noiDi = this.getIntent().getStringExtra("noiDi");
        noiDen = this.getIntent().getStringExtra("noiDen");
        ngayDi = this.getIntent().getStringExtra("ngayDi");
       // Toast.makeText(this, chuyenBays.get(0).getThoiGianDenDuKien(), Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("dschuyenbay", chuyenBays);
        bundle.putString("noiDi",noiDi);
        bundle.putString("noiDen",noiDen);
        bundle.putString("ngayDi",ngayDi);
        FragmentSelectFlightGo fragmentSelectFlightGo = new FragmentSelectFlightGo();
        fragmentSelectFlightGo.setArguments(bundle);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.selectFlightGo,fragmentSelectFlightGo);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
