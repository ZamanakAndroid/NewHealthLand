package com.zamanak.healthland.api.result;


import java.util.ArrayList;

/**
 * Created by zamanak on 4/29/2018.
 */

public class AppDetailedRes {

    private AppRes app;
    private ArrayList<AppDetailedComments> comments;

    public AppRes getApp() {
        return app;
    }

    public ArrayList<AppDetailedComments> getComments() {
        return comments;
    }
}
