package com.example.appdev_finalproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<OrderItem> orderItems;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.order_recyclerView);
        Button btnActive = (Button) view.findViewById(R.id.btn_active);
        Button btnCompleted = (Button) view.findViewById(R.id.btn_completed);
        Button btnCancelled = (Button) view.findViewById(R.id.btn_cancelled);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        orderItems = new ArrayList<>();
        Date date = new Date();

        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Chicken Adobo", new Date(), 100.00f, 1, "Cancelled"));
        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Ungart", new Date(), 100.00f, 1, "Active"));
        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Botyok", new Date(), 100.00f, 1, "Completed"));

        mAdapter = new OrderAdapter(this.getContext(), orderItems);

        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ArrayList<OrderItem> ActiveItems = new ArrayList<>();
                for(int i = 0; i < orderItems.size(); i++){
                    if(orderItems.get(i).getState() == "Active"){
                        ActiveItems.add(orderItems.get(i));
                    }
                }
                mAdapter = new OrderAdapter(requireContext(), ActiveItems);
                recyclerView.setAdapter(mAdapter);
            }

        });

        btnCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<OrderItem> CancelledItems = new ArrayList<>();
                for(int i = 0; i < orderItems.size(); i++){
                    if(orderItems.get(i).getState() == "Cancelled"){
                        CancelledItems.add(orderItems.get(i));
                    }
                }

                mAdapter = new OrderAdapter(requireContext(), CancelledItems);
                recyclerView.setAdapter(mAdapter);
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<OrderItem> CompletedItems = new ArrayList<>();
                for(int i = 0; i < orderItems.size(); i++){
                    if(orderItems.get(i).getState() == "Completed"){
                        CompletedItems.add(orderItems.get(i));
                    }
                }


                mAdapter = new OrderAdapter(requireContext(), CompletedItems);
                recyclerView.setAdapter(mAdapter);
            }


        });


    }
}