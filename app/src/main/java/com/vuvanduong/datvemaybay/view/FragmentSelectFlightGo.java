package com.vuvanduong.datvemaybay.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.FlightAdapter;
import com.vuvanduong.datvemaybay.model.BookingActivity;
import com.vuvanduong.datvemaybay.object.ChuyenBay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FragmentSelectFlightGo extends Fragment {

    ArrayList<ChuyenBay> chuyenBays;
    String noiDi="";
    String noiDen="";
    String ngayDi="";
    FlightAdapter flightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String date = getArguments().getString("date");
        String fromTo = getArguments().getString("fromto");
        View view = inflater.inflate(R.layout.fragment_select_flight_go,container,false);

        TextView txtFromTo = view.findViewById(R.id.txtFromTo);
        TextView txtDate1 = view.findViewById(R.id.txtDate1);
        TextView txtDate2 = view.findViewById(R.id.txtDate2);
        TextView txtDate3 = view.findViewById(R.id.txtDate3);
        ListView lvFlight = view.findViewById(R.id.lvFlight);

        chuyenBays = new ArrayList<>();
        chuyenBays =  getArguments().getParcelableArrayList("dschuyenbay");
        //Toast.makeText(getActivity(),"soze:" + chuyenBays.size(), Toast.LENGTH_SHORT).show();
        noiDi = getArguments().getString("noiDi");
        noiDen = getArguments().getString("noiDen");
        ngayDi = getArguments().getString("ngayDi");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNgayDi= null;
        try {
            dateNgayDi = dateFormat.parse(ngayDi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dateNgayDi);

        Date dateTommorow = new Date();
        dateTommorow.setTime(c.getTimeInMillis()+86400000+2000);
        String strDateTomorrow = dateFormat.format(dateTommorow);

        Date dateYesterday = new Date();
        dateYesterday.setTime(c.getTimeInMillis()-86400000+1000);
        String strDateYesterday = dateFormat.format(dateYesterday);

        txtFromTo.setText(noiDi + " - "+noiDen);
        txtDate2.setText(ngayDi);
        txtDate1.setText(strDateYesterday);
        txtDate3.setText(strDateTomorrow);

        flightAdapter = new FlightAdapter(getActivity(),R.layout.flight_item,chuyenBays);
        lvFlight.setAdapter(flightAdapter);

        return view;
    }
}
