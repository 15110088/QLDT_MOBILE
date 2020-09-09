package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by PTCN_08 on 6/1/2016.
 */
public class CustomListViewLichSuThuaDat extends ArrayAdapter<clsLichSuThuaDat> {

    Activity context = null;
    ArrayList<clsLichSuThuaDat> myArray =null;
    int layoutId;

    public CustomListViewLichSuThuaDat(Activity context, int layoutId, ArrayList<clsLichSuThuaDat> arr){
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
            TextView txtNgayThucHien=(TextView)convertView.findViewById(R.id.txtNgayBienDong);
            TextView txtNoiDungBienDong=(TextView)convertView.findViewById(R.id.txtNoiDungBienDong);
            clsLichSuThuaDat lichSuThuaDat=myArray.get(position);

            String noiDung = lichSuThuaDat.noiDungBienDong;
            String ngay = new SimpleDateFormat("dd/MM/yyyy").format(lichSuThuaDat.ngayBienDong);
            txtNgayThucHien.setText("Ngày: " + ngay);
            txtNoiDungBienDong.setText(noiDung);
            /*
            String strNoiDung = String.format("Ngày : %s . %s",
                        new SimpleDateFormat("dd/MM/yyyy").format(lichSuThuaDat.ngayBienDong),
                        lichSuThuaDat.noiDungBienDong
                    );
            txtNgayThucHien.setText(strNoiDung); */

        }
        /*if(position%2==0)
            convertView.setBackgroundColor(Color.WHITE);
        else
            convertView.setBackgroundColor(Color.rgb(246,245,245));*/

        return convertView;
    }
}
