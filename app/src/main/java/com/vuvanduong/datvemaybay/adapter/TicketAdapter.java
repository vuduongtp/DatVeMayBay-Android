package com.vuvanduong.datvemaybay.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.object.SanBay;
import com.vuvanduong.datvemaybay.object.Ve;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ve> {

    Activity context;
    int resource;
    List<Ve> objects;

    public TicketAdapter(@NonNull Activity context, int resource, @NonNull List<Ve> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public Ve getItem(int position) {
        return objects.get(position);
    }

    static class ViewHolder {
        TextView txtBayTu,txtBayDen,txtHoTenVe,txtChuyenBayVe,txtNgayBayVe,txtSoGheVe,txtGioKHoiHanhVe,txtGioKetThucVe;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            holder = new ViewHolder();

            holder.txtBayDen = convertView.findViewById(R.id.txtBayDen);
            holder.txtBayTu = convertView.findViewById(R.id.txtBayTu);
            holder.txtChuyenBayVe = convertView.findViewById(R.id.txtChuyenBayVe);
            holder.txtHoTenVe = convertView.findViewById(R.id.txtHoTenVe);
            holder.txtNgayBayVe = convertView.findViewById(R.id.txtNgayBayVe);
            holder.txtSoGheVe = convertView.findViewById(R.id.txtSoGheVe);
            holder.txtGioKHoiHanhVe = convertView.findViewById(R.id.txtGioKHoiHanhVe);
            holder.txtGioKetThucVe = convertView.findViewById(R.id.txtGioKetThucVe);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Ve ve = this.objects.get(position);
        holder.txtBayDen.setText(ve.getBayDen());
        holder.txtBayTu.setText(ve.getBayTu());
        holder.txtChuyenBayVe.setText(ve.getChuyenBay());
        holder.txtHoTenVe.setText(ve.getNguoiDat());
        holder.txtNgayBayVe.setText(ve.getNgayBay());
        holder.txtSoGheVe.setText(ve.getSoGhe());
        holder.txtGioKHoiHanhVe.setText(ve.getGioKhoiHanh());
        holder.txtGioKetThucVe.setText(ve.getGioKetThuc());

        return convertView;
    }



}
