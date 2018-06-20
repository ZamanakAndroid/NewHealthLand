package com.zamanak.healthland.api.result;

import java.util.ArrayList;

/**
 * Created by zamanak on 5/3/2018.
 */

public class ResultLoadMoreComments {

    private int code;
    private String error;
    private ArrayList<AppDetailedComments> result;

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public ArrayList<AppDetailedComments> getResult() {
        return result;
    }
}
