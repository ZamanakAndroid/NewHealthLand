package com.zamanak.healthland.api.request;

import android.content.Context;

import com.zamanak.healthland.LandOfHealthSDK;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.Urls;
import com.zamanak.healthland.api.object.PostInsertComment;
import com.zamanak.healthland.utils.Constants;

import org.json.JSONException;

/**
 * Created by PIRI on 4/21/2018.
 */

public class RequestPostInsertComment extends BaseApi {

    private PostInsertComment item;

    public RequestPostInsertComment(Context context, ApiSuccessCB outerSuccessCB,
                                    ApiErrorCB outerErrorCB, PostInsertComment item) {

        super(context, LandOfHealthSDK.getBASE_URL(), LandOfHealthSDK.getINSERT_COMMENT(), outerSuccessCB, outerErrorCB,
                true, true);
        this.api_key = LandOfHealthSDK.getBaseApiKey();
        this.token   = LandOfHealthSDK.getTOKEN();
        this.item = item;
    }

    @Override
    protected void prepareRequest() throws JSONException {
        reqJson.put("appId", item.getAppId());
        reqJson.put("commentText", item.getCommentText());
    }
}