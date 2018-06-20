package com.zamanak.healthland.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zamanak.landofhealth.R;


/**
 * Created by PIRI on 4/28/2018.
 */
public class MainHealthLandViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView;
    public RecyclerView innerRecyclerView;
    public TextView showAllTextView;

    public MainHealthLandViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        innerRecyclerView = itemView.findViewById(R.id.innerRecyclerView);
        showAllTextView = itemView.findViewById(R.id.showAllTextView);
    }
}
