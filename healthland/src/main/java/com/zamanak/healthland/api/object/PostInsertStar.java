package com.zamanak.healthland.api.object;

/**
 * Created by zamanak on 4/30/2018.
 */

public class PostInsertStar {

    private String appId;
    private float star;

    public PostInsertStar(String appId, float star) {
        this.appId = appId;
        this.star = star;
    }

    public String getAppId() {
        return appId;
    }

    public float getStar() {
        return star;
    }
}
