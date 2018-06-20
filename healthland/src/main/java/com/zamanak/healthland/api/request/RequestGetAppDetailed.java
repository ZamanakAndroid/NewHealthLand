package com.zamanak.healthland.api.request;

import android.content.Context;

import com.zamanak.healthland.LandOfHealthSDK;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.Urls;
import com.zamanak.healthland.api.object.GetAppDetailedRequest;
import com.zamanak.healthland.utils.Constants;

import org.json.JSONException;

/**
 * Created by PIRI on 4/21/2018.
 */

public class RequestGetAppDetailed extends BaseApi {

    private GetAppDetailedRequest item;

    public RequestGetAppDetailed(Context context, ApiSuccessCB outerSuccessCB,
                                 ApiErrorCB outerErrorCB, GetAppDetailedRequest item) {

        super(context, LandOfHealthSDK.getBASE_URL(), LandOfHealthSDK.getGET_App_DETAILED(), outerSuccessCB, outerErrorCB,
                true, true);
        this.api_key = LandOfHealthSDK.getBaseApiKey();
        this.token = LandOfHealthSDK.getTOKEN();
        this.item = item;
    }

    @Override
    protected void prepareRequest() throws JSONException {
        reqJson.put("id", item.getId());
    }
}