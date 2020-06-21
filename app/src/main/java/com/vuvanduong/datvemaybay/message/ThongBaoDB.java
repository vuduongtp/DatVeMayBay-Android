package com.vuvanduong.datvemaybay.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ThongBaoDB {
    SQLiteDatabase database;
    MyDatabaseHelper dbHelper;

    public ThongBaoDB(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public long them(ThongBao diemMonHoc) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COT_ID, diemMonHoc.getId());
        values.put(dbHelper.COT_TIEUDE, diemMonHoc.getTieuDe());
        values.put(dbHelper.COT_NOIDUNG, diemMonHoc.getNoiDung());
        values.put(dbHelper.COT_TIME, diemMonHoc.getNgayBatDau());
        return database.insert(dbHelper.TEN_BANG_DIEM_MON_HOC, null, values);
    }

    public long xoa() {
        return database.delete(dbHelper.TEN_BANG_DIEM_MON_HOC,
                dbHelper.COT_ID + " >= 0" , null);
    }

//    public long sua(DiemMonHoc diemMonHoc) {
//        ContentValues values = new ContentValues();
//        values.put(dbHelper.COT_DIEM, diemMonHoc.getDiem());
//        values.put(dbHelper.COT_NGAY, diemMonHoc.getDay());
//        values.put(dbHelper.COT_SINHVIEN, diemMonHoc.getSinhVien());
//        values.put(dbHelper.COT_MONHOC, diemMonHoc.getMonHoc());
//        return  database.update(dbHelper.TEN_BANG_DIEM_MON_HOC, values,
//                dbHelper.COT_ID + " = " + diemMonHoc.getId(), null);
//    }

    public Cursor getAllData() {
        String[] cot = {dbHelper.COT_ID,
                dbHelper.COT_TIEUDE,
                dbHelper.COT_NOIDUNG,
                dbHelper.COT_TIME};
        Cursor cursor = null;
        cursor = database.query(dbHelper.TEN_BANG_DIEM_MON_HOC, cot, null, null, null, null,
                dbHelper.COT_ID + " DESC");
        return cursor;
    }
}

