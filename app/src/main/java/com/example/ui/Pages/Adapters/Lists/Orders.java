package com.example.ui.Pages.Adapters.Lists;

public class Orders {
    private String name;
    private int imageResource;
    private String date;
    private String items;
    private String price;

    public Orders(String name, int imageResource, String date, String items, String price) {
        this.name = name;
        this.imageResource = imageResource;
        this.date = date;
        this.items = items;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDate() {
        return date;
    }

    public String getItems() {
        return items;
    }

    public String getPrice() {
        return price;
    }
}
