package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 7/4/2016.
 */

public class AsynTaskTaiSan extends AsyncTask<String, Void, ArrayList<clsTaiSan>> {
    Activity activityCha;
    String url;

    private static final String TAG_TEN ="ten";
    private static final String TAG_MOTA="moTa";

    public AsynTaskTaiSan(Activity att) {this.activityCha =att; }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<clsTaiSan> doInBackground(String... params) {
        ArrayList<clsTaiSan> arrTaiSan = new ArrayList<clsTaiSan>();
        try{

            url= params[0];
            ServiceHandler sh = new ServiceHandler();
            String strJson = sh.makeServiceCall(url,ServiceHandler.GET);
            if(strJson !=null)
            {
                JSONArray jsonArray = new JSONArray(strJson);
                if(jsonArray.length() > 0)
                {
                    JSONObject jsonObj;
                    clsTaiSan taiSan;
                    for (int i=0; i< jsonArray.length();i++) {
                        jsonObj = jsonArray.getJSONObject(i);
                        taiSan = new clsTaiSan();
                        taiSan.ten = jsonObj.getString(TAG_TEN);
                        taiSan.moTa = jsonObj.getString(TAG_MOTA);
                        arrTaiSan.add(taiSan);
                    }
                }
            }
            return  arrTaiSan;
        }catch (Exception ex){
            //ex.printStackTrace();
            Toast.makeText(activityCha, ex.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<clsTaiSan> taiSanArr) {
        super.onPostExecute(taiSanArr);
        try {
            if (taiSanArr == null || taiSanArr.isEmpty()) {
                TextView txtThongBaoTaiSan = (TextView)activityCha.findViewById(R.id.txtThongBaoTaiSan);
                txtThongBaoTaiSan.setText("Không có thông tin tài sản.");
                return;
            }
            CustomListViewTaiSan adapter = new CustomListViewTaiSan(activityCha, R.layout.row_item_tai_san, taiSanArr);
            ListView lst = (ListView) activityCha.findViewById(R.id.lvTaiSanThuaDat);
            lst.setAdapter(adapter);
            super.onPreExecute();
            
        }catch(Exception ex){
                //ex.printStackTrace();
         }


    }
}

