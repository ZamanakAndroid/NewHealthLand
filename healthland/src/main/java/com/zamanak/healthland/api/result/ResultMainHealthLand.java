package com.zamanak.healthland.api.result;

import java.util.ArrayList;

/**
 * Created by PIRI on 4/29/2018.
 */
public class ResultMainHealthLand {

    public ArrayList<Featured> getFeatured() {
        return featured;
    }

    public void setFeatured(ArrayList<Featured> featured) {
        this.featured = featured;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ResultMainHealthLand(ArrayList<Featured> featured, ArrayList<Categories> categories) {
        this.featured = featured;
        this.categories = categories;
    }

    private ArrayList<Featured> featured;
    private ArrayList<Categories> categories;
}
