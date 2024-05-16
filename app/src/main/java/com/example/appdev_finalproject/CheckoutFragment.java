package com.example.appdev_finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

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

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        //get the spinner from the xml.
        Spinner dropdown = view.findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        recyclerView = (RecyclerView) view.findViewById(R.id.confirmOrderRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        orderItems = new ArrayList<>();
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, hh:mm a");
        String formattedDate = sdf.format(date);

        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Chicken Adobo", formattedDate, 100.00f, 1, "Active"));
        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Chicken Adobo", formattedDate, 100.00f, 1, "Active"));
        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Ungart", formattedDate, 100.00f, 1, "Active"));
        orderItems.add(new OrderItem(getResources().getDrawable(R.drawable.chicken_adobo),"Botyok", formattedDate, 100.00f, 1, "Active"));

        ArrayList<OrderItem> ActiveItems = new ArrayList<>();
        for(int i = 0; i < orderItems.size(); i++){
            if(orderItems.get(i).getState() == "Active"){
                ActiveItems.add(orderItems.get(i));
            }
        }

        mAdapter = new CheckoutAdapter(requireContext(), ActiveItems);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}