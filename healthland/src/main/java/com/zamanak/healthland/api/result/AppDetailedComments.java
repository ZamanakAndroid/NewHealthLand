package com.zamanak.healthland.api.result;


/**
 * Created by zamanak on 4/29/2018.
 */

public class AppDetailedComments {

    private String id;
    private String userId;
    private String userName;
    private String appId;
    private String commentText;
    private String shamsiDate;
    private SuccessUserStar userStar;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAppId() {
        return appId;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getShamsiDate() {
        return shamsiDate;
    }

    public SuccessUserStar getUserStar() {
        return userStar;
    }
}
