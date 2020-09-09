package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 26/05/2016.
 */
public class AsynTaskLayThongTinThuaDat extends AsyncTask<clsUrl, Void,clsThuaDat> {

    private static String url;

    private static final String TAG_IDTHUA ="ID";
    private static  final String TAG_SOTO="soTo";
    private static  final String TAG_SOTHUA="soThua";
    private static  final String TAG_DIENTICH ="dienTich";
    private static  final String TAG_LOAIDAT = "loaiDat";
    private static  final String TAG_TENCHU = "tenChu";
    private static final String TAG_DACAPGIAY = "daCapGiay";
    private static final String TAG_QUYHOACH = "quyHoach";
    private static final String TAG_NGUONGOC = "moTa";
    private static final String TAG_CHANGIAODICH= "biChan";

    TextView lblSoTo, lblSoThua, lblDienTich,lblLoaiDat, lblTenChu, lblTinhTrangcapGiay,lblSoNha,lblDiaChi,lblChanGD,lblNguonGoc,lblSoNHaTest;
    Activity activityCha;

    public AsynTaskLayThongTinThuaDat(Activity att)
    {
        this.activityCha = att;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected clsThuaDat doInBackground(clsUrl... params) {
        ServiceHandler sh = new ServiceHandler();
        clsUrl cls = (clsUrl)params[0];
        //url = params[0];
        url = cls.getUrl();
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
        //Log.d("Response: ", "> " + jsonStr);
        clsThuaDat thuaDat = new clsThuaDat();
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                //thuaDat.idThua = jsonObj.getString(TAG_IDTHUA);
                thuaDat.soTo = jsonObj.getString(TAG_SOTO);
                thuaDat.soThua = jsonObj.getString(TAG_SOTHUA);
                thuaDat.dienTich = cls.getDienTich();
                thuaDat.loaiDat = cls.getLoaiDat();
                thuaDat.soNha=jsonObj.getString("soNha");
                thuaDat.diaChi=jsonObj.getString("diaChi");
                Log.e("ServiceHandler", jsonObj.getString("soNha"));

                if(cls.isCoQuyen())
                {
                    thuaDat.tenChu =  jsonObj.getString(TAG_TENCHU);
                }

                else
                {
                    thuaDat.tenChu = "******";
                }

                //thuaDat.daCapGiay = jsonObj.getString(TAG_DACAPGIAY);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url"+url);}
        return thuaDat;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(clsThuaDat thuaDat) {
        super.onPostExecute(thuaDat);
        try{
            if(thuaDat == null)
                return;
            lblSoTo = (TextView) activityCha.findViewById(R.id.lblSoTo1);
            lblSoThua = (TextView) activityCha.findViewById(R.id.lblSoThua1);
            lblTenChu = (TextView) activityCha.findViewById(R.id.lblTenChu1);

            lblDienTich =(TextView)activityCha.findViewById(R.id.lblDienTich1);
            lblLoaiDat = (TextView)activityCha.findViewById(R.id.lblLoaiDat1);
       //     lblSoNha=(TextView)activityCha.findViewById(R.id.lblSoNha);
            lblDiaChi=(TextView)activityCha.findViewById(R.id.lblDiaChi);
            lblSoNHaTest=(TextView)activityCha.findViewById(R.id.lblSoNhaTest);

            lblSoTo.setText(thuaDat.soTo);
            lblSoThua.setText(thuaDat.soThua);
            lblLoaiDat.setText(thuaDat.loaiDat);
            lblTenChu.setText(thuaDat.tenChu);
            lblDienTich.setText(String.format("%sm2", thuaDat.dienTich));
            lblDiaChi.setText(thuaDat.diaChi);
            lblSoNHaTest.setText(thuaDat.soNha);

        }catch (Exception ex)
        {
            //ex.printStackTrace();
        }
    }
}
