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

import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.model.BookingActivity;


public class FragmentSelectFlightGo extends Fragment {

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

        return view;
    }
}
