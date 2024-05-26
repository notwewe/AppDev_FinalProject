package com.example.appdev_finalproject.model;

import android.graphics.drawable.Drawable;

public class OrderItem {
    private int image;
    private String name;
    private String date;
    private String price;
    private String quantity;
    private String state;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(int image, String name, String date, Float price, int quantity, String state, String orderId) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.price = "₱"+ price;
        this.quantity = quantity + " items";
        this.state = state;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public float getCalculatedPrice() {
        return Float.parseFloat(price.substring(1)) * Integer.parseInt(quantity);
    }
}
