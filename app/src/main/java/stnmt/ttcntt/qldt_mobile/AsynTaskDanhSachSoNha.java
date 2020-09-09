package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 7/4/2016.
 */

public class AsynTaskDanhSachSoNha extends AsyncTask<String, Void, ArrayList<String>> {
    Activity activityCha;
    String url;

    ProgressDialog dialog;

    public AsynTaskDanhSachSoNha(Activity att) {
        this.activityCha = att;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activityCha, "", "Chương trình đang xử lý. Vui lòng chờ...");
        dialog.setCancelable(false);
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        try {
            ArrayList<String> arrSoNha = new ArrayList<String>();
            url = params[0];
            ServiceHandler sh = new ServiceHandler();
            String strJson = sh.makeServiceCall(url, ServiceHandler.GET);
            if (strJson != null) {
                JSONArray jsonArray = new JSONArray(strJson);
                if (jsonArray.length() > 0) {
                    JSONObject jsonObj;
                    String sonhaFull;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObj = jsonArray.getJSONObject(i);
                        String sonha = jsonObj.getString("soNha");
                        sonhaFull=sonha+";"+jsonObj.getString("soTo")+";"+jsonObj.getString("soThua");
                        arrSoNha.add(sonhaFull);
                    }
                }
            }
            return arrSoNha;
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<String> soNhas) {
        try {
            super.onPreExecute();

            
        }catch(Exception ex){
                ex.printStackTrace();
         }
        dialog.dismiss();


}
}

