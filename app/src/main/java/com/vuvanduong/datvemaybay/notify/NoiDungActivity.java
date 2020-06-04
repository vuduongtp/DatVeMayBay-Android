package com.vuvanduong.datvemaybay.notify;

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

public class NoiDungActivity extends AppCompatActivity {

    TextView txtTieuDeND,txtThoiGianND,txtNoiDungND;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference notify = database.getReference("notify");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);

        txtNoiDungND =findViewById(R.id.txtNoiDungND);
        txtThoiGianND =findViewById(R.id.txtThoiGianND);
        txtTieuDeND =findViewById(R.id.txtTieuDeND);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        System.out.println(id);

        notify.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        System.out.println(dataSnapshot.toString());
                        for (DataSnapshot item : dataSnapshot.getChildren()){
                            ThongBao thongBao = item.getValue(ThongBao.class);
                            String idTB=String.valueOf(item.child("id").getValue());
                            assert thongBao != null;
                            if (idTB.equalsIgnoreCase(id)){
                                txtTieuDeND.setText(thongBao.getTieuDe());
                                txtThoiGianND.setText(thongBao.getNgayBatDau());
                                txtNoiDungND.setText(thongBao.getNoiDung());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
