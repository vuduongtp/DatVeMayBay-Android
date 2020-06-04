package com.vuvanduong.datvemaybay.mail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.activity.BookingSucessActivity;
import com.vuvanduong.datvemaybay.activity.GetUserInfoActivity;

public class ConfirmCodeActivity extends AppCompatActivity {

    EditText txtConfirmCode;
    TextView txtErrorConfirmCode;
    Button btnConfirmCode;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);
        
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtConfirmCode.getText().toString().trim().equalsIgnoreCase("")){
                    txtErrorConfirmCode.setText("Vui lòng nhập mã xác nhận.");
                }else {
                    if (txtConfirmCode.getText().toString().trim().equalsIgnoreCase(code)){
                        Intent myIntent = new Intent(ConfirmCodeActivity.this, BookingSucessActivity.class);
                        startActivity(myIntent);
                    }else {
                        txtErrorConfirmCode.setText("Mã xác nhận không đúng. Vui lòng nhập lại.");
                    }
                }
            }
        });
    }

    private void setControl() {
        txtConfirmCode=findViewById(R.id.txtConfirmCode);
        txtErrorConfirmCode=findViewById(R.id.txtErrorConfirmCode);
        btnConfirmCode=findViewById(R.id.btnConfirmCode);

        code = this.getIntent().getStringExtra("code");

    }
}
