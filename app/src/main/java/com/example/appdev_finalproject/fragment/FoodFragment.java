package com.example.appdev_finalproject.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdev_finalproject.model.CartModel;
import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.model.CartItem;
import com.example.appdev_finalproject.model.State;

import java.util.Date;

public class FoodFragment extends Fragment {

    private int quantity = 1; // Initial quantity

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        ImageView foodImage = view.findViewById(R.id.foodimage);
        TextView foodName = view.findViewById(R.id.food_item_name);
        TextView foodRating = view.findViewById(R.id.food_item_rating);
        TextView foodDescription = view.findViewById(R.id.food_item_description);
        TextView foodPrice = view.findViewById(R.id.food_item_price);
        TextView quantityTextView = view.findViewById(R.id.quantitynum);
        Button addToCartButton = view.findViewById(R.id.btnaddtocart);
        Button incrementButton = view.findViewById(R.id.increment);
        Button decrementButton = view.findViewById(R.id.decrement);

        if (getArguments() != null) {
            foodName.setText(getArguments().getString("Name"));
            foodRating.setText(getArguments().getString("Rating"));
            foodDescription.setText(getArguments().getString("Description"));
            foodPrice.setText(getArguments().getString("Price"));
            foodImage.setImageResource(getArguments().getInt("ImageResource"));
            foodImage.setTag(getArguments().getInt("ImageResource"));
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a CartItem object representing the item being added to the cart
                String itemName = foodName.getText().toString();
                float itemPrice = Float.parseFloat(foodPrice.getText().toString().substring(1)); // Remove the currency symbol
                int itemQuantity = quantity; // Use the current quantity
                int itemImage = (int) foodImage.getTag();

                CartItem cartItem = new CartItem(itemName, itemPrice, itemQuantity, new Date(), itemImage, State.ACTIVE);

                // Add the CartItem object to the cartItems list in the CartManager
                CartModel.getInstance().addToCart(cartItem);
                Toast.makeText(getContext(), foodName.getText() + "(Qty. " + quantity + ") added to cart!" , Toast.LENGTH_SHORT).show();
            }
        });


        // Increment button click listener
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment quantity
                quantity++;
                // Update quantity text view
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        // Decrement button click listener
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    // Decrement quantity
                    quantity--;
                    // Update quantity text view
                    quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });

        return view;
    }
}
