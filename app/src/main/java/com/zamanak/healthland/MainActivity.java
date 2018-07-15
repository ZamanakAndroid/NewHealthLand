package com.zamanak.healthland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // please send token and base api key and base url
        LandOfHealthSDK.sharedLandOfHealth().startLandOfHealthActivity(this,"","","");
    }
}
