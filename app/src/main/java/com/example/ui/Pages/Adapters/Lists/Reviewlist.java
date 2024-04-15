package com.example.ui.Pages.Adapters.Lists;

public class Reviewlist {
    private String name;
    private int imageResource;
    private String reviewdone;
    private String date;

    public Reviewlist(String name, int imageResource, String reviewdone, String date) {
        this.name = name;
        this.imageResource = imageResource;
        this.reviewdone = reviewdone;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getReviewdone() {
        return reviewdone;
    }

    public String getDate() {
        return date;
    }
}
