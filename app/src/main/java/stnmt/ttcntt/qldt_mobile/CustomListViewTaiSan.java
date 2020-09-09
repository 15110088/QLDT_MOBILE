package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 6/1/2016.
 */
public class CustomListViewTaiSan extends ArrayAdapter<clsTaiSan> {

    Activity context = null;
    ArrayList<clsTaiSan> myArray =null;
    int layoutId;

    public CustomListViewTaiSan(Activity context, int layoutId, ArrayList<clsTaiSan> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArray.size()>0 && position>=0)
        {
            TextView txtTaiSanThuaDat =(TextView)convertView.findViewById(R.id.txtTaiSanThuaDat);
            clsTaiSan taiSan= myArray.get(position);

            String ten = taiSan.ten;
            String mota = taiSan.moTa;

            String strKq = String.format("<b>Tên tài sản:  <font color='#336699'> %s</font> </b><br>" +
                    "%s",ten, mota);
            txtTaiSanThuaDat.setText(Html.fromHtml(strKq));
        }
        return convertView;
    }
}
