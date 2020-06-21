package com.vuvanduong.datvemaybay.message;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //Tên cơ sở dữ liệu
    public static final String TEN_DATABASE = "ThongBao";
    //Tên bảng
    public static final String TEN_BANG_DIEM_MON_HOC = "ThongBao";
    //Bảng gồm 5 cột
    public static final String COT_ID = "_id";
    public static final String COT_TIEUDE = "_title";
    public static final String COT_NOIDUNG = "_content";
    public static final String COT_TIME = "_time";

    //Lệnh tạo bảng
    private static final String TAO_BANG_DIEM_MON_HOC = ""
            + "create table " + TEN_BANG_DIEM_MON_HOC + " ( "
            + COT_ID + " text not null, "
            + COT_TIEUDE + " text not null, "
            + COT_NOIDUNG + " text not null, "
            + COT_TIME + " text not null );";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, TEN_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_DIEM_MON_HOC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

