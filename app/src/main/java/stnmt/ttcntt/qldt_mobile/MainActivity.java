package stnmt.ttcntt.qldt_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.ArcGISFeature;
import com.esri.arcgisruntime.data.ArcGISFeatureTable;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureCollection;
import com.esri.arcgisruntime.data.FeatureCollectionTable;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.layers.ArcGISMapImageSublayer;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.WebTiledLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.GeoElement;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.security.UserCredential;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.Symbol;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stnmt.ttcntt.qldt_mobile.Adapter.customAdapterViewPage;
import stnmt.ttcntt.qldt_mobile.Adapter.tabAdapter;
import stnmt.ttcntt.qldt_mobile.Fragment.chuthichbando;
import stnmt.ttcntt.qldt_mobile.Fragment.loaibando;
import stnmt.ttcntt.qldt_mobile.Fragment.thongtinthuadat;
import stnmt.ttcntt.qldt_mobile.Interface.IEventImageButton;
import stnmt.ttcntt.qldt_mobile.Model.Model;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ConvertMoneyService;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ResponseCurrency;
import stnmt.ttcntt.qldt_mobile.Util.BottomSheetCustom;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, IEventImageButton {
    private MapView mMapView;
    private ArcGISMap map;
    private Callout mCallout;
    private ServiceFeatureTable mServiceFeatureTable;
    private FeatureLayer mFeatureLayer;
    private ServiceFeatureTable mServiceFeatureTableMauQH;
    private FeatureLayer mFeatureLayerMauQH;


    LocationDisplay lDisplayManager;
    LocationDisplay mLocationDisplay;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    @BindView(R.id.lyt_layout)
    LinearLayout linearLayoutBottomSheet;

    public BottomSheetCustom bottomSheet;
    TextView textView;
    ImageButton btntest,btnLayerQH;
    private MenuItem mSearchAction;
    FloatingActionButton fab,fab_location;
    @BindView(R.id.fab_sheetMap)
    FloatingActionButton  fab_map;
    private boolean isSearchOpened = false;
    private AutoCompleteTextView edtSeach;
    private String _maHuyen = "";
    private String _maXa = "26377";//26368 long thanh .///26377
    private String soTo = "";
    private String soThua = "";
    private String dienTich = "";
    private String _tieuDe = "Phường Phước Tân";
    private String loaiDat="";
    private String loaiQH ="";
    LayoutInflater inflater;
    Intent _intentThuaChu = null;
    Boolean isBusy = false;
    boolean _enableGPS = false;
    boolean _isLayerQH = false;
    boolean _isExpaned = false;
    boolean _isLoadThuaDat = false;
    boolean _isLoadingMap =false;
    int LevelBottomSheet=2;

    RouteTask routeTask;
    List<String> lstSoNha=null;
    List<String> lstKQSoNha=null;
    private String urlServiceThongTin="http://stnmt.dongnai.gov.vn:8080/Dothibienhoa/ServicesViTri.svc/";

    private BottomSheetBehavior bottomSheetBehavior;
    Basemap.Type basemapType;

    private ToggleButton tbUpDown;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.spin_kit)
    ProgressBar progressBar;
    //Apdater
    customAdapterViewPage adapterViewPage;
    tabAdapter adapter;
    List<Model> models;


    Integer[] colors = null;
   // ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        inflater = getLayoutInflater();
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        //botton sheet


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);

        if (_maHuyen == null || _maHuyen.length()<1) CheckLastDaTa();
        actionBar.setTitle(_tieuDe);
        actionBar.setCustomView(R.layout.search_bar);
        edtSeach = (AutoCompleteTextView) actionBar.getCustomView().findViewById(R.id.edtSearch); //the text editor
        //this is a listener to do a search when the user clicks on search button
        edtSeach.setHintTextColor(getResources().getColor(R.color.white));
        layDuLieu();
        setupMap();
        ShowCallOut();
        InitControll();
        setupLocationDisplay();
        UserCredential user = new UserCredential("dothibienhoa", "dothibienhoa2020");
        UserCredential userBH = new UserCredential("bienhoa", "Stnmt75731");

        mServiceFeatureTableMauQH=new ServiceFeatureTable("http://datdai.stnmt.dongnai.gov.vn/arcgis/rest/services/DOTHIBIENHOA/PhanKhu_26377/MapServer/0");
        mServiceFeatureTableMauQH.setCredential(user);
        mFeatureLayerMauQH=new FeatureLayer(mServiceFeatureTableMauQH);
        mFeatureLayerMauQH.addDoneLoadingListener(()->{


        });



       String url1="http://datdai.stnmt.dongnai.gov.vn/arcgis/rest/services/DOTHIBIENHOA/26377/MapServer/0";
      //  String url1="http://stnmt.dongnai.gov.vn:8080/arcgis/rest/services/75731/26377/MapServer/1";
       // String url1 ="https://stnmt.dongnai.gov.vn:8443/arcgisdichvussl/rest/services/LongThanh_SoNha/SoNha_26368/MapServer/0";
       // String url1 = "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trailheads/FeatureServer/0";
        mServiceFeatureTable=new ServiceFeatureTable(url1);
        mServiceFeatureTable.setCredential(user);

        mFeatureLayer=new FeatureLayer(mServiceFeatureTable);

      //  map = new ArcGISMap(SpatialReference.create(3857));
        final WebTiledLayer webTiledLayer = new WebTiledLayer("https://mts1.google.com/vt/lyrs=m&hl=x-local&src=app&x={col}&y={row}&z={level}&s=Galile");
        webTiledLayer.loadAsync();
        webTiledLayer.addDoneLoadingListener(() -> {
            if (webTiledLayer.getLoadStatus() == LoadStatus.LOADED) {

            }
        });

        ArcGISTiledLayer tiledLayerBaseMap = new ArcGISTiledLayer("http://datdai.stnmt.dongnai.gov.vn/arcgis/rest/services/DOTHIBIENHOA/PhanKhu_26377/MapServer");
        tiledLayerBaseMap.setCredential(user);
        ArcGISTiledLayer tiledLayerBaseMap1 = new ArcGISTiledLayer("http://stnmt.dongnai.gov.vn:8080/arcgis/rest/services/75731/26377/MapServer");
        tiledLayerBaseMap1.setCredential(userBH);
       // map.getBasemap().getBaseLayers().add(webTiledLayer);

        //map.getBasemap().getBaseLayers().add(tiledLayerBaseMap);
        //map.getBasemap().getBaseLayers().add(tiledLayerBaseMap1);

        map.getOperationalLayers().add(tiledLayerBaseMap);
        map.getOperationalLayers().add(mFeatureLayer);

        mMapView = (MapView) findViewById(R.id.mapView);
        BackgroundGrid backgroundGrid = new BackgroundGrid(Color.WHITE,Color.WHITE,0,2);
        mMapView.setBackgroundGrid(backgroundGrid);
        mMapView.setMap(map);

