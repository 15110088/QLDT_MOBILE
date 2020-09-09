package stnmt.ttcntt.qldt_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DanhSachChuThuaActivity extends AppCompatActivity {

    Toolbar toolbar;
    String _strUrl = "";
    private String _maHuyen = "";
    private String _maXa = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_thua);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh sách thửa đất theo chủ");
        try
        {
            Bundle bundle = getIntent().getExtras();
            _strUrl = bundle.getString("strUrl");
            _maXa = bundle.getString("strMaKvHc");
            _maHuyen = bundle.getString("strMaKvHcCha");
        }
        catch(Exception ex)
        {
        }
        //gọi Sự kiện back màn hình
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CacHamSuKien();
    }

    private void CacHamSuKien() {
        try
        {
            if(!_strUrl.isEmpty())
            {
                AsynTaskThongTinThuaChu asynThongTinThuaChu = new AsynTaskThongTinThuaChu(this);
                asynThongTinThuaChu.execute(_strUrl);
                ListView lvDanhSachThuaChu = (ListView)findViewById(R.id.lvDanhSachThuaChu);
                lvDanhSachThuaChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent returnIntent = getIntent();
                        try
                        {
                            clsThongTinThuaChu thuaChu =(clsThongTinThuaChu) view.findViewById(R.id.txtThongTinThuaChu).getTag();
                            returnIntent.putExtra("strSoTo",thuaChu.soTo);
                            returnIntent.putExtra("strSoThua",thuaChu.soThua);
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                            //String test = String.format("Số tờ: %s, số thửa: %s", thuaChu.soTo, thuaChu.soThua);
                            //Toast.makeText(DanhSachChuThuaActivity.this,test, Toast.LENGTH_LONG).show();
                        }
                        catch(Exception ex)
                        {

                        }
                        setResult(Activity.RESULT_CANCELED, returnIntent);
                    }
                });

            }
        }
        catch (Exception ex)
        {

        }
    }

    ///Sự kiện back màn hình trước.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}