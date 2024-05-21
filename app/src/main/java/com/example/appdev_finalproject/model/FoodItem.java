package com.example.appdev_finalproject.model;

public class FoodItem {

    private String name;
    private String price;
    private String description;
    private String rating;
    private int imageResource;

    public FoodItem(String name, String price, String description, String rating, int imageResource) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public int getImageResource() {
        return imageResource;
    }
}
