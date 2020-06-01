package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.TicketAdapter;
import com.vuvanduong.datvemaybay.object.Ve;

import java.util.ArrayList;

public class BookingSucessActivity extends AppCompatActivity {

    ListView lvTicket;
    TicketAdapter ticketAdapter;
    ArrayList<Ve> dsVe;
    Button btnReturnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_sucess);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(GetUserInfoActivity.this, "Đặt vé thành công.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(BookingSucessActivity.this, MainActivity.class);
                startActivity(myIntent);
                finishAffinity();
            }
        });
    }

    private void setControl() {
        lvTicket = findViewById(R.id.lvTicket);
        btnReturnHome = findViewById(R.id.btnReturnHome);
        dsVe = new ArrayList<>();
        Ve ve = new Ve("V1","23/06/2020","25A","VJ001","1000000",0,"Vu Van Duong","abc","Ha Noi","Vinh","17:15","20:15");
        dsVe.add(ve);
        ticketAdapter = new TicketAdapter(BookingSucessActivity.this,R.layout.ticket_success_item,dsVe);
        lvTicket.setAdapter(ticketAdapter);
    }
}
