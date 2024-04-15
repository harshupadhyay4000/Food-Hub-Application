package com.example.ui.Pages.Adapters.Lists;

public class Resturant {
    private String name;
    private int imageResource;
    private String ratings;
    private String outlets;

    public Resturant(String name, int imageResource, String ratings, String outlets) {
        this.name = name;
        this.imageResource = imageResource;
        this.ratings = ratings;
        this.outlets = outlets;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getRatings() {
        return ratings;
    }

    public String getOutlets() {
        return outlets;
    }
}
