package com.zamanak.healthland.api.object;

/**
 * Created by zamanak on 5/3/2018.
 */

public class AppDetailLoadMoreComments {

    private String appId;
    private int limit;
    private int offset;

    public AppDetailLoadMoreComments(String appId, int limit, int offset) {
        this.appId = appId;
        this.limit = limit;
        this.offset = offset;
    }

    public String getAppId() {
        return appId;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
