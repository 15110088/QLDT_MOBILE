package stnmt.ttcntt.qldt_mobile.Util;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import stnmt.ttcntt.qldt_mobile.AsynTaskModalThongTinThuaDat;
import stnmt.ttcntt.qldt_mobile.CryptLib;
import stnmt.ttcntt.qldt_mobile.JsonConvert;
import stnmt.ttcntt.qldt_mobile.R;
import stnmt.ttcntt.qldt_mobile.clsUrl;

public class BottomSheetCustom extends BottomSheetDialogFragment {
    String _soTo="";
    String _soThua="";
    String _dienTich="";
    String _loaiDat="";
    String _maXa="";

    private AppBarLayout appBarLayout;
    private LinearLayout linearLayout;
    public  BottomSheetCustom(String maXa,String soTo,String soThua,String dientich,String loaiDat)
    {


        this._soTo=soTo;
        this._soThua=soThua;
        this._dienTich=dientich;
        this._loaiDat=loaiDat;
        this._maXa=maXa;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view =View.inflate(getContext(), R.layout.bottom_sheet,null);
        dialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior =BottomSheetBehavior.from((View) view.getParent());


        bottomSheetBehavior.setPeekHeight(bottomSheetBehavior.PEEK_HEIGHT_AUTO);



        boolean coQuyen=true;
        String data = JsonConvert.ConvertQueryThua(_maXa, Integer.parseInt(_soTo), _soThua);
        String key = "SoNhaajlkuoin1285sdfjk9LongThanh";
        String iv="IVsdfsdfgdf487LT";
        String url ="";
        try {
            CryptLib cry = new CryptLib();
            String enData = cry.encrypt(data, key, iv);

            String paraEncode = Uri.encode(enData);
            // Bundle bundle = getIntent().getExtras();
            String urlServiceThongTin="http://192.169.3.197/DTBienHoa/ServicesViTri.svc/";
            url = urlServiceThongTin+ "LayThongTinQuyHoach?thamSo="+paraEncode;
            clsUrl clsTT = new clsUrl(url,coQuyen,_dienTich,_loaiDat);
            AsynTaskModalThongTinThuaDat asynLayTT = new AsynTaskModalThongTinThuaDat(view,_soTo,_soThua,_dienTich,_loaiDat,paraEncode);
            asynLayTT.execute(clsTT);
        }
        catch (Exception e)
        {

        }


        linearLayout= view.findViewById(R.id.lyt_layout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(bottomSheetBehavior.STATE_EXPANDED==1)
                {
                    HideView(linearLayout);
                }
                if(bottomSheetBehavior.STATE_COLLAPSED==1)
                {
                    ShowView(linearLayout,GetActionBarsize());
                }
                if(bottomSheetBehavior.STATE_HIDDEN==1)
                {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        return dialog;
    }

    private void HideView(View view) {
        ViewGroup.LayoutParams params=view.getLayoutParams();
        params.height=0;
        view.setLayoutParams(params);

    }
    private void ShowView(View view,int size) {
        ViewGroup.LayoutParams params=view.getLayoutParams();
        params.height=size;
        view.setLayoutParams(params);

    }
    private int GetActionBarsize()
    {
        final TypedArray typedArray=getContext().getTheme().obtainStyledAttributes(new int[]{
            R.attr.actionBarSize
        });
        return  (int) typedArray.getDimension(0,0);
    }
}
