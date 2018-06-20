package com.zamanak.healthland.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.zamanak.landofhealth.R;

/**
 * Created by PIRI on 4/28/2018.
 */
public class SliderHealthLandViewHolder extends RecyclerView.ViewHolder {


    public SliderLayout slider;

    public SliderHealthLandViewHolder(View itemView) {
        super(itemView);
        slider = itemView.findViewById(R.id.slider);
    }
}
