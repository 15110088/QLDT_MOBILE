package stnmt.ttcntt.qldt_mobile;

import java.util.Date;

/**
 * Created by PTCN_08 on 6/1/2016.
 */
public class clsLichSuThuaDat implements Comparable<clsLichSuThuaDat> {

    Date ngayBienDong;
    String noiDungBienDong;

    public Date getNgayBienDong() {
        return ngayBienDong;
    }

    public void setNgayBienDong(Date ngayBienDong) {
        this.ngayBienDong = ngayBienDong;
    }

    public String getNoiDungBienDong() {
        return noiDungBienDong;
    }

    public void setNoiDungBienDong(String noiDungBienDong) {
        this.noiDungBienDong = noiDungBienDong;
    }

    public clsLichSuThuaDat() {
    }

    public clsLichSuThuaDat(Date ngayBienDong, String noiDungBienDong) {
        this.ngayBienDong = ngayBienDong;
        this.noiDungBienDong = noiDungBienDong;
    }

    @Override
    public String toString() {
        return  "Ngày thực hiện =" + ngayBienDong + ", Nội dung ='" + noiDungBienDong + '\'';
    }

    @Override
    public int compareTo(clsLichSuThuaDat another) {

        return 0;
    }
}
