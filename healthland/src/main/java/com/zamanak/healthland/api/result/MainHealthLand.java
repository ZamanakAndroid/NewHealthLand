package com.zamanak.healthland.api.result;

/**
 * Created by PIRI on 4/29/2018.
 */
public class MainHealthLand {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ResultMainHealthLand getResult() {
        return result;
    }

    public void setResult(ResultMainHealthLand result) {
        this.result = result;
    }

    private int code;
    private String error;
    private ResultMainHealthLand result;

    public MainHealthLand(int code, String error, ResultMainHealthLand result) {
        this.code = code;
        this.error = error;
        this.result = result;
    }
}