//        Point startPoint = new Point(435931.880, 1210144.374, map.getSpatialReference());
//        mMapView.setViewpointCenterAsync(startPoint, 1000000);
        mMapView.setAttributionTextVisible(false);
        //SpatialReference spatialReference = SpatialReference.create(3857);
        //Point startPoint = new Point(11929612.044659, 1225568.999846,  spatialReference);
        //mMapView.setViewpointCenterAsync(startPoint, 850000);
//        mMapView.setAttributionTextVisible(false);

        mFeatureLayer.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                //code here to check for error status
                if (mFeatureLayer.getLoadStatus() == LoadStatus.LOADED) {
                    Toast.makeText(getApplication(), "Tải Bản Đồ ", Toast.LENGTH_LONG).show();
                    _isLoadingMap=true;
                    progressBar.setVisibility(View.INVISIBLE);

                }
                if (mFeatureLayer.getLoadStatus() == LoadStatus.FAILED_TO_LOAD) {
                    Toast.makeText(getApplication(), "Lỗi FAILED_TO_LOAD mFeatureLayer", Toast.LENGTH_LONG).show();

                }
                if (mFeatureLayer.getLoadStatus() == LoadStatus.NOT_LOADED) {
                    Toast.makeText(getApplication(), "Lỗi NOT_LOADED  mFeatureLayer", Toast.LENGTH_LONG).show();

                }
            }
        });

        map.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                //code here to check for error status
                if (map.getLoadStatus() == LoadStatus.LOADED) {
                    Toast.makeText(getApplication(), "Tải Bản Đồ Quy Hoạch", Toast.LENGTH_LONG).show();
                }
                if (map.getLoadStatus() == LoadStatus.FAILED_TO_LOAD) {
                    Toast.makeText(getApplication(), "Lỗi FAILED_TO_LOAD  Quy Hoạch", Toast.LENGTH_LONG).show();
                }
                if (map.getLoadStatus() == LoadStatus.NOT_LOADED) {
                    Toast.makeText(getApplication(), "Lỗi NOT_LOADED  Quy Hoạch", Toast.LENGTH_LONG).show();
                }
            }
        });

        ZoomToXa(mMapView,_maXa);
        map.setMinScale(100000);

        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay);

        SimpleMarkerSymbol citySymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 16);
        QueryParameters populationQuery = new QueryParameters();
        populationQuery.setWhereClause("SH_TO = 58" );
        btntest=findViewById(R.id.btntest);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               QueryAndDisplayGraphics(mServiceFeatureTable, citySymbol, populationQuery, graphicsOverlay);
            }
        });

        btnLayerQH = findViewById(R.id.btnLayerQH);
        btnLayerQH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(_isLayerQH)
                    {
                        _isLayerQH=false;
                        Toast.makeText(getApplication(), "Chọn Lớp Ranh Thủa", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        _isLayerQH=true;
                        Toast.makeText(getApplication(), "Chọn Lớp Quy Hoạch", Toast.LENGTH_LONG).show();
                    }
            }
        });

        fab_location  = (FloatingActionButton) findViewById(R.id.fab_sheetlocation);
        fab_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
                mLocationDisplay.startAsync();
            }
        });
        lDisplayManager = mMapView.getLocationDisplay();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apilayer.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConvertMoneyService service= retrofit.create(ConvertMoneyService.class);
        Call<ResponseCurrency> repos = service.convertVNDtoUSD("843d4d34ae72b3882e3db642c51e28e6","VND", "USD", 1);

//        repos.enqueue(new Callback<ResponseCurrency>() {
//            @Override
//            public void onResponse(Call<ResponseCurrency> call, Response<ResponseCurrency> response) {
//                ResponseCurrency res = response.body();
//                //Tỉ giá mình lấy được
//               Double exchangeRate = res.getQuotes().getUSDVND();
//                Log.d("Ty giá USD", exchangeRate.toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseCurrency> call, Throwable t) {
//                Log.d("Error", t.getMessage().toString());
//            }
//        });

        //View page
