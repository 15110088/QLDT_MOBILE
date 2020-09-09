package stnmt.ttcntt.qldt_mobile;

/**
 * Created by Administrator on 13/07/2016.
 */
public class clsUrl {
    private String _url;
    private Boolean _coQuyen;
    private String _dienTich;
    private String _loaiDat;
    public clsUrl (String url, Boolean coQuyen, String dienTich, String loaiDat)
    {
        _url =url;
        _coQuyen =coQuyen;
        _dienTich=dienTich;
        _loaiDat=loaiDat;
    }
    public String getUrl() {
        return _url;
    }

    public String getLoaiDat() {
        return _loaiDat;
    }

    public String getDienTich() {
        return _dienTich;
    }
    public void setUrl(String url) {
        this._url = url;
    }

    public Boolean isCoQuyen() {
        return _coQuyen;
    }
    public void setQuyen(Boolean coQuyen) {
        this._coQuyen = coQuyen;
    }
}
