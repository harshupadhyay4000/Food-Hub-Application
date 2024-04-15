package com.example.ui.Pages.Cart;

public class Products {
    private String name;
    private int imageResource;
    private String price;
    private String description;

    public Products(String name, int imageResource, String price, String description) {
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
