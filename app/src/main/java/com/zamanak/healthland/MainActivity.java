package com.zamanak.healthland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjc1NzAzNywidXNlcl9pZCI6NzU3MDM3LCJlbWFpbCI6ImFib3phci5yYWdoaWJkb2lzdEBnbWFpbC5jb20iLCJmb3JldmVyIjpmYWxzZSwiaXNzIjoiaHR0cDpcL1wvYXZhc2RwLnNoYW1pbXNhbGFtYXQuaXJcL2FwaVwvdjJcL2F1dGhcL3JlZ2lzdGVyLWFuZC1sb2dpbiIsImlhdCI6MTUyNDU2NDc5MiwiZXhwIjoxODM5OTI0NzkyLCJuYmYiOjE1MjQ1NjQ3OTIsImp0aSI6ImZkZDY2Yjc2MjZkNjEzMGVlOTRkNjRkNDBlZTI3NGU3In0.cu6WRwgWFTnUw7yS_yC8Oc0kKWhWsKEswNv3QYpUJjI";
    private String baseApiKey = "b8c30f784ba9e0d1c384392cb930f6ad8139d512fec670666d94dbade03fa3f6";
    public String BASE_URL = "http://avasdp.shamimsalamat.ir/api/v2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // please send token and base api key and base url
        LandOfHealthSDK.sharedLandOfHealth().startLandOfHealthActivity(this,token,baseApiKey,BASE_URL);
    }
}
