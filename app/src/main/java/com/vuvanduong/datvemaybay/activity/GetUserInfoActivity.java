package com.vuvanduong.datvemaybay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.mail.ConfirmCodeActivity;
import com.vuvanduong.datvemaybay.mail.GMailSender;

public class GetUserInfoActivity extends AppCompatActivity {

    String maChuyenBayDi = "";
    String maChuyenBayVe = "";
    Button btnConfirm;
    EditText txtFirstName, txtLastName, txtIDNumber, txtEmail, txtPhone;
    TextView txtError;
    ProgressDialog dialog;
    String randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);
        Intent intent = getIntent();
        maChuyenBayDi = intent.getStringExtra("maChuyenBayDi");
        maChuyenBayVe = intent.getStringExtra("maChuyenBayVe");

        addControl();
        addEvent();
    }

    public static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
//        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//                + "0123456789"
//                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString

        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private void addEvent() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFirstName.getText().toString().trim().equalsIgnoreCase("")) {
                    txtError.setText("Họ không được để trống.");
                } else if (txtLastName.getText().toString().trim().equalsIgnoreCase("")) {
                    txtError.setText("Tên không được để trống.");
                } else if (txtIDNumber.getText().toString().trim().equalsIgnoreCase("")) {
                    txtError.setText("Số CMND không được để trống.");
                } else if (txtEmail.getText().toString().trim().equalsIgnoreCase("")) {
                    txtError.setText("Email không được để trống.");
                } else if (txtPhone.getText().toString().trim().equalsIgnoreCase("")) {
                    txtError.setText("Số điện thoại không được để trống.");
                } else {
                    randomNumber=getAlphaNumericString(6);
                    System.out.println("Code: "+randomNumber);
                    String tieuDe="Xác nhận đặt vé PTITAirlines";
                    String noiDung="Mã xác nhận đặt vé của bạn là: "+randomNumber;
                    dialog = ProgressDialog.show(GetUserInfoActivity.this, "",
                            "Loading...", true);

                    Bundle bundle = new Bundle();
                    bundle.putString("tieuDe", tieuDe);
                    bundle.putString("noiDung", noiDung);
                    bundle.putString("emailTo", txtEmail.getText().toString().trim());

                    guiMail myTask = new guiMail();
                    myTask.execute(bundle);
                    //Toast.makeText(GetUserInfoActivity.this, "Đặt vé thành công.", Toast.LENGTH_SHORT).show();
                    //finishAffinity();
                }
            }
        });
    }

    private void addControl() {
        btnConfirm = findViewById(R.id.btnConfirmBooking);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtIDNumber = findViewById(R.id.txtIDCode);
        txtEmail = findViewById(R.id.txtEmail1);
        txtPhone = findViewById(R.id.txtPhoneNum);
        txtError = findViewById(R.id.txtErrorUserInfo);
    }

    class guiMail extends AsyncTask<Bundle, Void, Void> {

        @Override
        protected Void doInBackground(Bundle... bundles) {
            Bundle b = bundles[0];
            String tieuDe = b.getString("tieuDe");
            String noiDung = b.getString("noiDung");
            String emailTo = b.getString("emailTo");
            //System.out.println(tieuDe+"/"+noiDung+"/"+emailTo);
            try {
                // System.out.println("chay vo day");
                GMailSender sender = new GMailSender("mailsenderptithcm@gmail.com", "ptithcm123");
                sender.sendMail(tieuDe,
                        noiDung,
                        "mailsenderptithcm@gmail.com",
                        emailTo);
                dialog.dismiss();
                Intent myIntent = new Intent(GetUserInfoActivity.this, ConfirmCodeActivity.class);
                myIntent.putExtra("code", randomNumber);
                startActivity(myIntent);
                //System.out.println("chay vo day");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                dialog.dismiss();
            }
            return null;
        }
    }
}
