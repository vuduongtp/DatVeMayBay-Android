package com.vuvanduong.datvemaybay.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SanBay implements Serializable, Parcelable {
    private String MaSanBay;
    private String TenSanBay;
    private String ThanhPho;
    private String QuocGia;
    private String GhiChu;

    public SanBay() {
    }

    public SanBay(String maSanBay, String tenSanBay, String thanhPho, String quocGia, String ghiChu) {
        MaSanBay = maSanBay;
        TenSanBay = tenSanBay;
        ThanhPho = thanhPho;
        QuocGia = quocGia;
        GhiChu = ghiChu;
    }

    protected SanBay(Parcel in) {
        MaSanBay = in.readString();
        TenSanBay = in.readString();
        ThanhPho = in.readString();
        QuocGia = in.readString();
        GhiChu = in.readString();
    }

    public static final Creator<SanBay> CREATOR = new Creator<SanBay>() {
        @Override
        public SanBay createFromParcel(Parcel in) {
            return new SanBay(in);
        }

        @Override
        public SanBay[] newArray(int size) {
            return new SanBay[size];
        }
    };

    public String getMaSanBay() {
        return MaSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        MaSanBay = maSanBay;
    }

    public String getTenSanBay() {
        return TenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        TenSanBay = tenSanBay;
    }

    public String getThanhPho() {
        return ThanhPho;
    }

    public void setThanhPho(String thanhPho) {
        ThanhPho = thanhPho;
    }

    public String getQuocGia() {
        return QuocGia;
    }

    public void setQuocGia(String quocGia) {
        QuocGia = quocGia;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MaSanBay);
        dest.writeString(TenSanBay);
        dest.writeString(ThanhPho);
        dest.writeString(QuocGia);
        dest.writeString(GhiChu);

    }

}
