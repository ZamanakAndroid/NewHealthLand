package com.zamanak.healthland.api.object;

/**
 * Created by zamanak on 5/1/2018.
 */

public class PostInsertComment {

    private String appId;
    private String commentText;

    public PostInsertComment(String appId, String commentText) {
        this.appId = appId;
        this.commentText = commentText;
    }

    public String getAppId() {
        return appId;
    }

    public String getCommentText() {
        return commentText;
    }
}
