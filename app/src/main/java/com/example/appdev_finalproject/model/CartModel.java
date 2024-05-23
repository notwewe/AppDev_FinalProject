package com.example.appdev_finalproject.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private static CartModel instance;
    private List<CartItem> cartItems;

    private CartModel() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartModel getInstance() {
        if (instance == null) {
            instance = new CartModel();
        }
        return instance;
    }

    public void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    // Add other methods as needed, such as removeItemFromCart, etc.
}
