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
import com.vuvanduong.datvemaybay.object.ChuyenBay;
import com.vuvanduong.datvemaybay.object.SanBay;

import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends ArrayAdapter<ChuyenBay> {

    Activity context;
    int resource;
    List<ChuyenBay> objects;
    boolean isFromSelectFight;

    public FlightAdapter(@NonNull Activity context, int resource, @NonNull List<ChuyenBay> objects,boolean isFromSelectFight) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.isFromSelectFight=isFromSelectFight;
    }


    static class ViewHolder {
        TextView txtSoHieu;
        TextView txtChuyenDi;
        TextView txtGiaHoacTrangThai;
        TextView txtTimeStartExpected;
        TextView txtTimeEndExpected;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            holder = new ViewHolder();

            holder.txtSoHieu = convertView.findViewById(R.id.txtSoHieu);
            holder.txtChuyenDi = convertView.findViewById(R.id.txtChuyenDi);
            holder.txtGiaHoacTrangThai = convertView.findViewById(R.id.txtGiaHoacTrangThai);
            holder.txtTimeStartExpected = convertView.findViewById(R.id.txtTimeStartExpected);
            holder.txtTimeEndExpected = convertView.findViewById(R.id.txtTimeEndExpected);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChuyenBay chuyenBay = this.objects.get(position);
        holder.txtSoHieu.setText(chuyenBay.getMaChuyenBay());
        holder.txtChuyenDi.setText(chuyenBay.getSanBayDi()+" - "+chuyenBay.getSanBayDen());
        if (isFromSelectFight) {
            holder.txtGiaHoacTrangThai.setText(String.valueOf(chuyenBay.getGiaVe()) + " đ");
        }else {
            holder.txtGiaHoacTrangThai.setText("Hoạt động");
        }
        holder.txtTimeStartExpected.setText(chuyenBay.getThoiGianDiDuKien());
        holder.txtTimeEndExpected.setText(chuyenBay.getThoiGianDenDuKien());


        return convertView;
    }

}
