package com.zamanak.healthland.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zamanak.landofhealth.R;


/**
 * Created by PIRI on 4/28/2018.
 */
public class RowHealthLandViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTitleTextView;
    public ImageView itemImageView;
    public CardView layout;

    public RowHealthLandViewHolder(View itemView) {
        super(itemView);
        itemTitleTextView = itemView.findViewById(R.id.itemTitleTextView);
        itemImageView = itemView.findViewById(R.id.itemImageView);
        layout = itemView.findViewById(R.id.layout);
    }
}
