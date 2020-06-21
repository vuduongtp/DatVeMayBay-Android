package com.vuvanduong.datvemaybay.message;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vuvanduong.datvemaybay.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class XemActivity extends AppCompatActivity {

    ListView lvThongBao;
    ArrayList<ThongBao> dsThongBao;
    ThongBaoAdapter thongBaoAdapter;
    ThongBaoDB thongBaoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem);

        setControl();
        setEvent();
    }

    private void setEvent() {
        lvThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XemActivity.this, NoiDungActivity.class);
                intent.putExtra("id",String.valueOf(dsThongBao.get(position).getId()));
                intent.putExtra("tieude",dsThongBao.get(position).getTieuDe());
                intent.putExtra("noidung",dsThongBao.get(position).getNoiDung());
                intent.putExtra("thoigian",dsThongBao.get(position).getNgayBatDau());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setControl() {
        lvThongBao = findViewById(R.id.lvThongBao);
        dsThongBao = new ArrayList<>();
        thongBaoAdapter = new ThongBaoAdapter(this, R.layout.thong_bao_item, dsThongBao);
        lvThongBao.setAdapter(thongBaoAdapter);

        thongBaoDB = new ThongBaoDB(this);
        Cursor cursor = thongBaoDB.getAllData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ThongBao thongBao = new ThongBao();
                thongBao.setId(cursor.getString(0));
                thongBao.setTieuDe(cursor.getString(1));
                thongBao.setNoiDung(cursor.getString(2));
                thongBao.setNgayBatDau(cursor.getString(3));
                dsThongBao.add(thongBao);
            }
            Collections.sort(dsThongBao,new Comparator<ThongBao>() {
                @Override
                public int compare(ThongBao o1, ThongBao o2) {
                    return String.valueOf(o2.getId()).compareTo(String.valueOf(o1.getId()));
                }
            });
            thongBaoAdapter.notifyDataSetChanged();
        }
    }
}
