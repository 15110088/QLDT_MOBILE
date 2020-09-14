package stnmt.ttcntt.qldt_mobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stnmt.ttcntt.qldt_mobile.R;
import stnmt.ttcntt.qldt_mobile.clsThuaDat;

public class quyhoachAdapter extends RecyclerView.Adapter<quyhoachAdapter.ViewHolder> {

    Context c;
    List<clsThuaDat> dataThuaDat;
    public quyhoachAdapter(Context c,List<clsThuaDat> data) {
        this.c=c;
        this.dataThuaDat=data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.row_item_quyhoach,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        clsThuaDat td=this.dataThuaDat.get(position);
        holder.lblQHtest.setText(td.getTenVungQuyHoach());
    }

    @Override
    public int getItemCount() {
        return dataThuaDat.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder {
        TextView lblQHtest;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            lblQHtest=itemView.findViewById(R.id.txtQuyHoachItem);
            String a="";
        }
    }
}
