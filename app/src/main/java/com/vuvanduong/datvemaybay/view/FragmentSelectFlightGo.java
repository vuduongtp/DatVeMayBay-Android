package com.vuvanduong.datvemaybay.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.activity.BookingActivity;
import com.vuvanduong.datvemaybay.activity.GetUserInfoActivity;
import com.vuvanduong.datvemaybay.activity.SelectFlightActivity;
import com.vuvanduong.datvemaybay.adapter.FlightAdapter;
import com.vuvanduong.datvemaybay.app.MyVolley;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.object.ChuyenBay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FragmentSelectFlightGo extends Fragment {

    ArrayList<ChuyenBay> chuyenBays;
    ArrayList<ChuyenBay> chuyenBayDi;
    String noiDi="";
    String noiDen="";
    String ngayDi="";
    String ngayVe="";
    int soLuong=0;
    String urlArrival="";
    String urlGo="";
    String maChuyenBayDi="";
    String maChuyenBayVe="";
    boolean isFromBooking=true;
    boolean isRoundTrip=false;
    FlightAdapter flightAdapter;
    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        String date = getArguments().getString("date");
//        String fromTo = getArguments().getString("fromto");
        View view = inflater.inflate(R.layout.fragment_select_flight_go,container,false);

//        TextView txtFromTo = view.findViewById(R.id.txtFromTo);
//        TextView txtDate1 = view.findViewById(R.id.txtDate1);
//        TextView txtDate2 = view.findViewById(R.id.txtDate2);
//        TextView txtDate3 = view.findViewById(R.id.txtDate3);
        ListView lvFlight = view.findViewById(R.id.lvFlight);

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("");
        dialog.setMessage(getResources().getString(R.string.please_wait));
        dialog.setCanceledOnTouchOutside(false);

        chuyenBays = new ArrayList<>();
        chuyenBays =  getArguments().getParcelableArrayList("dschuyenbay");
        //Toast.makeText(getActivity(),"soze:" + chuyenBays.size(), Toast.LENGTH_SHORT).show();
        soLuong = getArguments().getInt("soLuong",1);
        urlArrival = getArguments().getString("urlArrival");
        urlGo = getArguments().getString("urlGo");
        noiDi = getArguments().getString("noiDi");
        noiDen = getArguments().getString("noiDen");
        ngayDi = getArguments().getString("ngayDi");
        ngayVe = getArguments().getString("ngayVe");
        isFromBooking = getArguments().getBoolean("isFromBooking");
        isRoundTrip = getArguments().getBoolean("isRoundTrip");
        maChuyenBayDi = getArguments().getString("maChuyenBayDi");



//        txtFromTo.setText(noiDi + " - "+noiDen);
//        txtDate2.setText(ngayDi);
//        txtDate1.setText(strDateYesterday);
//        txtDate3.setText(strDateTomorrow);

        flightAdapter = new FlightAdapter(getActivity(),R.layout.flight_item,chuyenBays, true);
        lvFlight.setAdapter(flightAdapter);

        lvFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChuyenBay chuyenBay = chuyenBays.get(position);
                final String maCB = chuyenBay.getMaChuyenBay();
                if (soLuong>chuyenBay.getSoLuongVe()){
                    Toast.makeText(getActivity(), "Chuyến bay đã hết vé.", Toast.LENGTH_SHORT).show();
                }else {
                    if (isRoundTrip&&isFromBooking){
                        //Toast.makeText(getActivity(), "dat ve tiep", Toast.LENGTH_SHORT).show();
                        dialog.show();
                        chuyenBayDi=new ArrayList<>();
                        RequestQueue queue = MyVolley.getRequestQueue();
                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                                Request.Method.GET,
                                urlArrival,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        // Do something with response
                                        //mTextView.setText(response.toString());

                                        // Process the JSON
                                        try{
                                            // Loop through the array elements
                                            for(int i=0;i<response.length();i++){

                                                JSONObject jsonObject = response.getJSONObject(i);
                                                ChuyenBay chuyenBay = new ChuyenBay();
                                                if (jsonObject.has("macb")) {
                                                    chuyenBay.setMaChuyenBay(jsonObject.getString("macb"));
                                                    System.out.println(chuyenBay.getMaChuyenBay());
                                                }
                                                if (jsonObject.has("gioidi")&&jsonObject.has("phutdi")) {
                                                    chuyenBay.setThoiGianDiDuKien(addZeroToNumber(jsonObject.getString("gioidi"))+" : "+addZeroToNumber(jsonObject.getString("phutdi")));
                                                }
                                                if (jsonObject.has("gioden")&&jsonObject.has("phutden")) {
                                                    chuyenBay.setThoiGianDenDuKien(addZeroToNumber(jsonObject.getString("gioden"))+" : "+addZeroToNumber(jsonObject.getString("phutden")));
                                                }
                                                if (jsonObject.has("sbdi")) {
                                                    chuyenBay.setSanBayDi(jsonObject.getJSONObject("sbdi").getString("tp"));
                                                }
                                                if (jsonObject.has("sbden")) {
                                                    chuyenBay.setSanBayDen(jsonObject.getJSONObject("sbden").getString("tp"));
                                                }
                                                if (jsonObject.has("trangthai")) {
                                                    chuyenBay.setTrangThai(jsonObject.getInt("trangthai"));
                                                }
                                                if (jsonObject.has("ghichu")) {
                                                    chuyenBay.setGhiChu(jsonObject.getString("ghichu"));
                                                }
                                                if (jsonObject.has("maybay")) {
                                                    chuyenBay.setMaMayBay(jsonObject.getJSONObject("maybay").getString("mamb"));
                                                }
                                                if (jsonObject.has("giave")) {
                                                    if (jsonObject.getString("giave").equalsIgnoreCase("null")){
                                                        chuyenBay.setGiaVe(990000);
                                                    }else {
                                                        chuyenBay.setGiaVe((float) jsonObject.getInt("giave"));
                                                    }
                                                }
                                                if (jsonObject.has("ves")) {
                                                    chuyenBay.setSoLuongVe(jsonObject.getJSONArray("ves").length());
                                                }
                                                chuyenBayDi.add(chuyenBay);
                                                System.out.println("Size 2:" + chuyenBayDi.size());

                                                if (chuyenBayDi.size()==response.length() && response.length()>0) {

                                                    Intent intentSelectFlight = new Intent(getActivity(), SelectFlightActivity.class);
                                                    // System.out.println("fasdf:" + chuyenBayDi.size());
                                                    dialog.dismiss();
                                                        intentSelectFlight.putParcelableArrayListExtra("dschuyenbay", chuyenBayDi);
                                                        intentSelectFlight.putExtra("soLuong", soLuong);
                                                        intentSelectFlight.putExtra("noiDi",noiDen);
                                                        intentSelectFlight.putExtra("noiDen", noiDi);
                                                        intentSelectFlight.putExtra("ngayDi", ngayVe);
                                                        intentSelectFlight.putExtra("url_arrival", urlArrival);
                                                        intentSelectFlight.putExtra("url_go", urlGo);
                                                        intentSelectFlight.putExtra("maChuyenBayDi", maCB);
                                                        intentSelectFlight.putExtra("isFromBooking", false);
                                                        intentSelectFlight.putExtra("isRoundTrip", isRoundTrip);

                                                        if (!chuyenBayDi.isEmpty()) {
                                                            startActivity(intentSelectFlight);
                                                        }
                                                }

                                            }
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                            dialog.dismiss();
                                        }
                                    }
                                },
                                new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error){
                                        // Do something when error occurred
                                        dialog.dismiss();
                                        Toast.makeText(getActivity(), "Không tìm thấy chuyến bay.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                        );

                        // Add JsonArrayRequest to the RequestQueue
                        queue.add(jsonArrayRequest);
                    }else {
                        if (!isRoundTrip) {
                            maChuyenBayDi = chuyenBay.getMaChuyenBay();
                            //Toast.makeText(getActivity(),"Chuyen bay di "+ maChuyenBayDi, Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(getActivity(), GetUserInfoActivity.class);
                            myIntent.putExtra("maChuyenBayDi", maChuyenBayDi);
                            myIntent.putExtra("soLuong", soLuong);
                            startActivity(myIntent);
                        }
                        else {
                            maChuyenBayVe=chuyenBay.getMaChuyenBay();
                           // Toast.makeText(getActivity(),"Chuyen bay di "+ maChuyenBayDi, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getActivity(),"Chuyen bay ve "+ maChuyenBayVe, Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(getActivity(), GetUserInfoActivity.class);
                            myIntent.putExtra("maChuyenBayDi", maChuyenBayDi);
                            myIntent.putExtra("maChuyenBayVe", maChuyenBayVe);
                            myIntent.putExtra("soLuong", soLuong);
                            startActivity(myIntent);
                        }
                    }
                }
            }
        });

        return view;
    }

    private String addZeroToNumber(String input){
        if (input.length()<2){
            return "0"+input;
        }else return input;
    }
}
