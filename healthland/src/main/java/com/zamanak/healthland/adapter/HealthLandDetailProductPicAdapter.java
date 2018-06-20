package com.zamanak.healthland.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HealthLandDetailProductPicAdapter extends RecyclerView.Adapter<HealthLandDetailProductPicAdapter.myViewHolder> {

    private ArrayList<String> items;
    private Context context;
    private OnItemClickListener listener;
    private int type;

    public HealthLandDetailProductPicAdapter(Context context, ArrayList<String> item, OnItemClickListener listener, int type) {
        this.items = item;
        this.context = context;
        this.listener = listener;
        this.type = type;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }


    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        String obj = items.get(position);
        Glide.with(context).load(obj).into(holder.ivAppImages);

        holder.bind(position,listener);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (type==1){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_land_health_detail_product_pic, viewGroup, false);
            return new myViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_land_health_detail_product_gallery, viewGroup, false);
            return new myViewHolder(itemView);

        }

    }


    static class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivAppImages;
        //private AppCompatImageView ivAppImages;

        myViewHolder(View convertView) {
            super(convertView);

            ivAppImages = convertView.findViewById(R.id.iv_app_images);
            //ButterKnife.bind(this,convertView);

        }
        public void bind(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }


    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}