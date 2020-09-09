package stnmt.ttcntt.qldt_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChonHuyen extends AppCompatActivity {
    Toolbar toolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_huyen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Sự kiện back màn hình
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        AsynTaskKVHC kv = new AsynTaskKVHC(this);
        kv.execute();

        ListView lv = (ListView)findViewById(R.id.lisViewCapHuyen);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView lbl = (TextView)view.findViewById(R.id.txt);
                ImageView img =(ImageView)view.findViewById(R.id.img);
                Intent intenHuyen = new Intent(ChonHuyen.this,ChonXaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("strMaKvHc", lbl.getTag().toString());
                bundle.putString("strMaKvHcCha", img.getTag().toString());
                bundle.putString("strTenHuyen",lbl.getText().toString());
                intenHuyen.putExtras(bundle);
                startActivityForResult(intenHuyen, 1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1) {
                Intent returnIntent = getIntent();
                if (resultCode == Activity.RESULT_OK) {
                    returnIntent.putExtra("strMaKvHc", data.getStringExtra("strMaKvHc"));
                    returnIntent.putExtra("strMaKvHcCha", data.getStringExtra("strMaKvHcCha"));
                    returnIntent.putExtra("strTenKvHc", data.getStringExtra("strTenKvHc"));
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        } catch (Exception ex) {
        }
    }
}