package com.zamanak.healthland;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zamanak.healthland.activity.BaseActivityNew;

public class MainActivity extends BaseActivityNew {

    private AppCompatTextView tvHello;
    private String baseApiKey= "b8c30f784ba9e0d1c384392cb930f6ad8139d512fec670666d94dbade03fa3f6";
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3NGM0ODEzN2Q2ZmVkZDM1YjAzOWYwN2NjNjNkZmQxNiIsImlzcyI6Imh0dHBzOi8vYXBpLnphZXJhcHAuY29tL2FwaS92Mi9hdXRoL3JlZ2lzdGVyLWFuZC1sb2dpbi12MyIsImlhdCI6MTU2NzIzMDUzNywiZXhwIjoxODgyNTkwNTM3LCJuYmYiOjE1NjcyMzA1MzcsImp0aSI6InhOSWJieWtBeGxNOFFvWVciLCJ1c2VyX2lkIjozMjExMjQsImZvcmV2ZXIiOmZhbHNlfQ.o7HS0j3POQz-kMmltkA9J7b7QobsJV66GUCk95Ec_QM";
    private String baseUrl = "https://api.zaerapp.com/api/v2";

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        tvHello = findViewById(R.id.tvHello);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LandOfHealthSDK.sharedLandOfHealth().startLandOfHealthActivity(MainActivity.this,token,baseApiKey,baseUrl);
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
