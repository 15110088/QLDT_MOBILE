package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 5/21/2016.
 */
public class AsynTaskKVHC extends AsyncTask<Void, Void, ArrayList<clsKvHc>> {

    Activity activityCha;
    //String url ="http://stnmt.dongnai.gov.vn:8080/quanlydatwebservicelongan/QuanLyDatWebService.svc/LayDonViHanhChinhCon?maKvhc=80";
   // String url ="http://stnmt.dongnai.gov.vn:8080/ServicesSoNhaLongThanh/ServicesViTri.svc/LayDonViHanhChinhCon?maKvhc=740";
   String url ="http://stnmt.dongnai.gov.vn:8080/Dothibienhoa/ServicesViTri.svc/LayDonViHanhChinhCon?maKvhc=75";
    private static  final String TAG_MAKVHC = "maKvhc";
    private static final String TAG_TEN ="ten";
    private static final String TAG_MACHA="maKvhcCha";

    public AsynTaskKVHC(Activity att){activityCha = att;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<clsKvHc> doInBackground(Void... params) {
        ArrayList<clsKvHc> lstRs = new ArrayList<clsKvHc>();
        ServiceHandler sh = new ServiceHandler();
        String strJson = sh.makeServiceCall(url,ServiceHandler.GET);
        //Log.d("Response: ", "> " + strJson);
        if(strJson !=null)
        {
            try{
                JSONArray jsonArray = new JSONArray(strJson);
                if(jsonArray.length()>0)
                {
                    JSONObject jsonObject;
                    clsKvHc objKvHc;
                    for(int i=0; i< jsonArray.length(); i++)
                    {
                        jsonObject = jsonArray.getJSONObject(i);
                        objKvHc = new clsKvHc();
                        objKvHc.maKvhc = jsonObject.getString(TAG_MAKVHC);
                        objKvHc.ten = jsonObject.getString(TAG_TEN);
                        objKvHc.maKvhcCha = jsonObject.getString(TAG_MACHA);
                        lstRs.add(objKvHc);
                    }
                }
            }catch (Exception ex){ex.toString();}
        }
        return lstRs;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<clsKvHc> clsKvHcs) {
        super.onPostExecute(clsKvHcs);
        CustomListViewKvHc adapter = new CustomListViewKvHc(activityCha,R.layout.layout_rowitem_huyen,clsKvHcs);
        ListView lst = (ListView) activityCha.findViewById(R.id.lisViewCapHuyen);
        lst.setAdapter(adapter);
    }
}
