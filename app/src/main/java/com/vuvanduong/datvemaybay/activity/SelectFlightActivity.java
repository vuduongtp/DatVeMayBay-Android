package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.object.ChuyenBay;
import com.vuvanduong.datvemaybay.view.FragmentSelectFlightGo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SelectFlightActivity extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<ChuyenBay> chuyenBays;
    String noiDi="";
    String noiDen="";
    String ngayDi="";
    boolean isFromBooking=true;
    boolean isRoundTrip=false;

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

        TextView txtFromTo = findViewById(R.id.txtFromTo);
        TextView txtDate1 = findViewById(R.id.txtDate1);
        TextView txtDate2 = findViewById(R.id.txtDate2);
        TextView txtDate3 = findViewById(R.id.txtDate3);

        chuyenBays = new ArrayList<>();
        chuyenBays = this.getIntent().getParcelableArrayListExtra("dschuyenbay");
        noiDi = this.getIntent().getStringExtra("noiDi");
        noiDen = this.getIntent().getStringExtra("noiDen");
        ngayDi = this.getIntent().getStringExtra("ngayDi");
        isFromBooking = this.getIntent().getBooleanExtra("isFromBooking",false);
        isRoundTrip = this.getIntent().getBooleanExtra("isRoundTrip",false);
       // Toast.makeText(this, chuyenBays.get(0).getThoiGianDenDuKien(), Toast.LENGTH_SHORT).show();
        Date dateNgayDi= null;
        try {
            dateNgayDi = Constant.sdf.parse(ngayDi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dateNgayDi);

        Date dateTommorow = new Date();
        dateTommorow.setTime(c.getTimeInMillis()+86400000+2000);
        String strDateTomorrow = Constant.sdf.format(dateTommorow);

        Date dateYesterday = new Date();
        dateYesterday.setTime(c.getTimeInMillis()-86400000+1000);
        String strDateYesterday = Constant.sdf.format(dateYesterday);

        if (!isRoundTrip&&isFromBooking) {
            txtFromTo.setText("Please select flight go.\n"+noiDi + " - " + noiDen);
        }
        txtDate2.setText(ngayDi);
        txtDate1.setText(strDateYesterday);
        txtDate3.setText(strDateTomorrow);

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
