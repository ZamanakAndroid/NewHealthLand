package com.zamanak.healthland.activity;

import android.os.Bundle;

import com.zamanak.healthland.fragment.LandOfHealthDetailFragment;
import com.zamanak.landofhealth.R;

public class LandOfHealthDetailActivity extends BaseActivityNew {

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        pushFragment(LandOfHealthDetailFragment.class, R.id.fragment);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_land_health;
    }

    @Override
    protected boolean isRtl() {
        return false;
    }

    @Override
    protected boolean isPortrait() {
        return false;
    }

    @Override
    protected void setListener() {

    }


}
