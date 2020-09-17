package stnmt.ttcntt.qldt_mobile.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import stnmt.ttcntt.qldt_mobile.Interface.ItemClickListener;
import stnmt.ttcntt.qldt_mobile.R;
import stnmt.ttcntt.qldt_mobile.clsThuaDat;

public class quyhoachAdapter extends RecyclerView.Adapter<quyhoachAdapter.ViewHolder> {

    Context c;
    List<clsThuaDat> dataThuaDat;
    private ItemClickListener itemClickListener;

    public quyhoachAdapter(Context c,List<clsThuaDat> data,ItemClickListener _iItemClickListener) {
        this.c=c;
        this.dataThuaDat=data;
        this.itemClickListener=_iItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        row=inflater.inflate(R.layout.row_item_quyhoach,parent,false);

        Log.e("TypeView", (String.valueOf(viewType)));
//        switch (viewType)
//        {
//
//            case 0:
//                row=inflater.inflate(R.layout.row_item_quyhoach,parent,false);
//                break;
//            case 1:
//                row=inflater.inflate(R.layout.row_item_quyhoach_null,parent,false);
//
//                break;
//            default:
//                row=inflater.inflate(R.layout.row_item_quyhoach_null,parent,false);
//                break;
//
//        }

        return new ViewHolder(row);
    }

    @Override
    public int getItemViewType(int position) {
        if(dataThuaDat != null &&dataThuaDat.size()>0)
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataThuaDat != null &&dataThuaDat.size()>0)
        {
            clsThuaDat td=this.dataThuaDat.get(position);
            holder.txtQH.setText(td.getTenVungQuyHoach());
            holder.txtLo.setText(td.getLoChucNang());
            holder.txtO.setText(td.getoPho());
            holder.txtMatDoXayDung.setText(td.getMatDoXD());
            holder.txtTangXayDung.setText(td.getTangCaoXayDung());
            holder.txtDanSo.setText(td.getDanSo());
            holder.txtDienTich.setText(td.getDienTichQuyHoach()+" (ha)");
            holder.txtMDSD.setText(td.getMaMDSDQuyHoach());
            holder.txtDienTichGiao.setText(td.getDienTichGiao()+ " (m²)");

        }
    }


    @Override
    public int getItemCount() {
        return dataThuaDat.size();
    }




    public class  ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtQH,txtLo,txtO,txtTangXayDung,txtMatDoXayDung,txtDanSo,txtDienTich,txtDienTichGiao,txtMDSD;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            if(dataThuaDat != null &&dataThuaDat.size()>0)
            {
                txtQH = itemView.findViewById(R.id.txtQuyHoachItem);
                txtLo = itemView.findViewById(R.id.txtLo);
                txtO = itemView.findViewById(R.id.txtO);
                txtTangXayDung = itemView.findViewById(R.id.txtTangXayDung);
                txtMatDoXayDung = itemView.findViewById(R.id.txtMatDoXayDung);
                txtDanSo = itemView.findViewById(R.id.txtDanSo);
                txtDienTich = itemView.findViewById(R.id.txtdientich);
                txtDienTichGiao = itemView.findViewById(R.id.txtdientichGiao);
                txtMDSD=itemView.findViewById(R.id.txtMDSD);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onClick(v,getAdapterPosition(),false);

                    }
                });
            }
        }

    }
}
