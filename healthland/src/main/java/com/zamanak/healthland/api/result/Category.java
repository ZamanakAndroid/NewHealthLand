package com.zamanak.healthland.api.result;

/**
 * Created by PIRI on 4/29/2018.
 */
public class Category {

    public String getName_En() {
        return name_En;
    }

    public void setName_En(String name_En) {
        this.name_En = name_En;
    }

    public String getName_Fa() {
        return name_Fa;
    }

    public void setName_Fa(String name_Fa) {
        this.name_Fa = name_Fa;
    }

    public Category(String name_En, String name_Fa) {
        this.name_En = name_En;
        this.name_Fa = name_Fa;
    }

    private String name_En;
    private String name_Fa;

}
