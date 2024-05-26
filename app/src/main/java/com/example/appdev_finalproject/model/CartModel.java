package com.example.appdev_finalproject.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.NavType;

import com.example.appdev_finalproject.SignIn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartModel  {
    private static CartModel instance;

    private List<Map<String, Object>> mapCartItems;

    private ArrayList<CartItem> cartItems;

    private DatabaseReference db;

    private CartModel() {

        cartItems = new ArrayList<>();
        mapCartItems = new ArrayList<Map<String, Object>>();
        db = FirebaseDatabase.getInstance().getReference();
    }

    public static synchronized CartModel getInstance() {
        if (instance == null) {
            instance = new CartModel();
        }
        return instance;
    }

    public void addToCart(CartItem item) {
        if(!checkIfExists(item)){
            cartItems.add(item);
        }

        updateCartInFirebase();
    }

    public void updateCartMap(){
        mapCartItems.clear();
        for(CartItem a : cartItems){
            mapCartItems.add(a.toMap());
        }
    }

    public boolean checkIfExists(CartItem item){
        for (CartItem a : cartItems) {
            if(a.getName().equals(item.getName())){
                int newQuantity = a.getQuantity() + item.getQuantity();
                a.setQuantity(newQuantity);
                return true;
            }
        }
        return false;
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    // Add other methods as needed, such as removeItemFromCart, etc.
    public void updateCartInFirebase() {
        updateCartMap();

        // Convert the CartModel to a Map
        Map<String, Object> cartMap = new HashMap<>();
        cartMap.put("userId", SignIn.sessionManager.getUserId());
        cartMap.put("cartItems", mapCartItems);

        // Write the cart to the database
        db.child("cart").child(SignIn.sessionManager.getUserId())
                .setValue(cartMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("RealtimeDB", "Cart successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("RealtimeDB", "Error writing cart", e);
                    }
                });
    }
}
