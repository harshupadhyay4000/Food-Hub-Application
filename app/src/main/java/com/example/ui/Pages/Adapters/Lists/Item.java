package com.example.ui.Pages.Adapters.Lists;

public class Item {
    private String name;
    private int imageResource;
    private String ratings;
    private String outlets;
    private String price;
    private String description;

    public String getDescription() {
        return description;
    }

    public Item(String name, int imageResource, String ratings, String outlets, String price, String description) {
        this.name = name;
        this.imageResource = imageResource;
        this.ratings = ratings;
        this.outlets = outlets;
        this.price = price;
        this.description = description;
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

    public String getPrice() {
        return price;
    }
}
