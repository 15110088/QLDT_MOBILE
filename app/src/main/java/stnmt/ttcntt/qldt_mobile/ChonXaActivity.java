package stnmt.ttcntt.qldt_mobile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChonXaActivity extends AppCompatActivity {

    Toolbar toolbar;

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_xa);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Chọn xã");
        //Sự kiện quay về trang trước
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        String strMaKvHc = bundle.getString("strMaKvHc");
        String strMaKvHcCha = bundle.getString("strMaKvHcCha");
        AsynTaskKvHcXa kv = new AsynTaskKvHcXa(this);
        kv.execute(strMaKvHc);

        ListView lvCapXa = (ListView)findViewById(R.id.lwCapXa);
        lvCapXa.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView lbl = (TextView)view.findViewById(R.id.txtXa);
                ImageView img =(ImageView)view.findViewById(R.id.imgXa);
                //Intent returnIntent = getIntent();
                //returnIntent.putExtra("strMaKvHc", lbl.getTag().toString());
                //returnIntent.putExtra("strMaKvHcCha",img.getTag().toString());
                //returnIntent.putExtra("strTenKvHc",lbl.getText().toString());
                //setResult(Activity.RESULT_OK,returnIntent);
                //finish();
                Intent intenMain = new Intent(ChonXaActivity.this,MainActivity.class);
                //Toast.makeText(ChonXaActivity.this,img.getTag().toString() + " : " + lbl.getTag().toString(), Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("strMaKvHc", lbl.getTag().toString());
                bundle.putString("strMaKvHcCha",img.getTag().toString());
                bundle.putString("strTenKvHc",lbl.getText().toString());
                intenMain.putExtras(bundle);
                startActivityForResult(intenMain, 3);

            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    //Ham dong, mo edidtext search
    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar
        if(isSearchOpened)
        {
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar
            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);
            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_open_search));
            isSearchOpened = false;
        }else{
            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title
            edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor
            edtSeach.setHint("Nhập tên phường/xã");
            edtSeach.setHintTextColor(getResources().getColor(R.color.white));
            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        TimKiemXa();
                        return true;
                    }
                    return false;
                }
            });

            edtSeach.requestFocus();
            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);
            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_close_search));
            isSearchOpened = true;
        }
    }

    //Ham tim kiem
    private void TimKiemXa(){
        try{
            //noi dung tim kiem
        }catch (Exception ex){}
    }
}