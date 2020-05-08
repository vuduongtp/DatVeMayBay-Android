package com.vuvanduong.datvemaybay.adapter;

import android.app.Activity;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class AirportAdapter extends ArrayAdapter<SanBay> implements Filterable {

    Activity context;
    int resource;
    List<SanBay> objects;
    private List<SanBay> filteredData = null;
    private ItemFilter mFilter = new ItemFilter();

    public AirportAdapter(@NonNull Activity context, int resource, @NonNull List<SanBay> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.filteredData=objects;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Nullable
    @Override
    public SanBay getItem(int position) {
        return filteredData.get(position);
    }

    static class ViewHolder {
        TextView txtIdAirport;
        TextView txtName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            holder = new ViewHolder();

            holder.txtIdAirport = convertView.findViewById(R.id.txtIdAirport);
            holder.txtName = convertView.findViewById(R.id.txtNamePlace);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        SanBay sanBay = this.filteredData.get(position);
        holder.txtIdAirport.setText(sanBay.getMaSanBay());
        holder.txtName.setText(sanBay.getThanhPho() + " - " + sanBay.getQuocGia());

        return convertView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<SanBay> list = objects;

            int count = list.size();
            final ArrayList<SanBay> nlist = new ArrayList<SanBay>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getThanhPho();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<SanBay>) results.values;
            notifyDataSetChanged();
        }
    }
}
