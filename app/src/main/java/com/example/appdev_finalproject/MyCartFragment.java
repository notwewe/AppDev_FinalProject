package com.example.appdev_finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyCartAdapter mAdapter;
    private TextView subtotalAmountText;
    private View cartRecyclerView;
    private View cartIcon;
    private TextView addSomethingText;
    private TextView emptyCartText;

    private List<CartItem> cartItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycart, container, false);

        recyclerView = view.findViewById(R.id.cart_recyclerView);
        subtotalAmountText = view.findViewById(R.id.subtotal_amount_text);
        cartRecyclerView = view.findViewById(R.id.linearLayout2);
        cartIcon = view.findViewById(R.id.cart_icon);
        addSomethingText = view.findViewById(R.id.add_smth_text);
        emptyCartText = view.findViewById(R.id.empty_cart_text);

        cartItems = new ArrayList<>();
        // Add sample cart items
        cartItems.add(new CartItem("Item 1", 10.00f, 1));
        cartItems.add(new CartItem("Item 2", 15.00f, 1));
        cartItems.add(new CartItem("Item 3", 15.00f, 1));
        cartItems.add(new CartItem("Item 4", 15.00f, 1));
        cartItems.add(new CartItem("Item 5", 15.00f, 1));
        cartItems.add(new CartItem("Item 6", 15.00f, 1));


        mAdapter = new MyCartAdapter(getContext(), cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        updateSubtotal();
        updateVisibility();

        Button checkoutButton = view.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle checkout button click
            }
        });

        return view;
    }

    private void updateSubtotal() {
        float subtotal = 0.0f;
        for (CartItem item : cartItems) {
            subtotal += item.getPrice() * (float)item.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        subtotalAmountText.setText("₱" + df.format(subtotal));
    }

    private void updateVisibility() {
        if (cartItems.isEmpty()) {
            cartRecyclerView.setVisibility(View.GONE);
            cartIcon.setVisibility(View.GONE);
            addSomethingText.setVisibility(View.VISIBLE);
            emptyCartText.setVisibility(View.VISIBLE);
        } else {
            cartRecyclerView.setVisibility(View.VISIBLE);
            cartIcon.setVisibility(View.GONE);
            addSomethingText.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.GONE);
        }
    }
}
