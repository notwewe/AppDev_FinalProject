package com.example.appdev_finalproject.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.CartManager;
import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.adapter.MyCartAdapter;
import com.example.appdev_finalproject.model.CartItem;

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
    private LinearLayout subtotal_layout;
    private Spinner spinner_mycart;
    private TextView methodofclaiming;

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
        subtotal_layout = view.findViewById(R.id.subtotal_layout);
        spinner_mycart = view.findViewById(R.id.spinner_mycart);
        methodofclaiming = view.findViewById(R.id.methodofclaiming);
        //get the spinner from the xml.
        Spinner dropdown = view.findViewById(R.id.spinner_mycart);
        //create a list of items for the spinner.
        String[] items = new String[]{"Delivery", "Pickup"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        cartItems = new ArrayList<>();
        // Add sample cart items
//        cartItems.add(new CartItem("Item 1", 10.00f, 1));
//        cartItems.add(new CartItem("Item 2", 15.00f, 1));
//        cartItems.add(new CartItem("Item 3", 15.00f, 1));
//        cartItems.add(new CartItem("Item 4", 15.00f, 1));
//        cartItems.add(new CartItem("Item 5", 15.00f, 1));
//        cartItems.add(new CartItem("Item 6", 15.00f, 1));


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
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Drinks");
                bundle.putString("GenreSubtitle", "Refreshing!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.checkoutFragment, bundle);
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
            // When the cart is empty
            Log.d("MyCartFragment", "Cart is empty. Showing add items message.");
            cartIcon.setVisibility(View.VISIBLE);
            addSomethingText.setVisibility(View.VISIBLE);
            emptyCartText.setVisibility(View.VISIBLE);
            methodofclaiming.setVisibility(View.GONE);
            spinner_mycart.setVisibility(View.GONE);
            subtotal_layout.setVisibility(View.GONE);
        } else {
            // When there are items in the cart
            Log.d("MyCartFragment", "Cart is not empty. Showing method of claiming options.");
            cartIcon.setVisibility(View.GONE);
            addSomethingText.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.GONE);
            methodofclaiming.setVisibility(View.VISIBLE);
            spinner_mycart.setVisibility(View.VISIBLE);
            subtotal_layout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the cart items from the CartManager
        cartItems.clear();
        cartItems.addAll(CartManager.getInstance().getCartItems());

        // Update the adapter with the cart items
        mAdapter.setCartItems(cartItems);

        // Update subtotal and visibility
        updateSubtotal();
        updateVisibility();
    }


}
