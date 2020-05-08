package com.vuvanduong.datvemaybay.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.FamousPlaceAdapter;
import com.vuvanduong.datvemaybay.model.MainActivity;
import com.vuvanduong.datvemaybay.object.FamousPlace;

import java.util.ArrayList;

public class FragmentFamousPlace extends Fragment {


//    ArrayList<FamousPlace> placeList;
//    FamousPlaceAdapter adapterPlace;
//    GridView gvPlace;
    FrameLayout palaceHanoi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_famous_place,container,false);

        palaceHanoi = view.findViewById(R.id.placeHanoi);
        palaceHanoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hanoi", Toast.LENGTH_SHORT).show();
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
