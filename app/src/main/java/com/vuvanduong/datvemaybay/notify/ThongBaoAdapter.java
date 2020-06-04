package com.vuvanduong.datvemaybay.notify;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vuvanduong.datvemaybay.R;

import java.util.List;

public class ThongBaoAdapter extends ArrayAdapter<ThongBao> {

    Activity context;
    int resource;
    List<ThongBao> objects;

    public ThongBaoAdapter(@NonNull Activity context, int resource, @NonNull List<ThongBao> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ThongBao getItem(int position) {
        return objects.get(position);
    }

    static class ViewHolder {
        TextView txtTieuDeTB,txtNoiDungTB,txtThoiGian;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            holder = new ViewHolder();

            holder.txtTieuDeTB = convertView.findViewById(R.id.txtTieuDeTB);
            holder.txtNoiDungTB = convertView.findViewById(R.id.txtNoiDungTB);
            holder.txtThoiGian = convertView.findViewById(R.id.txtThoiGian);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ThongBao thongBao = this.objects.get(position);
        holder.txtTieuDeTB.setText(thongBao.getTieuDe());

        String context = thongBao.getNoiDung();
        if (context.length() > 25) {
            context = context.substring(0, 25) + "...";
        }
        holder.txtNoiDungTB.setText(context);

        holder.txtThoiGian.setText(thongBao.getNgayBatDau());

        return convertView;
    }



}
