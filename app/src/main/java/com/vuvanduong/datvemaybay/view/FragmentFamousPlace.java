package com.vuvanduong.datvemaybay.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.model.BookingActivity;


public class FragmentFamousPlace extends Fragment {


//    ArrayList<FamousPlace> placeList;
//    FamousPlaceAdapter adapterPlace;
//    GridView gvPlace;
    FrameLayout palaceHanoi,placeDienBien,placeHaiPhong,placeNgheAn,placeQuangBinh,placeThanhHoa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_famous_place,container,false);

        palaceHanoi = view.findViewById(R.id.placeHanoi);
        placeDienBien = view.findViewById(R.id.placeDienBien);
        placeHaiPhong = view.findViewById(R.id.placeHaiPhong);
        placeNgheAn = view.findViewById(R.id.placeNgheAn);
        placeQuangBinh = view.findViewById(R.id.placeQuangBinh);
        placeThanhHoa = view.findViewById(R.id.placeThanhHoa);

        palaceHanoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Hà Nội");
                intentSelectAirport.putExtra("idPlaceArrival","HAN");
                startActivity(intentSelectAirport);
            }
        });

        placeDienBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Điện Biên");
                intentSelectAirport.putExtra("idPlaceArrival","DIN");
                startActivity(intentSelectAirport);
            }
        });

        placeHaiPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Hải Phòng");
                intentSelectAirport.putExtra("idPlaceArrival","HPH");
                startActivity(intentSelectAirport);
            }
        });

        placeNgheAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Nghệ An");
                intentSelectAirport.putExtra("idPlaceArrival","VII");
                startActivity(intentSelectAirport);
            }
        });

        placeQuangBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Quảng Bình");
                intentSelectAirport.putExtra("idPlaceArrival","VDH");
                startActivity(intentSelectAirport);
            }
        });

        placeThanhHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelectAirport = new Intent(getActivity(), BookingActivity.class);
                intentSelectAirport.putExtra("placeArrival","Thanh Hoá");
                intentSelectAirport.putExtra("idPlaceArrival","THD");
                startActivity(intentSelectAirport);
            }
        });
//        gvPlace = view.findViewById(R.id.gvFamousPlace);
//        placeList = new ArrayList<>();
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        placeList.add(new FamousPlace("Hanoi",R.drawable.hanoi));
//        adapterPlace = new FamousPlaceAdapter(getActivity(),R.layout.gv_place_item,placeList);
//        gvPlace.setAdapter(adapterPlace);
        return view;
    }
}
