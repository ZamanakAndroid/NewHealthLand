package com.zamanak.healthland.api.request;

import android.content.Context;

import com.zamanak.healthland.LandOfHealthSDK;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.Urls;
import com.zamanak.healthland.utils.Constants;


/**
 * Created by PIRI on 4/29/2018.
 */
public class RequestGetMainHealthLand extends BaseApi {

    public RequestGetMainHealthLand(Context context, ApiSuccessCB apiSuccessCB,
                                    ApiErrorCB apiErrorCB) {

        super(context, LandOfHealthSDK.getBASE_URL(), LandOfHealthSDK.getGET_MAIN_PAGE(), apiSuccessCB, apiErrorCB,
                false, true);
        this.api_key = LandOfHealthSDK.getBaseApiKey();
        this.token = LandOfHealthSDK.getTOKEN();
    }
}
