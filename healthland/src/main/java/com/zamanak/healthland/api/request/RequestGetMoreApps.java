package com.zamanak.healthland.api.request;

import android.content.Context;


import com.zamanak.healthland.LandOfHealthSDK;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.Urls;
import com.zamanak.healthland.utils.Constants;

import org.json.JSONException;

/**
 * Created by PIRI on 4/30/2018.
 */
public class RequestGetMoreApps extends BaseApi {

    private String catName;
    private int limit;
    private int offset;

    public RequestGetMoreApps(Context context, ApiSuccessCB apiSuccessCB, ApiErrorCB apiErrorCB,
                              String catName, int limit, int offset) {

        super(context, Urls.BASE_URL, Urls.LOAD_MORE_APPS, apiSuccessCB, apiErrorCB, true,
                false);
        this.catName = catName;
        this.limit = limit;
        this.offset = offset;
        this.api_key = LandOfHealthSDK.getBaseApiKey();
    }

    @Override
    protected void prepareRequest() throws JSONException {

        reqJson.put("categoryNameEn", catName);
        reqJson.put("limit", limit);
        reqJson.put("offset", offset);
    }
}
