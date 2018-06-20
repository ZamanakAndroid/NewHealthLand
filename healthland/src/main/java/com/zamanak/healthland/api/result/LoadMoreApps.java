package com.zamanak.healthland.api.result;

import java.util.List;

/**
 * Created by PIRI on 4/30/2018.
 */
public class LoadMoreApps {

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

    public List<Apps> getResult() {
        return result;
    }

    public void setResult(List<Apps> result) {
        this.result = result;
    }

    private int code;
    private String error;
    private List<Apps> result;

    public LoadMoreApps(int code, String error, List<Apps> result) {
        this.code = code;
        this.error = error;
        this.result = result;
    }
}
