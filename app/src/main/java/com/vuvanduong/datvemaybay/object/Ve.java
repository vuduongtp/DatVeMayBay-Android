package com.vuvanduong.datvemaybay.object;

import java.io.Serializable;

public class Ve implements Serializable {
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
}
