package stnmt.ttcntt.qldt_mobile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PTCN_08 on 5/21/2016.
 */
public class CustomListViewXa extends ArrayAdapter<clsKvHc> {

    Activity context = null;
    ArrayList<clsKvHc> myArray =null;
    int layoutId;

    public CustomListViewXa(Activity context, int layoutId, ArrayList<clsKvHc> arr){
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
            final TextView txtdisplay=(TextView)convertView.findViewById(R.id.txtXa);
            final clsKvHc oKvhc=myArray.get(position);
            txtdisplay.setText(oKvhc.ten.toString());
            txtdisplay.setTag(oKvhc.maKvhc);

            ImageView img = (ImageView)convertView.findViewById(R.id.imgXa);
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.mapxa));
            img.setTag(oKvhc.maKvhcCha);
        }
        return convertView;
    }




}
