package com.vuvanduong.datvemaybay.config;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constant {
    public static final String DOMAIN_NAME = "http://datvemaybay.somee.com/";

    private static Locale local= Locale.getDefault();
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", local);

    public static final int REQUEST_CODE_FROM = 100;
    public static final int REQUEST_CODE_TO = 101;
    public static final int LIMIT_ADULT = 3;
    public static final int LIMIT_CHILDREN = 2;
    public static final int LIMIT_BABY = 2;
}
