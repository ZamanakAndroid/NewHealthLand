package com.zamanak.healthland.api.object;

/**
 * Created by zamanak on 4/29/2018.
 */

public class PostAppIdRequest {

    private String appId;

    public PostAppIdRequest(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }
}
