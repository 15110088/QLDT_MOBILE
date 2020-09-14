package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stnmt.ttcntt.qldt_mobile.Adapter.quyhoachAdapter;
import stnmt.ttcntt.qldt_mobile.Retrofit.ApiService;
import stnmt.ttcntt.qldt_mobile.Retrofit.RetrofitSupport;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ConvertMoneyService;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ResponseCurrency;

public class AsynTaskModalThongTinThuaDat  extends AsyncTask<clsUrl,Void, ArrayList<clsThuaDat>>{
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
    TextView lblSoTo,lblQH, lblSoThua, lblDienTich,lblLoaiDat, lblTenChu, lblTinhTrangcapGiay,lblSoNha,lblDiaChi,lblChanGD,lblNguonGoc,lblSoNHaTest;
    RecyclerView rv;
    View activityCha;
    String jsonStr;
    String _soTo="";
    String _soThua="";
    String _dienTich="";
    String _loaiDat="";
    String paraEncode="";
    public AsynTaskModalThongTinThuaDat(View att,String soTo,String soThua,String dientich,String loaiDat,String paraEncode)
    {
        this.activityCha = att;
        this._soTo=soTo;
        this._soThua=soThua;
        this._dienTich=dientich;
        this._loaiDat=loaiDat;
        this.paraEncode=paraEncode;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

//    @Override
//    protected List<clsThuaDat> doInBackground(clsUrl... params) {
//        final List<clsThuaDat> thuaDat = null;
//        clsUrl cls = (clsUrl)params[0];
//        RetrofitSupport<ApiService> retrofitTest =new RetrofitSupport<ApiService>();
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.169.3.197/DTBienHoa/servicesvitri.svc/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        ApiService service= retrofit.create(ApiService.class);
//        Call<List<clsThuaDat>> repos = service.LayThongTinQuyHoach(paraEncode);
//
//        repos.enqueue(new Callback<List<clsThuaDat>>() {
//            @Override
//            public void onResponse(Call<List<clsThuaDat>> call, Response<List<clsThuaDat>> response) {
//
//                List<clsThuaDat> t=response.body();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<clsThuaDat>> call, Throwable t) {
//
//            }
//        });
//        return  thuaDat;
//    }

        @Override
    protected ArrayList<clsThuaDat> doInBackground(clsUrl... params) {

            ArrayList<clsThuaDat> thuaDats = new ArrayList<clsThuaDat>();

//            ServiceHandler sh = new ServiceHandler();
//            clsUrl cls = (clsUrl)params[0];
//            url = cls.getUrl();
//            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
//            Log.d("Response: ", "> " + jsonStr);
//            if (jsonStr != null) {
//                try {
//                    JSONArray jsonArray = new JSONArray(jsonStr);
//                    if(jsonArray.length() > 0)
//                    {
//                        JSONObject jsonObj;
//                        clsThuaDat thuaDat;
//                        for (int i=0; i< jsonArray.length();i++) {
//                            jsonObj = jsonArray.getJSONObject(i);
//                            thuaDat = new clsThuaDat();
//                            thuaDat.tenVungQuyHoach=jsonObj.getString("tenVungQuyHoach");
//                            thuaDats.add(thuaDat);
//                        }
//                    }
//
//
//
//                } catch (JSONException e) {
//                    Log.e(getClass().toString(),  e.getMessage());
//                }
//            } else {
//                Log.e("ServiceHandler", "Couldn't get any data from the url"+url);}
            clsThuaDat td= new clsThuaDat();
            td.tenVungQuyHoach="Xay cong vien";
            thuaDats.add(td);
            clsThuaDat td2= new clsThuaDat();
            td2.tenVungQuyHoach="Xay nha tro";
            thuaDats.add(td2);

            clsThuaDat td3= new clsThuaDat();
            td3.tenVungQuyHoach="Xay khach san";
            thuaDats.add(td3);


            clsThuaDat td4= new clsThuaDat();
            td3.tenVungQuyHoach="Xay khach san";
            thuaDats.add(td4);


            clsThuaDat td5= new clsThuaDat();
            td3.tenVungQuyHoach="Xay khach san";
            thuaDats.add(td5);

            return thuaDats;

        }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<clsThuaDat> thuaDat) {
        super.onPostExecute(thuaDat);
        try{
            if(thuaDat == null)
                return;
            lblSoTo = (TextView) activityCha.findViewById(R.id.txtSoTo);
            lblSoThua = (TextView) activityCha.findViewById(R.id.txtSoThua);
            lblDienTich=(TextView) activityCha.findViewById(R.id.txtdientich);
            lblLoaiDat=(TextView) activityCha.findViewById(R.id.txtLoaiDat);
            //lblQH=(TextView) activityCha.findViewById(R.id.txtQuyHoach);

            rv=(RecyclerView)activityCha.findViewById(R.id.rvQuyHoach);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activityCha.getContext(),LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(linearLayoutManager);
            quyhoachAdapter qh=new quyhoachAdapter(activityCha.getContext(),thuaDat);
            rv.setAdapter(qh);


            lblSoTo.setText(_soTo);
            lblSoThua.setText(_soThua);
            lblDienTich.setText(_dienTich);
            lblLoaiDat.setText(_loaiDat);
//            if(thuaDat.size()>0)
//            {
//                String thongtinQH="";
//                for (clsThuaDat item: thuaDat) {
//                    thongtinQH+=item.tenVungQuyHoach;
//                }
//                lblQH.setText(thongtinQH);
//
//
//            }
//            else {
//                lblQH.setText("Không có thông tin ");
//
//            }


        }catch (Exception ex)
        {
            Log.e("onPostExecute "+getClass(),ex.getMessage());
        }
    }
}
