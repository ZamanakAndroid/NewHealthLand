package com.zamanak.healthland.api.result;

/**
 * Created by PIRI on 4/29/2018.
 */
public class Featured {

    private String id;
    private String featuredIcon;
    private String title;
    private double stars;
    private long votes;
    private long views;
    // private String link;

    public Featured(String id, String featuredIcon, String title, double stars, long votes,
                    long views/*, String link*/) {
        this.id = id;
        this.featuredIcon = featuredIcon;
        this.title = title;
        this.stars = stars;
        this.votes = votes;
        this.views = views;
        // this.link = link;
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

    public String getFeaturedIcon() {
        return featuredIcon;
    }

    public void setFeaturedIcon(String featuredIcon) {
        this.featuredIcon = featuredIcon;
    }

   /* public String getLink() {
        return link;
    }*/

/*
    public void setLink(String link) {
        this.link = link;
    }
*/
}
