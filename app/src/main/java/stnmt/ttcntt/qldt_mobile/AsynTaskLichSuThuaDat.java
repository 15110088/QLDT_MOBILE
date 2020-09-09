package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by PTCN_08 on 6/1/2016.
 */
public class AsynTaskLichSuThuaDat extends AsyncTask<String, Void, ArrayList<clsLichSuThuaDat>> {
    Activity activityCha;
    String url;
    ProgressDialog dialog;

    private static final String TAG_NGAY ="thoiDiem";
    private static final String TAG_NOIDUNG="moTa";

    public AsynTaskLichSuThuaDat(Activity att) {this.activityCha =att; }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activityCha,"","Chương trình đang xử lý. Vui lòng chờ...");
        dialog.setCancelable(false);
    }

    @Override
    protected ArrayList<clsLichSuThuaDat> doInBackground(String... params) {
        try{
            ArrayList<clsLichSuThuaDat> arrLichSu = new ArrayList<clsLichSuThuaDat>();
            url= params[0];
            ServiceHandler sh = new ServiceHandler();
            String strJson = sh.makeServiceCall(url,ServiceHandler.GET);
            if(strJson !=null)
            {
                JSONArray jsonArray = new JSONArray(strJson);
                if(jsonArray.length() > 0)
                {
                    JSONObject jsonObj;
                    clsLichSuThuaDat lichSuThuaDat;
                    for (int i=0; i< jsonArray.length();i++) {
                        jsonObj = jsonArray.getJSONObject(i);
                        lichSuThuaDat = new clsLichSuThuaDat();

                        String strNgay = jsonObj.getString(TAG_NGAY);

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        lichSuThuaDat.ngayBienDong = dateFormat.parse(jsonObj.getString(TAG_NGAY));
                        lichSuThuaDat.noiDungBienDong = jsonObj.getString(TAG_NOIDUNG);
                        arrLichSu.add(lichSuThuaDat);
                    }
                }
            }
            return  arrLichSu;
        }catch (Exception ex){}
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<clsLichSuThuaDat> clsLichSuThuaDats) {
        super.onPostExecute(clsLichSuThuaDats);
        try{
            if(clsLichSuThuaDats == null ||clsLichSuThuaDats.isEmpty())
            {
                dialog.dismiss();
                //Toast.makeText(activityCha,"Không tìm thấy dữ liệu.", Toast.LENGTH_SHORT).show();
                return;
            }
            CustomListViewLichSuThuaDat adapter = new CustomListViewLichSuThuaDat(activityCha,R.layout.layout_rowitem_lichsu_thuadat,clsLichSuThuaDats);
            ListView lst = (ListView) activityCha.findViewById(R.id.lvLichSuThuaDat);
            lst.setAdapter(adapter);
            dialog.dismiss();
            super.onPreExecute();

        }catch (Exception ex){dialog.dismiss();
            //ex.printStackTrace();
        }
    }
}
