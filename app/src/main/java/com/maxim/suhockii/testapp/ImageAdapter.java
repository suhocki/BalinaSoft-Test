package com.maxim.suhockii.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hzkto on 11/7/2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private LayoutInflater layoutInflater;
    List<Category> categories;

    public ImageAdapter(Context context, int resource, List<Category> categories) {
        this.context = context;
        this.resource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    public int getCount() {
        return categories.size();
    }

    public Object getItem(int position) {
        return categories.get(position);
    }

    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = this.layoutInflater.inflate(this.resource, parent, false);
        } else {
            view = convertView;
        }
        ImageView iv_adapter = (ImageView) view.findViewById(R.id.iv_adapter);
        iv_adapter.setImageResource(this.categories.get(position).getImageResource());
        TextView tv_adapter = (TextView) view.findViewById(R.id.tv_adapter);
        tv_adapter.setText(this.categories.get(position).getText());

        return view;
    }
}
