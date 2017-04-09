package trangtrunghoangphuc.model;

import java.io.Serializable;

/**
 * Created by Administrator on 11/28/2016.
 */

public class DateMoney implements Serializable {
    private int ngay;
    private int thang;
    private int nam;
    private int soTien;

    public DateMoney() {
    }

    public DateMoney(int ngay, int thang, int nam, int soTien) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.soTien = soTien;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
