package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Parser;

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
import stnmt.ttcntt.qldt_mobile.Interface.ItemClickListener;
import stnmt.ttcntt.qldt_mobile.Retrofit.ApiService;
import stnmt.ttcntt.qldt_mobile.Retrofit.RetrofitSupport;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ConvertMoneyService;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ResponseCurrency;

public class AsynTaskModalThongTinThuaDat  extends AsyncTask<clsUrl,Void, ArrayList<clsThuaDat>> implements ItemClickListener {
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
    TextView lblSoTo,txtSoVungQuyHoach,txtQuyetDinh, lblSoThua, lblDienTich,lblLoaiDat, lblTenChu, lblTinhTrangcapGiay,lblSoNha,lblDiaChi,lblChanGD,lblNguonGoc,lblSoNHaTest;
    RecyclerView rv;
    View activityCha;
    String jsonStr;
    String _soTo="";
    String _soThua="";
    String _dienTich="";
    String _loaiDat="";
    String paraEncode="";
    ArrayList<clsThuaDat> thuaDatArrayList;
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
            ServiceHandler sh = new ServiceHandler();
            clsUrl cls = (clsUrl)params[0];
            url = cls.getUrl();
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    if(jsonArray.length() > 0)
                    {
                        JSONObject jsonObj;
                        clsThuaDat thuaDat;
                        for (int i=0; i< jsonArray.length();i++) {
                            jsonObj = jsonArray.getJSONObject(i);
                            thuaDat = new clsThuaDat();
                            thuaDat.tenVungQuyHoach=jsonObj.getString("tenVungQuyHoach");
                            thuaDat.tangCaoXayDung=jsonObj.getString("tangCaoXayDung");
                            thuaDat.matDoXD=jsonObj.getString("matDoXD");
                            thuaDat.danSo=jsonObj.getString("danSo");
                            thuaDat.loChucNang=jsonObj.getString("loChucNang");
                            thuaDat.oPho=jsonObj.getString("oPho");
                            thuaDat.dienTichQuyHoach=jsonObj.getString("dienTichQuyHoach");
                            thuaDat.maMDSDQuyHoach=jsonObj.getString("maMDSDQuyHoach");
                            thuaDat.dienTichGiao=jsonObj.getString("dienTichGiao");
                            thuaDats.add(thuaDat);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(getClass().toString(),  e.getMessage());
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url"+url);}
//            clsThuaDat td= new clsThuaDat();
//            td.tenVungQuyHoach="Xay cong vien";
//            thuaDats.add(td);
//            clsThuaDat td2= new clsThuaDat();
//            td2.tenVungQuyHoach="Xay nha tro";
//            thuaDats.add(td2);
//
//            clsThuaDat td3= new clsThuaDat();
//            td3.tenVungQuyHoach="Xay khach san";
//            thuaDats.add(td3);
//
//
//            clsThuaDat td4= new clsThuaDat();
//            td3.tenVungQuyHoach="Xay khach san";
//            thuaDats.add(td4);
//
//
//            clsThuaDat td5= new clsThuaDat();
//            td3.tenVungQuyHoach="Xay khach san";
//            thuaDats.add(td5);

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
            txtQuyetDinh=(TextView) activityCha.findViewById(R.id.txtQuyetDinh);
            txtQuyetDinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url ="https://www.dongnai.gov.vn/Shared%20Documents/2-2020-QD-613.pdf";
                    Uri uriUrl = Uri.parse(url);
                    Intent launchBrower = new Intent(Intent.ACTION_VIEW,uriUrl);
                    activityCha.getContext().startActivity(launchBrower);


                }
            });
            if(thuaDat == null)
                return;
            lblSoTo = (TextView) activityCha.findViewById(R.id.txtSoTo);
            lblSoThua = (TextView) activityCha.findViewById(R.id.txtSoThua);
            lblDienTich=(TextView) activityCha.findViewById(R.id.txtdientich);
            //lblLoaiDat=(TextView) activityCha.findViewById(R.id.txtLoaiDat);
            txtSoVungQuyHoach=(TextView) activityCha.findViewById(R.id.txtSoVungQuyHoach);
            //lblQH=(TextView) activityCha.findViewById(R.id.txtQuyHoach);
            thuaDatArrayList=thuaDat;
            rv=(RecyclerView)activityCha.findViewById(R.id.rvQuyHoach);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activityCha.getContext(),LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(linearLayoutManager);
            rv.hasFixedSize();
            quyhoachAdapter qh=new quyhoachAdapter(activityCha.getContext(),thuaDat,this);
            rv.setAdapter(qh);



            lblSoTo.setText(_soTo);
            lblSoThua.setText(_soThua);
            lblDienTich.setText(_dienTich);
            txtSoVungQuyHoach.setText("("+ thuaDat.size()+")");



        }catch (Exception ex)
        {
            Log.e("onPostExecute "+getClass(),ex.getMessage());
        }
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Toast.makeText(activityCha.getContext(), "Item Click: "+thuaDatArrayList.get(position), Toast.LENGTH_SHORT).show();

    }
}
