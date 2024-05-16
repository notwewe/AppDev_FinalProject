package com.example.appdev_finalproject;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.Date;

public class OrderItem {
    private Drawable image;
    private String name;
    private String date;
    private String price;
    private String quantity;
    private String state;

    public OrderItem(Drawable image, String name, String date, Float price, int quantity, String state) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.price = "₱"+ price;
        this.quantity = quantity + "";
        this.state = state;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = "₱" + price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity + " items";
    }
}
