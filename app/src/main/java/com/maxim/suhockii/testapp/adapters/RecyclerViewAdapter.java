package com.maxim.suhockii.testapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxim.suhockii.testapp.R;
import com.maxim.suhockii.testapp.catalog.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hzkto on 11/9/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Offer> offers;

    public RecyclerViewAdapter(Context context, List<Offer> offers) {
        this.context = context;
        this.offers = offers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(offers.get(position).mPictureUrl).into(holder.ivPhoto);
        holder.tvName.setText(offers.get(position).mName);
        holder.tvPrice.setText(String.valueOf(offers.get(position).mPrice));
        if (offers.get(position).mParamList != null) {
            holder.tvWeight.setText(String.valueOf(offers.get(position).mParamList));
        }
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvWeight, tvPrice;
        ImageView ivPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvWeight = (TextView) itemView.findViewById(R.id.tv_weight);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}
