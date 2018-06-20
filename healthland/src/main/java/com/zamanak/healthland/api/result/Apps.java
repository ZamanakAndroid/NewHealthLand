package com.zamanak.healthland.api.result;

import java.io.Serializable;

/**
 * Created by PIRI on 4/29/2018.
 */
public class Apps implements Serializable {

    private String id;
    private String icon;
    private String title;
    private double stars;
    private long votes;
    private long views;
    private String link;

    public Apps(String id, String icon, String title, double stars, long votes,
                long views, String link) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.stars = stars;
        this.votes = votes;
        this.views = views;
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
