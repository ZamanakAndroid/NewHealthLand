package com.zamanak.healthland.api.result;

/**
 * Created by zamanak on 5/2/2018.
 */

public class ResultInsertComment {

    private int code;
    private String error;
    private SuccessResult result;

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public SuccessResult getResult() {
        return result;
    }
}
