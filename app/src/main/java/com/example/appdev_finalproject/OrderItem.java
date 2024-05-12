package com.example.appdev_finalproject;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.Date;

public class OrderItem {
    private Drawable image;
    private String name;
    private Date date;
    private String price;
    private String quantity;

    public OrderItem(Drawable image, String name, Date date, Float price, int quantity) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.price = "₱"+ price;
        this.quantity = quantity + " items";
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
