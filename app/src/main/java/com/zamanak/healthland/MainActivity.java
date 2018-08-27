package com.zamanak.healthland;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zamanak.healthland.activity.BaseActivityNew;

public class MainActivity extends BaseActivityNew {

    private AppCompatTextView tvHello;

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        tvHello = findViewById(R.id.tvHello);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LandOfHealthSDK.sharedLandOfHealth().startLandOfHealthActivity(MainActivity.this,"","","");
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
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
