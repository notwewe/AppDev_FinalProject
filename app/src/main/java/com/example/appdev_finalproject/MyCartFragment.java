package com.example.appdev_finalproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyCartFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<OrderItem> orderItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycart, container, false);
        recyclerView = view.findViewById(R.id.cart_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderItems = new ArrayList<>();

        Drawable itemImage = getResources().getDrawable(R.drawable.chicken_adobo);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, hh:mm a");
        String formattedDate = sdf.format(new Date());


        orderItems.add(new OrderItem(itemImage, "Sample Item", formattedDate, 50.0f, 2, "Cancelled"));

        mAdapter = new MyCartAdapter(getContext(), orderItems);
        recyclerView.setAdapter(mAdapter);

        ImageView cartIcon = view.findViewById(R.id.cart_icon);
        TextView emptyCartText = view.findViewById(R.id.empty_cart_text);
        TextView addItemText = view.findViewById(R.id.add_smth_text);
        if (orderItems.size() > 0) {

            cartIcon.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.GONE);
            addItemText.setVisibility(View.GONE);
        } else {

            cartIcon.setVisibility(View.VISIBLE);
            emptyCartText.setVisibility(View.VISIBLE);
            addItemText.setVisibility(View.VISIBLE);
        }

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
