package com.zamanak.healthland.api.result;

import java.util.ArrayList;

/**
 * Created by zamanak on 4/29/2018.
 */

public class AppRes {

    private String id;
    private String icon;
    private ArrayList<String> gallery;
    private boolean isFeatured;
    private String featuredIcon;
    private float stars;
    private int votes;
    private int views;
    private int comments;
    private String title;
    private String description;
    private String link;
    private String category;
    private int dateCreated;
    private String shareMessage;

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public ArrayList<String> getGallery() {
        return gallery;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public String getFeaturedIcon() {
        return featuredIcon;
    }

    public float getStars() {
        return stars;
    }

    public int getVotes() {
        return votes;
    }

    public int getViews() {
        return views;
    }

    public int getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public String getShareMessage() {
        return shareMessage;
    }
}
