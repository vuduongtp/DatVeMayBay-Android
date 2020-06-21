package com.vuvanduong.datvemaybay.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.activity.MainActivity;
import com.vuvanduong.datvemaybay.config.LocaleHelper;

import java.util.Locale;

public class DialogLanguage extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public RadioButton vn, en;
    Button cancel,save;
    String locale= "";

    public DialogLanguage(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.language_dialog);
        vn = findViewById(R.id.rbtnVN);
        en = findViewById(R.id.rbtnEN);
        cancel = findViewById(R.id.btnDialogCancel);
        save = findViewById(R.id.btnDialogOK);
        vn.setOnClickListener(this);
        en.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbtnVN:
                Toast.makeText(c, "VN", Toast.LENGTH_SHORT).show();
                locale="vi";
                break;
            case R.id.rbtnEN:
                Toast.makeText(c, "EN", Toast.LENGTH_SHORT).show();
                locale="en";
                break;
            case R.id.btnDialogCancel:
                dismiss();
                break;
            case R.id.btnDialogOK:
                LocaleHelper.onAttach(c, locale);

                Intent refresh = new Intent(c, MainActivity.class);
                c.finish();
                c.startActivity(refresh);
                Toast.makeText(c, "lang "+locale, Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            default:
                break;
        }
    }

 //   private void setLocale(String local){
//        Locale locale = new Locale(local);
//        Locale.setDefault(locale);
//        Configuration config = c.getBaseContext().getResources().getConfiguration();
//        config.locale = locale;
//        Locale.setDefault(locale);
//        config.setLocale(locale);
//        Toast.makeText(c, "set ok "+local, Toast.LENGTH_SHORT).show();
//        c.getBaseContext().getResources().updateConfiguration(config,
//                c.getBaseContext().getResources().getDisplayMetrics());
 //   }


}
