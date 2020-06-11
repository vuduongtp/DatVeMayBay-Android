package com.vuvanduong.datvemaybay.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ve implements Serializable, Parcelable {
    private String MaVe;
    private String NgayBay;
    private String SoGhe;
    private String ChuyenBay;
    private String Gia;
    private int TrangThai;
    private String NguoiDat;
    private String Email;
    private String BayTu;
    private String BayDen;
    private String GioKhoiHanh;
    private String GioKetThuc;

    public Ve() {
    }

    public Ve(String maVe, String ngayBay, String soGhe, String chuyenBay, String gia, int trangThai, String nguoiDat, String email, String bayTu, String bayDen, String gioKhoiHanh, String gioKetThuc) {
        MaVe = maVe;
        NgayBay = ngayBay;
        SoGhe = soGhe;
        ChuyenBay = chuyenBay;
        Gia = gia;
        TrangThai = trangThai;
        NguoiDat = nguoiDat;
        Email = email;
        BayTu = bayTu;
        BayDen = bayDen;
        GioKhoiHanh = gioKhoiHanh;
        GioKetThuc = gioKetThuc;
    }

    protected Ve(Parcel in) {
        MaVe = in.readString();
        NgayBay = in.readString();
        SoGhe = in.readString();
        ChuyenBay = in.readString();
        Gia = in.readString();
        TrangThai = in.readInt();
        NguoiDat = in.readString();
        Email = in.readString();
        BayTu = in.readString();
        BayDen = in.readString();
        GioKhoiHanh = in.readString();
        GioKetThuc = in.readString();
    }

    public static final Creator<Ve> CREATOR = new Creator<Ve>() {
        @Override
        public Ve createFromParcel(Parcel in) {
            return new Ve(in);
        }

        @Override
        public Ve[] newArray(int size) {
            return new Ve[size];
        }
    };

    public String getBayTu() {
        return BayTu;
    }

    public void setBayTu(String bayTu) {
        BayTu = bayTu;
    }

    public String getBayDen() {
        return BayDen;
    }

    public void setBayDen(String bayDen) {
        BayDen = bayDen;
    }

    public String getNgayBay() {
        return NgayBay;
    }

    public void setNgayBay(String ngayBay) {
        NgayBay = ngayBay;
    }

    public String getGioKhoiHanh() {
        return GioKhoiHanh;
    }

    public void setGioKhoiHanh(String gioKhoiHanh) {
        GioKhoiHanh = gioKhoiHanh;
    }

    public String getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        GioKetThuc = gioKetThuc;
    }

    public String getChuyenBay() {
        return ChuyenBay;
    }

    public void setChuyenBay(String chuyenBay) {
        ChuyenBay = chuyenBay;
    }

    public String getMaVe() {
        return MaVe;
    }

    public void setMaVe(String maVe) {
        MaVe = maVe;
    }

    public String getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(String soGhe) {
        SoGhe = soGhe;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getNguoiDat() {
        return NguoiDat;
    }

    public void setNguoiDat(String nguoiDat) {
        NguoiDat = nguoiDat;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return  " Mã đặt chỗ: " + MaVe  +
                "\n Ngày bay: " + NgayBay  +
                "\n Số ghế: " + SoGhe +
                "\n Chuyến bay: " + ChuyenBay  +
                "\n Từ: " + BayTu  +
                "\n Đến: " + BayDen  +
                "\n Họ tên: " + NguoiDat  +
                "\n Email: " + Email +
                "\n Giờ khởi hành: " + GioKhoiHanh+
                "\n Giờ kết thúc: " + GioKetThuc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MaVe);
        dest.writeString(NgayBay);
        dest.writeString(SoGhe);
        dest.writeString(ChuyenBay);
        dest.writeString(Gia);
        dest.writeInt(TrangThai);
        dest.writeString(NguoiDat);
        dest.writeString(Email);
        dest.writeString(BayTu);
        dest.writeString(BayDen);
        dest.writeString(GioKhoiHanh);
        dest.writeString(GioKetThuc);
    }
}
