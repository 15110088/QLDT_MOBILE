package stnmt.ttcntt.qldt_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class ThuaDatChiTiet extends AppCompatActivity {


    TabHost host;
    Toolbar toolbar;
    String _maHuyen ="", _maXa="", _soTo="", _soThua="",_dienTich="",_loaiDat="";
    String url ="", urlLichSu="", urlTaiSan="", urlQuyHoach="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thua_dat_chi_tiet);
        try
        {
            Bundle bundle = getIntent().getExtras();
            _maXa = bundle.getString("strMaKvHc");
            _maHuyen = bundle.getString("strMaKvHcCha");
            _soTo = bundle.getString("strSoTo");
            _soThua = bundle.getString("strSoThua");
            _dienTich = bundle.getString("strDienTich");
            _loaiDat = bundle.getString("strLoaiDat");
        }
        catch (Exception ex)
        {
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết Số nhà");
        //Sự kiện back màn hình
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //loadControl();
        addSuKien();


    }

    private void addSuKien() {
        try {
           // clsAccount mAccount = (clsAccount) getApplicationContext();
         //   boolean coQuyen = mAccount.CheckUserPemission(_maXa);
            boolean coQuyen=true;
            String data = JsonConvert.ConvertQueryThua(_maXa, Integer.parseInt(_soTo), _soThua);
            String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
            String iv="IVsdfsdfgdf487LT";
            CryptLib cry = new CryptLib();
            String enData = cry.encrypt(data, key, iv);
            String paraEncode = Uri.encode(enData);
            // Bundle bundle = getIntent().getExtras();
            String urlServiceThongTin="http://stnmt.dongnai.gov.vn:8080/ServicesSoNhaLongThanh/ServicesViTri.svc/";
            url = urlServiceThongTin+ "layThongTinSoNhaEpt?thamSo="+paraEncode;
            clsUrl clsTT = new clsUrl(url,coQuyen,_dienTich,_loaiDat);
            AsynTaskLayThongTinThuaDat asynLayTT = new AsynTaskLayThongTinThuaDat(this);
            //asynLayTT.execute(url);
            asynLayTT.execute(clsTT);
//            if (coQuyen) {
//                TextView v = (TextView) findViewById(R.id.txtLichSu);
//                v.setVisibility(View.INVISIBLE);
//                AsynTaskLichSuThuaDat asynTaskLichSuThuaDat = new AsynTaskLichSuThuaDat(this);
//                asynTaskLichSuThuaDat.execute(urlLichSu);
//            }
//            else
//            {
//                TextView v = (TextView) findViewById(R.id.txtLichSu);
//                v.setText("Bạn không có quyền xem thông tin lịch sử");
//                v.setVisibility(View.VISIBLE);
//            }
            AsynTaskThongTinQuyHoach asynQuyHoach = new AsynTaskThongTinQuyHoach(this);
            asynQuyHoach.execute(urlQuyHoach);
//            if (coQuyen) {
//                TextView v = (TextView) findViewById(R.id.txtThongBaoTaiSan);
//                v.setText("");
//                AsynTaskTaiSan asynTaiSan = new AsynTaskTaiSan(this);
//                asynTaiSan.execute(urlTaiSan);
//            }
//            else
//            {
//                TextView v = (TextView) findViewById(R.id.txtThongBaoTaiSan);
//                v.setText("Bạn không có quyền xem thông tin về tài sản");
//            }

        }catch (Exception ex){
            String err= ex.getMessage();
            Log.e(getResources().getString(R.string.app_name), "Select feature failed: " + ex.getMessage());

        }
    }

    ///add control
    /*private void loadControl() {

        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.imgthongtin));

        host.addTab(spec);

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}