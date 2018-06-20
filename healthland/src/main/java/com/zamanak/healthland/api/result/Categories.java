package com.zamanak.healthland.api.result;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PIRI on 4/29/2018.
 */
public class Categories implements Serializable {

    private Category category;
    private ArrayList<Apps> apps;

    public Categories(Category category, ArrayList<Apps> apps) {
        this.category = category;
        this.apps = apps;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Apps> getApps() {
        return apps;
    }

    public void setApps(ArrayList<Apps> apps) {
        this.apps = apps;
    }
}
