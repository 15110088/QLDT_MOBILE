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
public class AsynTaskThongTinQuyHoach extends AsyncTask<String, Void, String> {

    private static String url;

    private static final String TAG_quyHoach ="quyHoach";
    Activity activityCha;

    public AsynTaskThongTinQuyHoach(Activity att)
    {
        this.activityCha = att;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String strReturn ="";
        ServiceHandler sh = new ServiceHandler();
        url = params[0];
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
               strReturn = jsonObj.getString(TAG_quyHoach);

            } catch (JSONException e) {
                //e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");}
        return strReturn;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String strKetQua) {
        super.onPostExecute(strKetQua);
        try{
            TextView txtQuyHoach = (TextView)activityCha.findViewById(R.id.lblSoNhaTest);
            txtQuyHoach.setText(strKetQua);
        }catch (Exception ex){
            //ex.printStackTrace();
        }
    }
}
