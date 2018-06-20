package com.zamanak.healthland.api.object;

/**
 * Created by zamanak on 4/29/2018.
 */

public class GetAppDetailedRequest {

    private String id;

    public GetAppDetailedRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


}
