package com.vuvanduong.datvemaybay.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.AirportAdapter;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.object.SanBay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectAirportActivity extends AppCompatActivity {

    EditText txtSearch;
    ListView lvAirport;
    ArrayList<SanBay> dsSanBay;
    AirportAdapter adapterSanBay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_airport);

        addControl();
        addEvent();
    }

    private void addEvent() {
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"]");

                adapterSanBay.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lvAirport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent();
                intent.putExtra("place",adapterSanBay.getItem(position).getThanhPho());
                intent.putExtra("idplace",adapterSanBay.getItem(position).getMaSanBay());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void addControl() {
        txtSearch = findViewById(R.id.txtSearchPlace);
        lvAirport = findViewById(R.id.lvAirport);


        dsSanBay = new ArrayList<>();
        adapterSanBay = new AirportAdapter(SelectAirportActivity.this, R.layout.airport_item, dsSanBay);
        lvAirport.setAdapter(adapterSanBay);
        getAirport getAirport = new getAirport();
        getAirport.execute();
    }

    private class getAirport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dsSanBay.clear();
            String url = Constant.DOMAIN_NAME + "api/san-bay/get-all";
            RequestQueue requestQueue = Volley.newRequestQueue(SelectAirportActivity.this);
            final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    SanBay sanBay = new SanBay();
                                    if (jsonObject.has("MaSanBay")) {
                                        sanBay.setMaSanBay(jsonObject.getString("MaSanBay"));
                                        System.out.println(sanBay.getMaSanBay());
                                    }
                                    if (jsonObject.has("TenSanBay")) {
                                        sanBay.setTenSanBay(jsonObject.getString("TenSanBay"));
                                    }
                                    if (jsonObject.has("ThanhPho")) {
                                        sanBay.setThanhPho(jsonObject.getString("ThanhPho"));
                                    }
                                    if (jsonObject.has("QuocGia")) {
                                        sanBay.setQuocGia(jsonObject.getString("QuocGia"));
                                    }
                                    if (jsonObject.has("GhiChu")) {
                                        sanBay.setGhiChu(jsonObject.getString("GhiChu"));
                                    }
                                    dsSanBay.add(sanBay);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapterSanBay.notifyDataSetChanged();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("loi", error.toString());
                        }
                    });

            requestQueue.add(jsonArrayRequest);
            return null;
        }
    }
}
