package com.example.ui.Pages.Adapters.Lists;

public class Details {
    private String name;
    private String ratings;
    private String outlets;
    private String price;
    private String description;

    public Details(String name, String ratings, String outlets, String price, String description) {
        this.name = name;
        this.ratings = ratings;
        this.outlets = outlets;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }
}
