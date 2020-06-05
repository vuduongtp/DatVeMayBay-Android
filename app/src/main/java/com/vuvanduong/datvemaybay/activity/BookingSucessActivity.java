package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.adapter.TicketAdapter;
import com.vuvanduong.datvemaybay.mail.GMailSender;
import com.vuvanduong.datvemaybay.object.Ve;

import java.util.ArrayList;

public class BookingSucessActivity extends AppCompatActivity {

    ListView lvTicket;
    TicketAdapter ticketAdapter;
    ArrayList<Ve> dsVe;
    Button btnReturnHome;
    TextView txtSumMoney;
    ProgressDialog dialog;
    Ve ve;

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

    class guiMail extends AsyncTask<Bundle, Void, Void> {

        @Override
        protected Void doInBackground(Bundle... bundles) {
            Bundle b = bundles[0];
            String tieuDe = b.getString("tieuDe");
            String noiDung = b.getString("noiDung");
            String email = b.getString("email");
            //System.out.println(tieuDe+"/"+noiDung+"/"+emailTo);
            try {
                // System.out.println("chay vo day");
                GMailSender sender = new GMailSender("mailsenderptithcm@gmail.com", "ptithcm123");
                sender.sendMail(tieuDe,
                        noiDung,
                        "mailsenderptithcm@gmail.com",
                        email);
                dialog.dismiss();
                //System.out.println("chay vo day");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(BookingSucessActivity.this, MainActivity.class);
        startActivity(myIntent);
        finishAffinity();
    }

    private void setControl() {
        lvTicket = findViewById(R.id.lvTicket);
        btnReturnHome = findViewById(R.id.btnReturnHome);
        txtSumMoney = findViewById(R.id.txtSumMoney);
        dsVe = new ArrayList<>();
        //Ve ve = new Ve("V1","23/06/2020","25A","VJ001","1000000",0,"Vu Van Duong","abc","Ha Noi","Vinh","17:15","20:15");
        ve = (Ve) this.getIntent().getSerializableExtra("ve");
        dsVe.add(ve);
        ticketAdapter = new TicketAdapter(BookingSucessActivity.this,R.layout.ticket_success_item,dsVe);
        lvTicket.setAdapter(ticketAdapter);
        txtSumMoney.setText(ve.getGia()+" đ");


        dialog = ProgressDialog.show(BookingSucessActivity.this, "",
                "Loading...", true);
        String tieuDe="Đặt vé thành công PTITAirlines";
        String noiDung="\tThông tin vé của bạn:\n\n"+ve.toString();

        Bundle bundle = new Bundle();
        bundle.putString("tieuDe", tieuDe);
        bundle.putString("noiDung", noiDung);
        bundle.putString("email", ve.getEmail());

        guiMail myTask = new guiMail();
        myTask.execute(bundle);
    }
}
