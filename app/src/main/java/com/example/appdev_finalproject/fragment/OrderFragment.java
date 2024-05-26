package com.example.appdev_finalproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.adapter.OrderAdapter;
import com.example.appdev_finalproject.model.OrderItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;
    private ArrayList<OrderItem> orderItems;
    private MutableLiveData<ArrayList<OrderItem>> liveOrderItems = new MutableLiveData<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.order_recyclerView);
        orderItems = new ArrayList<>();
        fetchOrdersFromDatabase("ACTIVE");

        Button btnActive = view.findViewById(R.id.btn_active);
        Button btnCompleted = view.findViewById(R.id.btn_completed);
        Button btnCancelled = view.findViewById(R.id.btn_cancelled);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        liveOrderItems.observe(getViewLifecycleOwner(), new Observer<ArrayList<OrderItem>>() {
            @Override
            public void onChanged(ArrayList<OrderItem> orderItems) {
                mAdapter = new OrderAdapter(requireContext(), orderItems, "ACTIVE");
                recyclerView.setAdapter(mAdapter);
            }
        });

        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCompleted.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));
                btnActive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape));
                btnCancelled.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));

                fetchOrdersFromDatabase("ACTIVE");
            }
        });

        btnCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCompleted.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));
                btnActive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));
                btnCancelled.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape));

                fetchOrdersFromDatabase("CANCELLED");
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCompleted.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape));
                btnActive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));
                btnCancelled.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape2));

                fetchOrdersFromDatabase("COMPLETED");
            }
        });
    }

    public void fetchOrdersFromDatabase(String state) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ordersRef = db.child("orders");

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<OrderItem> newOrderItems = new ArrayList<>();
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    String orderId = (String) orderSnapshot.child("orderId").getValue();
                    Map<String, Object> itemMap = (Map<String, Object>) orderSnapshot.child("cartItem").getValue();

                    String itemName = (String) itemMap.get("name");
                    float price = ((Number) itemMap.get("price")).floatValue();
                    int quantity = ((Number) itemMap.get("quantity")).intValue();
                    String itemState = (String) itemMap.get("state");
                    int imageResId = ((Number) itemMap.get("image")).intValue();
                    String date = (String) itemMap.get("date");

                    if (itemState.equals(state)) {
                        OrderItem item = new OrderItem(imageResId, itemName, date, price, quantity, itemState, orderId);
                        newOrderItems.add(item);
                    }
                }
                liveOrderItems.setValue(newOrderItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Database error: " + error.getMessage());
            }
        });
    }
}