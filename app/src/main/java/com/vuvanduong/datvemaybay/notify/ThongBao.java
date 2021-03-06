package com.vuvanduong.datvemaybay.notify;

import java.io.Serializable;

public class ThongBao implements Serializable {
    long Id;
    String TieuDe;
    String NoiDung;
    String NgayBatDau;
    String Status;

    public ThongBao() {
    }

    public ThongBao(long id, String tieuDe, String noiDung, String ngayBatDau, String status) {
        Id = id;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        NgayBatDau = ngayBatDau;
        Status = status;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "Id=" + Id +
                ", TieuDe='" + TieuDe + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", NgayBatDau='" + NgayBatDau + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
