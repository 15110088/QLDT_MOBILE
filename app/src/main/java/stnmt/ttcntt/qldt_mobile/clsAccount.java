package stnmt.ttcntt.qldt_mobile;
import android.app.Application;

/**
 * Created by Administrator on 13/07/2016.
 */
public class clsAccount extends Application {
    private String _userName;
    private String _passWord;
    private String _fullName;
    private String _kvhc;

    public String getUserName() {
        return _userName;
    }
    public void setUserName(String userName) {
        this._userName = userName;
    }

    public String getPassWord() {
        return _passWord;
    }
    public void setPassWord(String passWord) {
        this._passWord = passWord;
    }

    public String getFullName() {
        return _fullName;
    }
    public void setFullName(String fullName) {
        this._fullName = fullName;
    }

    public String getDonViHanhChinh() {
        return _kvhc;
    }
    public void setDonViHanhChinh(String kvhc) {
        this._kvhc = kvhc;
    }

    public boolean CheckUserPemission(String maXa)
    {
        try
        {
            return _kvhc.contains(maXa);
        }
        catch (Exception ex)
        {
        }
       return false;
    }
}
