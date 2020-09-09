package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.graphics.Color;
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
public class CustomListViewThongTinChuThua extends ArrayAdapter<clsThongTinThuaChu> {

    Activity context = null;
    ArrayList<clsThongTinThuaChu> myArray =null;
    int layoutId;

    public CustomListViewThongTinChuThua(Activity context, int layoutId, ArrayList<clsThongTinThuaChu> arr){
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
            TextView txtThongTinThuaChu =(TextView)convertView.findViewById(R.id.txtThongTinThuaChu);
            clsThongTinThuaChu  thuaChu  = myArray.get(position);
            txtThongTinThuaChu.setTag(thuaChu);

            String soNha = String.format("<b>Số nhà: </b>%s",thuaChu.soTo);
            String soToThua = String.format("<b>Số tờ: </b>%s  <b>Số thửa: </b> %s",thuaChu.soTo,thuaChu.soThua);
            String diaChi = String.format("<b>Địa chỉ: </b> %s",thuaChu.diaChi);

            String strNoiDung = String.format("%s <br>" +
                                                "%s <br>" +
                                                "%s",soNha, soToThua,diaChi);
            txtThongTinThuaChu.setText(Html.fromHtml(strNoiDung));
        }
        if(position%2==0)
            convertView.setBackgroundColor(Color.rgb(238, 233, 233));
        else
            convertView.setBackgroundColor(Color.rgb(255, 255, 255));

        return convertView;
    }
}
