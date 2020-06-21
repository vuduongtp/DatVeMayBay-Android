package com.vuvanduong.datvemaybay.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.activity.MainActivity;
import com.vuvanduong.datvemaybay.config.Constant;
import com.vuvanduong.datvemaybay.config.SharedPrefs;

import java.util.Locale;

public class DialogLanguage extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public RadioButton vn, en;
    Button cancel,save;
    String locale= "",country="";

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
                //Toast.makeText(c, "VN", Toast.LENGTH_SHORT).show();
                locale="vi";
                country="VN";
                break;
            case R.id.rbtnEN:
                //Toast.makeText(c, "EN", Toast.LENGTH_SHORT).show();
                locale="en";
                country="EN";
                break;
            case R.id.btnDialogCancel:
                dismiss();
                break;
            case R.id.btnDialogOK:
                if(locale.equalsIgnoreCase("")){
                    Toast.makeText(c, R.string.alert_select_language, Toast.LENGTH_SHORT).show();
                }else {
                    setLanguage(locale);
                    SharedPrefs.getInstance().clear();
                    SharedPrefs.getInstance().put(Constant.LANGUAGE_CODE, locale);
                    Intent refresh = new Intent(c, MainActivity.class);
                    c.finish();
                    c.startActivity(refresh);
                    Toast.makeText(c, R.string.success_language, Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            default:
                break;
        }
    }
    private void setLanguage(String local)  {
        Resources res = c.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(local.toLowerCase(),country)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }
}
