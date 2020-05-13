package com.vuvanduong.datvemaybay.object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChuyenBay implements Serializable, Parcelable {
    @SerializedName("MaChuyenBay")
    private String MaChuyenBay;

    @SerializedName("ThoiGianDiDuKien")
    private String ThoiGianDiDuKien;

    @SerializedName("ThoiGianDenDuKien")
    private String ThoiGianDenDuKien;

    @SerializedName("SanBayDi")
    private String SanBayDi;

    @SerializedName("SanBayDen")
    private String SanBayDen;

    @SerializedName("TrangThai")
    private int TrangThai;

    @SerializedName("GhiChu")
    private String GhiChu;

    @SerializedName("MaMayBay")
    private String MaMayBay;

    @SerializedName("GiaVe")
    private float GiaVe;

    public ChuyenBay() {
    }

    protected ChuyenBay(Parcel in) {
        MaChuyenBay = in.readString();
        ThoiGianDiDuKien = in.readString();
        ThoiGianDenDuKien = in.readString();
        SanBayDi = in.readString();
        SanBayDen = in.readString();
        TrangThai = in.readInt();
        GhiChu = in.readString();
        MaMayBay = in.readString();
        GiaVe = in.readFloat();
    }

    public static final Creator<ChuyenBay> CREATOR = new Creator<ChuyenBay>() {
        @Override
        public ChuyenBay createFromParcel(Parcel in) {
            return new ChuyenBay(in);
        }

        @Override
        public ChuyenBay[] newArray(int size) {
            return new ChuyenBay[size];
        }
    };

    public String getMaChuyenBay() {
        return MaChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        MaChuyenBay = maChuyenBay;
    }

    public String getThoiGianDiDuKien() {
        return ThoiGianDiDuKien;
    }

    public void setThoiGianDiDuKien(String thoiGianDiDuKien) {
        ThoiGianDiDuKien = thoiGianDiDuKien;
    }

    public String getThoiGianDenDuKien() {
        return ThoiGianDenDuKien;
    }

    public void setThoiGianDenDuKien(String thoiGianDenDuKien) {
        ThoiGianDenDuKien = thoiGianDenDuKien;
    }

    public String getSanBayDi() {
        return SanBayDi;
    }

    public void setSanBayDi(String sanBayDi) {
        SanBayDi = sanBayDi;
    }

    public String getSanBayDen() {
        return SanBayDen;
    }

    public void setSanBayDen(String sanBayDen) {
        SanBayDen = sanBayDen;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getMaMayBay() {
        return MaMayBay;
    }

    public void setMaMayBay(String maMayBay) {
        MaMayBay = maMayBay;
    }

    public float getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(float giaVe) {
        GiaVe = giaVe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MaChuyenBay);
        dest.writeString(ThoiGianDiDuKien);
        dest.writeString(ThoiGianDenDuKien);
        dest.writeString(SanBayDi);
        dest.writeString(SanBayDen);
        dest.writeInt(TrangThai);
        dest.writeString(GhiChu);
        dest.writeString(MaMayBay);
        dest.writeFloat(GiaVe);
    }
}
