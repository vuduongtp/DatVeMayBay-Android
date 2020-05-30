package com.vuvanduong.datvemaybay.object;

import java.io.Serializable;

public class Ve implements Serializable {
    private String MaVe;
    private String NgayDat;
    private String SoGhe;
    private String ChuyenBay;
    private String Gia;
    private int TrangThai;
    private String NguoiDat;
    private String Email;

    public Ve() {
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

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
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
        return  " Mã vé: " + MaVe  +
                "\n Ngày đặt: " + NgayDat  +
                "\n Số ghế: " + SoGhe +
                "\n Chuyến bay: " + ChuyenBay  +
                "\n Giá: " + Gia  +
                "\n Trạng thái: " + TrangThai +
                "\n Người đặt: " + NguoiDat  +
                "\n Email: " + Email ;
    }
}
