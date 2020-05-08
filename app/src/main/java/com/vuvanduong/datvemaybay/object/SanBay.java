package com.vuvanduong.datvemaybay.object;

import java.io.Serializable;

public class SanBay implements Serializable {
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
}
