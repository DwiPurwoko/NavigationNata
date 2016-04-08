package com.example.administrator.navigationnata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.navigationnata.R;
import com.example.administrator.navigationnata.model.Product;

import java.util.List;


/**
 * Created by Administrator on 4/4/2016.
 */
public class ListPropertyAdapter extends BaseAdapter {

    private Context context;
    private List<Product> list;
    private ListProductHolder holder;

    public ListPropertyAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_property, null);
            holder = new ListProductHolder();
            holder.refProperty = (TextView) convertView.findViewById(R.id.refProperty);
            holder.nameProperty = (TextView) convertView.findViewById(R.id.nameProperty);

            convertView.setTag(holder);
        } else {
            holder = (ListProductHolder) convertView.getTag();
        }
        Product product = list.get(position);

        holder.refProperty.setText(product.getId());
        holder.nameProperty.setText(product.getName());

        return convertView;
    }

    private class ListProductHolder {
        TextView refProperty, nameProperty;
    }
}