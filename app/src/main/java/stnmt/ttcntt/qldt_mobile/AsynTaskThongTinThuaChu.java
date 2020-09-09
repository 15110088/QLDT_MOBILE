package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 7/4/2016.
 */

public class AsynTaskThongTinThuaChu extends AsyncTask<String, Void, ArrayList<clsThongTinThuaChu>> {
    Activity activityCha;
    String url;

    private static final String TAG_SO_TO ="soTo";
    private static final String TAG_SO_THUA="soThua";
    private static final String TAG_TENCHU="tenChu";
    private static final String TAG_SO_GIAYTO="soGiayTo";
    private static final String TAG_DIACHI="diaChi";
    private static final String TAG_SONHA="soNha";

    ProgressDialog dialog;

    public AsynTaskThongTinThuaChu(Activity att) {this.activityCha =att; }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activityCha,"","Chương trình đang xử lý. Vui lòng chờ...");
        dialog.setCancelable(false);
    }

    @Override
    protected ArrayList<clsThongTinThuaChu> doInBackground(String... params) {
        try{
            ArrayList<clsThongTinThuaChu> arrThuaChu = new ArrayList<clsThongTinThuaChu>();
            url= params[0];
            ServiceHandler sh = new ServiceHandler();
            String strJson = sh.makeServiceCall(url,ServiceHandler.GET);
            if(strJson !=null)
            {
                JSONArray jsonArray = new JSONArray(strJson);
                if(jsonArray.length() > 0)
                {
                    JSONObject jsonObj;
                    clsThongTinThuaChu thuaChu;
                    for (int i=0; i< jsonArray.length();i++) {
                        jsonObj = jsonArray.getJSONObject(i);
                        thuaChu = new clsThongTinThuaChu();
                        thuaChu.soNha=jsonObj.getString(TAG_SONHA);

                        thuaChu.soTo = jsonObj.getString(TAG_SO_TO);
                        thuaChu.soThua = jsonObj.getString(TAG_SO_THUA);
                        thuaChu.tenChu = jsonObj.getString(TAG_TENCHU);
                        //thuaChu.soGiayTo = jsonObj.getString(TAG_SO_GIAYTO);
                        thuaChu.diaChi = jsonObj.getString(TAG_DIACHI);

                        arrThuaChu.add(thuaChu);
                    }
                }
            }
            return  arrThuaChu;
        }catch (Exception ex){ex.toString();}
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<clsThongTinThuaChu> clsThongTinThuaChus) {
        super.onPostExecute(clsThongTinThuaChus);
        try {
            if (clsThongTinThuaChus == null) {
                Toast.makeText(activityCha, "Loi du lieu null", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                super.onPreExecute();
                return;
            }

            CustomListViewThongTinChuThua adapter = new CustomListViewThongTinChuThua(activityCha, R.layout.row_item_thuachu, clsThongTinThuaChus);
            ListView lst = (ListView) activityCha.findViewById(R.id.lvDanhSachThuaChu);
            lst.setAdapter(adapter);
            dialog.dismiss();
            super.onPreExecute();
            
        }catch(Exception ex){
                //ex.printStackTrace();
         }


    }
}

