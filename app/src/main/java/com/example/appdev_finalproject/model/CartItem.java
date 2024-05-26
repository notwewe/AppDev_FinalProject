package com.example.appdev_finalproject.model;

import android.graphics.drawable.Drawable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CartItem {
    private String name;
    private float price;
    private int quantity;
    private String date;
    private int image;
    private State state;

    public CartItem() {
    }

    public CartItem(String name, float price, int quantity, Date date, int image, State state) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

        // Convert the Date object to a String
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US);
        this.date = format.format(date);

        this.image = image;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity == 0) return;
        this.quantity = quantity;
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateString) {
        this.date = dateString;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getComputedPrice(){
        return price * quantity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("price", price);
        result.put("quantity", quantity);

        result.put("date", date);

        result.put("image", image);
        result.put("state", state.toString());
        return result;
    }
}
