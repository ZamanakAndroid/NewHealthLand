package com.zamanak.healthland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjc1NzAzNywidXNlcl9pZCI6NzU3MDM3LCJlbWFpbCI6ImFib3phci5yYWdoaWJkb2lzdEBnbWFpbC5jb20iLCJmb3JldmVyIjpmYWxzZSwiaXNzIjoiaHR0cDpcL1wvYXZhc2RwLnNoYW1pbXNhbGFtYXQuaXJcL2FwaVwvdjJcL2F1dGhcL3JlZ2lzdGVyLWFuZC1sb2dpbiIsImlhdCI6MTUyOTQxMjg4MSwiZXhwIjoxODQ0NzcyODgxLCJuYmYiOjE1Mjk0MTI4ODEsImp0aSI6IjdhMTdhNjkxOTFmNmQ2ZjA0MDA4MGExMTNmMmRmYmZjIn0.lShm4ChaEYNoyqmDhrs1rHoH3ULHNQZpW-44U1KOJh0";
        String baseApiKey="b8c30f784ba9e0d1c384392cb930f6ad8139d512fec670666d94dbade03fa3f6";
        LandOfHealthSDK.sharedLandOfHealth().startLandOfHealthActivity(this,token,baseApiKey);
    }
}
