package com.vuvanduong.datvemaybay.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vuvanduong.datvemaybay.R;
import com.vuvanduong.datvemaybay.object.FamousPlace;

import java.util.List;


public class FamousPlaceAdapter extends ArrayAdapter<FamousPlace> {

    Activity context;
    int resource;
    List<FamousPlace> objects;

    public FamousPlaceAdapter(@NonNull Activity context, int resource, @NonNull List<FamousPlace> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = inflater.inflate(this.resource,null);

        ImageView img = row.findViewById(R.id.imgFamousPlace);
        TextView txt = row.findViewById(R.id.txtNameFamousPlace);
        img.setImageResource(this.objects.get(position).getImage());
        txt.setText(this.objects.get(position).getName());

        return row;
    }
}
