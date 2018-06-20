package com.zamanak.healthland.activity;

import android.os.Bundle;

import com.zamanak.healthland.fragment.HomeHealthLandFragment;
import com.zamanak.landofhealth.R;

public class HealthLandActivity extends BaseActivityNew {

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        pushFragment(HomeHealthLandFragment.class, R.id.fragment);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_health_land;
    }

    @Override
    protected boolean isRtl() {
        return true;
    }

    @Override
    protected boolean isPortrait() {
        return true;
    }

    @Override
    protected void setListener() {
    }
}