//        models = new ArrayList<>();
//        models.add(new Model(R.drawable.maphouse, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
//        models.add(new Model(R.drawable.search, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
//        models.add(new Model(R.drawable.arrow, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
//        models.add(new Model(R.drawable.biendong, "Namecard", "Business cards are cards bearing business information about a company or individual."));
//
//        adapter = new tabAdapter(models, this);
//
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(130, 0, 130, 0);
//
//        Integer[] colors_temp = {
//                getResources().getColor(R.color.md_black_1000),
//                getResources().getColor(R.color.md_blue_grey_50),
//                getResources().getColor(R.color.md_red_100),
//                getResources().getColor(R.color.md_brown_50)
//        };
//
//        colors = colors_temp;
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
//                    viewPager.setBackgroundColor(
//
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

    private void InitControll() {
        fab = findViewById(R.id.fab_sheet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_isLoadThuaDat)
                {
                    if(_isExpaned)
                    {
                       // ShowView(linearLayoutBottomSheet,270);
                        v.setRotation(0);
                        _isExpaned=false;
                        LevelBottomSheet=1;
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

                    }
                    else {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            v.setRotation(45);
                            LevelBottomSheet=2;
                            _isExpaned=true;
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng chọn thửa đất",Toast.LENGTH_SHORT).show();
                }
            }
        });
        fab_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
                bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
                Toast.makeText(MainActivity.this,"Chọn bản đồ",Toast.LENGTH_SHORT).show();
                adapterViewPage= new customAdapterViewPage(getSupportFragmentManager());
                adapterViewPage.removeAllFragment();
                loaibando frmLoaiBanDo= new loaibando((view, position) -> {
                    switch (position)
                    {
                        case 1:
                            Toast.makeText(getApplication(), "Bản Đồ Vệ Tinh",Toast.LENGTH_SHORT).show();
                            basemapType = Basemap.Type.IMAGERY;
                            mMapView.getMap().setBasemap(Basemap.createImageryWithLabelsVector());                            break;
                        case 2:
                            Toast.makeText(getApplication(), "Bản Đồ Đường",Toast.LENGTH_SHORT).show();
                            basemapType = Basemap.Type.OPEN_STREET_MAP;
                            mMapView.getMap().setBasemap(Basemap.createOpenStreetMap());
                            break;
                    }
                });
                chuthichbando frmChuThich = new chuthichbando();
                adapterViewPage.addFragment(frmLoaiBanDo,"ok");
                adapterViewPage.addFragment(frmChuThich,"ok");
                viewPager.setAdapter(adapterViewPage);
                viewPager.setPageTransformer(true,new StackTransformer());
                DotsIndicator dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
                dotsIndicator.setViewPager(viewPager);
                if(_isExpaned)
                {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    fab.setRotation(0);
                    _isExpaned=false;
                }
                else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    fab.setRotation(45);
                    _isExpaned=true;
                }
            }
        });
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

    }

    private void setupMap() {
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud6806025350,none,1JPJD4SZ8Y4DRJE15232");
        mMapView = findViewById(R.id.mapView);
        if (mMapView != null) {
            basemapType = Basemap.Type.OPEN_STREET_MAP;
            double latitude =  10.890587;
            double longitude = 106.922532;
            int levelOfDetail = 11;
            map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
           // ArcGISMap map = new ArcGISMap(SpatialReference.create(3857));
           // mMapView.setMap(map);
        }
    }
    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.itemHome:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intenMain = new Intent(MainActivity.this,MainActivity.class);
                                startActivity(intenMain);
                                return true;
                            case R.id.item_kvhc_huyen:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intenCapXa = new Intent(MainActivity.this,ChonXaActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("strMaKvHc", "731");
                                bundle.putString("strMaKvHcCha", "75");
                                bundle.putString("strTenHuyen","TP Biên Hòa");
                                intenCapXa.putExtras(bundle);
                                //startActivity(intenCapXa);
                                startActivityForResult(intenCapXa, 3);
                                return true;
                            /*case R.id.item_bieuDo:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                clsAccount mAccount1 = (clsAccount) getApplicationContext();
                                if(! mAccount1.CheckUserPemission(_maXa))
                                {
                                    Toast.makeText(MainActivity.this,"Bạn không có quyền xem biểu đồ thống kê", Toast.LENGTH_SHORT).show();
                                    return true;
                                }
                                Bundle bundle = new Bundle();
                                bundle.putString("strMaKvHc",_maXa);
                                bundle.putString("strMaKvHcCha", _maHuyen);
                                bundle.putString("strTenHuyen",_tieuDe);
                                Intent intenBieuDo = new Intent(MainActivity.this,veBieuDo.class);
                                intenBieuDo.putExtras(bundle);
                                //startActivity(intenCapHuyen);
                                startActivityForResult(intenBieuDo, 4);
                                return true;*/
                            case R.id.item_dangnhap:
                                menuItem.setChecked(true);
                                // Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                 Intent intentDangNhap = new Intent(MainActivity.this,LoginActivity.class);
                                  startActivityForResult(intentDangNhap,2);
                                return true;
                            case R.id.item_dangXuat:
//                                clsAccount mAccount = ((clsAccount) getApplicationContext());
//                                mAccount.setPassWord("");
//                                mAccount.setDonViHanhChinh("");
//                                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                editor.clear();
//                                editor.commit();
                                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
                                NavigationView iDangXuat = (NavigationView)findViewById( R.id.navigation_view);
                                iDangXuat.getMenu().findItem(R.id.item_dangXuat).setVisible(false);
                                return true;
                            case R.id.itemLienHe:
                                String url ="https://ungdung.stnmt.dongnai.gov.vn/sonhalongthanh/thongtin/pthongtinphanmem.aspx";
                                Uri uriUrl = Uri.parse(url);
                                Intent launchBrower = new Intent(Intent.ACTION_VIEW,uriUrl);
                                startActivity(launchBrower);

                                return  true;
                        }
                        return true;
                    }
                });
    }
    String strContent="";
    private void setupLocationDisplay() {
        mLocationDisplay = mMapView.getLocationDisplay();
        mLocationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {

            // If LocationDisplay started OK or no error is reported, then continue.
            if (dataSourceStatusChangedEvent.isStarted() || dataSourceStatusChangedEvent.getError() == null) {
                return;
            }

            int requestPermissionsCode = 2;
            String[] requestPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            // If an error is found, handle the failure to start.
            // Check permissions to see if failure may be due to lack of permissions.
            if (!(ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[1]) == PackageManager.PERMISSION_GRANTED)) {

                // If permissions are not already granted, request permission from the user.
                ActivityCompat.requestPermissions(MainActivity.this, requestPermissions, requestPermissionsCode);
            } else {

                // Report other unknown failure types to the user - for example, location services may not
                // be enabled on the device.
                String message = String.format("Error in DataSourceStatusChangedListener: %s", dataSourceStatusChangedEvent
                        .getSource().getLocationDataSource().getError().getMessage());
                Toast.makeText(MainActivity.this, "Vui lòng bật định vị", Toast.LENGTH_LONG).show();
            }
        });
        //mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.COMPASS_NAVIGATION);
        mLocationDisplay.startAsync();
        mLocationDisplay.startAsync();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mLocationDisplay.startAsync();
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
        }
    }

    private  void ShowCallOut(){
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                Log.d("Info", "onSingleTapConfirmed: " + motionEvent.toString());
                soTo = "";
                soThua = "";
                dienTich = "0";
                loaiDat="";
                strContent="";
                mFeatureLayer.clearSelection();
                mFeatureLayerMauQH.clearSelection();

                // get the point that was clicked and convert it to a point in map coordinates
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(motionEvent.getX()),
                        Math.round(motionEvent.getY()));
                // create a map point from screen point
                Point mapPoint = mMapView.screenToLocation(screenPoint);
                // convert to WGS84 for lat/lon format
                Point wgs84Point = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
                int tolerance = 10;

                final ListenableFuture<IdentifyLayerResult> identifyLayerResultListenableFuture = mMapView
                        .identifyLayerAsync(_isLayerQH?mFeatureLayerMauQH:mFeatureLayer, screenPoint, tolerance, false, 1);


                // create a textview for the callout
                identifyLayerResultListenableFuture.addDoneListener(() -> {
                    try {
                        IdentifyLayerResult identifyLayerResult = identifyLayerResultListenableFuture.get();
                        // create a textview to display field values
                        View calloutView = inflater.inflate(R.layout.sqmi, null);
                        TextView noiDung = (TextView) calloutView.findViewById(R.id.thongTinDoHoa);
                        ImageButton imgButtonInfor = (ImageButton) calloutView.findViewById(R.id.infor);
                        for (GeoElement element : identifyLayerResult.getElements()) {
                            Feature feature = (Feature) element;
                            if(_isLayerQH)
                            {
                                mFeatureLayerMauQH.selectFeature(feature);
                            }
                            else{
                                mFeatureLayer.selectFeature(feature);
                            }
                            // create a map of all available attributes as name value pairs
                            Map<String, Object> attr = feature.getAttributes();
                            Set<String> keys = attr.keySet();

                            for (String key : keys) {
                                Object value = attr.get(key);
                                // format observed field value as date
                                if (value instanceof GregorianCalendar) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                                    value = simpleDateFormat.format(((GregorianCalendar) value).getTime());
                                }
                                if(key.equals("SH_TO"))
                                {
                                    soTo=attr.get(key).toString();
                                }
                                if(key.equals("SH_THUA"))
                                {
                                    soThua=attr.get(key).toString();
                                }

                                if(key.equals("DIEN_TICH"))
                                {
                                    dienTich=attr.get(key).toString();
                                }
                                if(key.equals("LOAIDAT"))
                                {
                                    loaiDat=attr.get(key).toString();
                                }
                                if(key.equals("TENVUNGQUY"))
                                {
                                    loaiQH=attr.get(key).toString();
                                }


                                Log.i(getResources().getString(R.string.app_name), "Select feature failed: " + key + " | " + value + "\n");

                            }
                            if(_isLayerQH)
                            {
                                strContent = String.format("Mục Đích Quy Hoạch: %s", loaiQH);
                            }
                            else {
                                strContent = String.format("Số tờ: %s, số thửa: %s, diện tích: %.1fm²", soTo, soThua, Double.parseDouble(dienTich));
                            }
                            // center the mapview on selected feature
                             Envelope envelope = feature.getGeometry().getExtent();
                             mMapView.setViewpointGeometryAsync(envelope, 100);
//                            View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
//                            bottomSheet= new BottomSheetDialog(MainActivity.this);
//                            bottomSheet.setContentView(view);
                            if(!_isLayerQH)
                            {
                                handleViewPageThuaDat();
                            }
                            // show callout
                            noiDung.setText(strContent);
                            imgButtonInfor.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(MainActivity.this, "Sự kiện GPS thì bỏ vào đây", Toast.LENGTH_LONG).show();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("strMaKvHc", _maXa);
                                    bundle.putString("strMaKvHcCha", _maHuyen);
                                    bundle.putString("strSoTo", soTo);
                                    bundle.putString("strSoThua", soThua);
                                    bundle.putString("strDienTich", dienTich);
                                    bundle.putString("strLoaiDat", loaiDat);
                                   // Intent intent = new Intent(MainActivity.this, ThuaDatChiTiet.class);
                                    if(!_isLayerQH) {
                                      //  bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                                    }
                                    //  intent.putExtras(bundle);
                                    //startActivity(intent);

                                }
                            });
                            Log.i(getResources().getString(R.string.app_name), "Content: " +calloutView+ "\n");
                            mCallout = mMapView.getCallout();
                            mCallout.setLocation(mapPoint);
                            mCallout.setContent(calloutView);
                         //   mCallout.show();

                        }
                    } catch (Exception e1) {
                        Log.e(getResources().getString(R.string.app_name), "Select feature failed: " + e1.getMessage());
                    }
                });

                return true;
            }
        });
    }


    //Load dataviewpage

    private  void handleViewPageThuaDat(){
        if(!_isLayerQH)
        {
//                                bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
//                                bottomSheet = new BottomSheetCustom(_maXa,soTo,soThua,dienTich,loaiDat);
//                                bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
//                                final View view =View.inflate(getApplicationContext(), R.layout.bottom_sheet,null);
            fab.setRotation(45);
            bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
            String data = JsonConvert.ConvertQueryThua(_maXa, Integer.parseInt(soTo), soThua);
            String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
            String iv="IVsdfsdfgdf487LT";
            String url ="";
            try {
                CryptLib cry = new CryptLib();
                String enData = cry.encrypt(data, key, iv);
                String paraEncode = Uri.encode(enData);
                String urlServiceThongTin="http://stnmt.dongnai.gov.vn:8080/Dothibienhoa/ServicesViTri.svc/";
                url = urlServiceThongTin+ "LayThongTinQuyHoach?thamSo="+paraEncode;
                clsUrl clsTT = new clsUrl(url,true,dienTich,loaiDat);
                AsynTaskModalThongTinThuaDat asynLayTT = new AsynTaskModalThongTinThuaDat(linearLayoutBottomSheet,soTo,soThua,dienTich,loaiDat,paraEncode);
                asynLayTT.execute(clsTT);
                _isLoadThuaDat=true;

            }
            catch (Exception e)
            {

            }

            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view, int i) {

                    switch(i)
                    {
                        case BottomSheetBehavior.STATE_EXPANDED:
                            // ShowView(linearLayout,1300);
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            // ShowView(linearLayout,350);
                            fab.setRotation(45);
                            _isExpaned=true;
                            break;
                        case BottomSheetBehavior.STATE_HIDDEN:
                            _isExpaned=false;

                            fab.setRotation(0);break;
                    }
                }


                @Override
                public void onSlide(@NonNull View view, float v) {

                }
            });

            adapterViewPage= new customAdapterViewPage(getSupportFragmentManager());
            adapterViewPage.removeAllFragment();
            thongtinthuadat frmThuaDat = new thongtinthuadat();
            thongtinthuadat frmThuaDat2 = new thongtinthuadat();

            adapterViewPage.addFragment(frmThuaDat,"ok");
            adapterViewPage.addFragment(frmThuaDat2,"ok");

            viewPager.setAdapter(adapterViewPage);
            viewPager.setPageTransformer(true,new StackTransformer());

        }
    }

    //Ham dong, mo edidtext search
    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar
        //ImageButton imgButtonGPS = (ImageButton) findViewById(R.id.imgThuaChu);
        //imgButtonGPS.setVisibility(View.INVISIBLE);
        if(isSearchOpened)
        {
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus() .getWindowToken(),0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_open_search));
            isSearchOpened = false;
            edtSeach.setText("");
        }else{

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            // action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        //TimKiemThuaDat();
                        XuLyTimKiem();
                        ActionBar action = getSupportActionBar();
                        action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
                        action.setDisplayShowTitleEnabled(true); //show the title in the action bar
                        //hides the keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus() .getWindowToken(),0);
                        //add the search icon in the action bar
                        mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_open_search));
                        isSearchOpened = false;
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
            //addEventForAutocomplete();

        }
    }

    private void XuLyTimKiem()
    {
        try
        {
            EditText txt = (EditText) findViewById(R.id.edtSearch);
            String str = txt.getText().toString();
            String[] strS = str.split("/");
            if(strS.length ==1)
            {
                clsAccount mAccount = (clsAccount) getApplicationContext();
                if(! mAccount.CheckUserPemission(_maXa))
                {
                    Toast.makeText(MainActivity.this,"Bạn không có quyền tìm kiếm theo chủ sử dụng", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strSearch = strS[0];
                if(strSearch.length() < 5)
                {
                    Toast.makeText(MainActivity.this,"Thông tin tìm kiếm không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s = strSearch.replaceAll("\\D+","");
                String strTen ="";
                String strSoGiayTo ="";
                if(s.isEmpty())
                {
                    strTen =strSearch;
                    strSoGiayTo ="";
                }
                else
                {
                    strTen ="";
                    strSoGiayTo =strSearch;
                }
                String data = JsonConvert.ConvertQueryThuaChu(_maHuyen,_maXa,strTen,strSoGiayTo);
                String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
                String iv="IVsdfsdfgdf487LT";
                CryptLib cry = new CryptLib();
                String enData = cry.encrypt(data,key,iv);
                String paraEncode = Uri.encode(enData);
                //String url = String.format("http://stnmt.dongnai.gov.vn:8080/quanlydatwebservicelongan/QuanLyDatWebService.svc/LayThuaChuLongAnEpt?thamSo=%s", paraEncode);
                String url = String.format(urlServiceThongTin+ "timSoNhaByChuEpt?thamSo=%s", paraEncode);
                Bundle bundle = new Bundle();
                bundle.putString("strUrl", url);
                bundle.putString("strMaKvHc", _maXa);
                bundle.putString("strMaKvHcCha", _maHuyen);
                _intentThuaChu = new Intent(MainActivity.this, DanhSachChuThuaActivity.class);
                _intentThuaChu.putExtras(bundle);
                //startActivity(intent);
                //Request code = 1 sử dụng cho activity ThuaChu
                startActivityForResult(_intentThuaChu,1);
            }
            else if(strS.length>=2)
            {
                String str1 = strS[0];
                String str2 = strS[1];
//                     SimpleMarkerSymbol citySymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 16);
//
//                        graphicsOverlay.getGraphics().clear();
//
//                        QueryParameters populationQuery = new QueryParameters();
//                        populationQuery.setWhereClause("SH_TO =" + str1 + " and SH_THUA=" + str2 + " ");
//                        populationQuery
//                                .setGeometry(mMapView.getCurrentViewpoint(Viewpoint.Type.BOUNDING_GEOMETRY).getTargetGeometry());
//
//                        QueryAndDisplayGraphics(SubRanhThua, citySymbol, populationQuery, graphicsOverlay);


                if(isInteger(str1))
                {
                    TimKiemThuaDat(str1,str2);
                }
                else
                {
                    String data = JsonConvert.ConvertQueryThuaChu(_maHuyen,_maXa,str1,str2);
                    String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
                    String iv="IVsdfsdfgdf487LT";
                    CryptLib cry = new CryptLib();
                    String enData = cry.encrypt(data,key,iv);
                    String paraEncode = URLEncoder.encode(enData);
                    String url = String.format(urlServiceThongTin+"timSoNhaByChuEpt?thamSo=%s", paraEncode);
                    Bundle bundle = new Bundle();
                    bundle.putString("strUrl", url);
                    bundle.putString("strMaKvHc", _maXa);
                    bundle.putString("strMaKvHcCha", _maHuyen);
                    _intentThuaChu = new Intent(MainActivity.this, DanhSachChuThuaActivity.class);
                    _intentThuaChu.putExtras(bundle);
                    startActivityForResult(_intentThuaChu,1);
                }

            }

        }
        catch (Exception ex)
        {

        }
    }
    public boolean isInteger( String input )
    {
        try
        {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception ex)
        {
            return false;
        }
    }
    //Ham tim kiem
    private void TimKiemThuaDat(String soToTim, String soThuaTim ){
        if(isBusy) return;
        isBusy = true;
        try{
            if (soToTim != null && soToTim.length() > 0) {
                QueryParameters queryParameters = new QueryParameters();
                queryParameters.setWhereClause("SH_TO =" + soToTim + " and SH_THUA=" + soThuaTim + " ");
                try {
                    List<FeatureQueryResult> outFields = new ArrayList<>();

                    final ListenableFuture<FeatureQueryResult> future = mServiceFeatureTable.queryFeaturesAsync(queryParameters);

                    future.addDoneListener(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                //create a feature collection table from the query results
                                FeatureCollectionTable featureCollectionTable = new FeatureCollectionTable(future.get());

                                //create a feature collection from the above feature collection table
                                FeatureCollection featureCollection = new FeatureCollection();
                                featureCollection.getTables().add(featureCollectionTable);

                                FeatureQueryResult result = future.get();
                                Iterator<Feature> resultIterator = result.iterator();
                                soThua=soThuaTim;
                                soTo=soToTim;
                                if (resultIterator.hasNext()) {
                                    Feature feature1 = resultIterator.next();
                                    Envelope envelope = feature1.getGeometry().getExtent();
                                    mMapView.setViewpointGeometryAsync(envelope, 10);
                                    mFeatureLayer.selectFeature(feature1);
                                    //handleViewPageThuaDat();
                                } else {
                                }
                            } catch (Exception e) {
                                Toast.makeText(getApplication(), "Error=" + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Log.e(getResources().getString(R.string.app_name),
                                        "Error=" + e.getMessage());
                            }
                        }
                    });

                } catch (Exception e) {

                }
            }

        }
        catch (Exception ex) {

        }
        isBusy=false;
    }
    public String GetMapServicesUrl(String maHuyen, String maXa)
    {
        return "https://stnmt.dongnai.gov.vn:8443/arcgisdichvussl/rest/services/LongThanh_SoNha/RanhThua_SoNha_" + maXa + "/MapServer";
        //   return "http://quyhoachdatdai.longan.gov.vn:8080/arcgis1/rest/services/80"+maHuyen+"/" + maXa + "/MapServer";
    }

    public String GetMapServicesUrl()
    {
        return "http://datdai.stnmt.dongnai.gov.vn:8080/atlasadaptordatdai/rest/services/Atlas2015/NEN_KTXH_150715/MapServer";
        //return "http://quyhoachdatdai.longan.gov.vn:8080/arcgis1/rest/services/HanhChinh/HanhChinh_LA/MapServer";
    }

    public String GetMapServicesUrlSoNha(String maXa)
    {
        return "https://stnmt.dongnai.gov.vn:8443/arcgisdichvussl/rest/services/LongThanh_SoNha/" + "SoNha_" + maXa + "/MapServer";
    }
    public String GetMapServicesUrlQLDT(String maHuyen, String maXa)
    {
        try
        {
            if (maHuyen.equals("731") || maHuyen.equals("736") || maHuyen.equals("737") || maHuyen.equals("738") || maHuyen.equals("740") || maHuyen.equals("742"))
            {
                //Toast.makeText(MainActivity.this,"http://stnmt.dongnai.gov.vn:8080/arcgis/rest/services/75" + maHuyen + "/" + maXa + "/MapServer",Toast.LENGTH_LONG).show();
                return "http://stnmt.dongnai.gov.vn:8080/arcgis/rest/services/75" + maHuyen + "/" + maXa + "/MapServer";
            }
            else
            {
                //Toast.makeText(MainActivity.this,"http://stnmt.dongnai.gov.vn:8080/arcgis1/rest/services/75" + maHuyen + "/" + maXa + "/MapServer",Toast.LENGTH_SHORT).show();
                return "http://stnmt.dongnai.gov.vn:8080/arcgis1/rest/services/75" + maHuyen + "/" + maXa + "/MapServer";
            }

        }
        catch (Exception ex){}
        return "http://stnmt.dongnai.gov.vn:8080/arcgis/rest/services/75731/25993/MapServer";
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if(isSearchOpened){
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        try {
            //Thua chu
            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK) {
                    {
                        soTo = data.getStringExtra("strSoTo");
                        soThua = data.getStringExtra("strSoThua");
                        TimKiemThuaDat(soTo, soThua);
                    }
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
            //dang nhap
            if (requestCode == 2) {
                if (resultCode == Activity.RESULT_OK) {
                    clsAccount mAcount = (clsAccount) getApplicationContext();
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("AccountSharedPreferences", mAcount.getUserName());
                    editor.putString("PasswordSharedPreferences", mAcount.getPassWord());
                    editor.apply();
                    NavigationView iDangXuat = (NavigationView) findViewById(R.id.navigation_view);
                    iDangXuat.getMenu().findItem(R.id.item_dangXuat).setVisible(true);
                    Toast.makeText(MainActivity.this, String.format("Chào mừng thành viên %s.", mAcount.getFullName()), Toast.LENGTH_LONG).show();
                }
            }
            if (requestCode == 3) {
                if (resultCode == Activity.RESULT_OK) {
                    mMapView = (MapView) findViewById(R.id.mapView);
                    mMapView.removeAllViewsInLayout();
                    _maXa = data.getStringExtra("strMaKvHc");
                    _maHuyen = data.getStringExtra("strMaKvHcCha");
                    _tieuDe = data.getStringExtra("strTenKvHc");
                    actionBar.setTitle(_tieuDe.substring(0, 1).toUpperCase() + _tieuDe.substring(1));
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("MaHuyenSharedPreferences", _maHuyen);
                    editor.putString("MaXaSharedPreferences", _maXa);
                    editor.putString("TieuDeSharedPreferences", _tieuDe);
                    editor.commit();
                    String url = GetMapServicesUrl(_maHuyen, _maXa);
                    String urlSoNha = GetMapServicesUrlSoNha(_maXa);

                    ArcGISMapImageLayer tiledLayer = new ArcGISMapImageLayer(url);
                    ArcGISMapImageLayer tiledLayerSoNha = new ArcGISMapImageLayer(urlSoNha);
                    ArcGISMap map = mMapView.getMap();

                    map.getBasemap().getBaseLayers().add(tiledLayer);
                    map.getBasemap().getBaseLayers().add(tiledLayerSoNha);
                    ZoomToXa(mMapView, _maXa);

                    layDuLieu();
                }
            }

        } catch (Exception ex) {
        }
    }
    private void CheckLastDaTa()
    {
        try
        {
            clsAccount mAcount = (clsAccount) getApplicationContext();
            if( mAcount.getFullName() == null || mAcount.getFullName().isEmpty())
            {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                String maHuyen = sharedPref.getString("MaHuyenSharedPreferences","");
                String maXa = sharedPref.getString("MaXaSharedPreferences","");
                String tieuDe = sharedPref.getString("TieuDeSharedPreferences","");
                if(!maHuyen.isEmpty() && !maXa.isEmpty())
                {
                    _maHuyen= maHuyen;
                    _maXa = maXa;
                    _tieuDe =tieuDe;
                    actionBar.setTitle(_tieuDe.substring(0, 1).toUpperCase() + _tieuDe.substring(1));
                }
                String userName = sharedPref.getString("AccountSharedPreferences","");
                String passWord = sharedPref.getString("PasswordSharedPreferences","");
                if(!userName.isEmpty() && !passWord.isEmpty())
                {
                    DangNhap(userName, passWord);
                }
            }
        }
        catch (Exception ex)
        {

        }
    }
    private void DangNhap(String userName, String passWord)
    {
        try {
            NavigationView iDangXuat = (NavigationView)findViewById( R.id.navigation_view);
            iDangXuat.getMenu().findItem(R.id.item_dangXuat).setVisible(false);
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
                return;
            }
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String imei = "";//telephonyManager.getDeviceId();
            String data = JsonConvert.ConvertQueryThanhVien(userName, passWord, imei);
            String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
            String iv="IVsdfsdfgdf487LT";

            CryptLib cry = new CryptLib();
            String enData = cry.encrypt(data, key, iv);
            String paraEncode = URLEncoder.encode(enData);
            ServiceHandler sh = new ServiceHandler();
            String url =urlServiceThongTin+ "DangNhaEpt?thamSo=" + paraEncode;

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String laThanhVien = jsonObj.getString("laThanhVien");
                    if (laThanhVien.equals("0")) {
                        Toast.makeText(MainActivity.this, "Tên đăng nhập không tồn tại", Toast.LENGTH_LONG).show();
                        return;
                    }
                    //String quyen = jsonObj.getString("quyen");
                    if (laThanhVien.equals("-1")) {
                        Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                        return;
                    }
                    clsAccount mAccount = ((clsAccount) getApplicationContext());
                    mAccount.setUserName(userName);
                    mAccount.setPassWord(passWord);
                    mAccount.setFullName(jsonObj.getString("tenDayDu"));
                    mAccount.setDonViHanhChinh(jsonObj.getString("quyenKVHC"));
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("AccountSharedPreferences", mAccount.getUserName());
                    editor.putString("PasswordSharedPreferences", mAccount.getPassWord());
                    editor.apply();
                    iDangXuat.getMenu().findItem(R.id.item_dangXuat).setVisible(true);
                }
                catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
        }
    }
    private void layDuLieu(){
        AsynTaskDanhSachSoNha asynsonha = new AsynTaskDanhSachSoNha(this);

        try {
            String data = JsonConvert.ConvertQuerySoNha("abc123@LongThanhSoNhahjglkportertgjiuos456789",_maXa);
            String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
            String iv="IVsdfsdfgdf487LT";
            CryptLib cry = new CryptLib();
            String enData = cry.encrypt(data,key,iv);
            String paraEncode = Uri.encode(enData);
            String url=urlServiceThongTin+ "layDanhSachSoNhaEpt?thamSo="+paraEncode;
            lstSoNha= asynsonha.execute(url).get();
            if(lstSoNha!=null)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,lstSoNha);
                edtSeach.setAdapter(adapter);
            }
            edtSeach.setOnItemClickListener(this);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String soNha= adapterView.getItemAtPosition(i).toString();
        String[] arrsonha=soNha.split(";");
        if(arrsonha.length>2)
        {
            String soTo=arrsonha[1].toString();
            String soThua=arrsonha[2].toString();
            TimKiemThuaDat(soTo,soThua);
        }
        //hides the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus() .getWindowToken(),0);
    }
    public UserCredential GetMapCredentialsNen() {
        UserCredential creds=null;
        try
        {
            creds= new UserCredential("nenatlas", "BanDoNenAtlas150715");
        }catch (Exception ex)
        {
        }
        return  creds;

    }
    private void ZoomToXa(MapView mMapView, String maXa)
    {
        try{
            ServiceFeatureTable  mServiceFeatureTableZoom=new ServiceFeatureTable("http://datdai.stnmt.dongnai.gov.vn:8080/atlasadaptordatdai/rest/services/Atlas2015/NEN_KTXH_150715/MapServer/61");

            QueryParameters q = new QueryParameters();
            q.setWhereClause(String.format("MASO =%s",maXa));
            q.setReturnGeometry(true);
            final ListenableFuture<FeatureQueryResult> future = mServiceFeatureTableZoom.queryFeaturesAsync(q);
            future.addDoneListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        FeatureQueryResult result = future.get();
                        Iterator<Feature> resultIterator = result.iterator();
                        if (resultIterator.hasNext()) {
                            Feature feature = resultIterator.next();
                            Envelope en = feature.getGeometry().getExtent();

                            mMapView.setViewpointGeometryAsync(en, 9);
                            // Toast.makeText(getApplicationContext(),feature.getAttributes().get("LOAIDAT").toString(), Toast.LENGTH_LONG).show();

                        } else {
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplication(), "Error=" + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        Log.e(getResources().getString(R.string.app_name),
                                "Error=" + e.getMessage());
                    }
                }
            });

        }
        catch (Exception ex) {
            Log.e( "Error=" ,ex.getMessage());

        }
    }

    public void ZoomToGPS() {
        try {

                if (!_enableGPS) {
                    _enableGPS = true;
                    lDisplayManager.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
                    if (!lDisplayManager.isStarted())
                    {lDisplayManager.startAsync();

                    }
                    lDisplayManager.isShowLocation();

                } else {
                    _enableGPS = false;

                   // lDisplayManager.stop();
                }


        } catch (Exception ex) {
            Log.i("error",ex.getMessage());
            //ex.printStackTrace();
        }
    }

    private  void QueryAndDisplayGraphics(ServiceFeatureTable sublayer, Symbol sublayerSymbol, QueryParameters query,
                                                GraphicsOverlay graphicsOverlay) {
        Toast.makeText(getApplication(),sublayer.getLoadStatus().toString(),
                Toast.LENGTH_SHORT).show();
        if (sublayer.getLoadStatus() == LoadStatus.LOADED) {
            ListenableFuture<FeatureQueryResult> sublayerQuery = sublayer.queryFeaturesAsync(query);
            sublayerQuery.addDoneListener(() -> {
                try {
                    FeatureQueryResult result = sublayerQuery.get();
                    for (Feature feature : result) {
                        Graphic sublayerGraphic = new Graphic(feature.getGeometry(), sublayerSymbol);
                        graphicsOverlay.getGraphics().add(sublayerGraphic);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e(MainActivity.class.getSimpleName(), e.toString());
                }
            });
        }
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }



    @Override
    public void OnClick(View view, int position) {
        switch (position)
        {
            case 1:
                Toast.makeText(getApplication(), "Bản Đồ Vệ Tinh",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplication(), "Bản Đồ Đường",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}