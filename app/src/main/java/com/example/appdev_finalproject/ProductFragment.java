package com.example.appdev_finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<FoodItem> items;


    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product_page, container, false);

        String GenreTitle = getArguments().getString("GenreTitle");
        String GenreSubTitle = getArguments().getString("GenreSubtitle");

        TextView genreTitle = view.findViewById(R.id.genreTitle);
        TextView genreSubTitle = view.findViewById(R.id.genreDescription);

        genreTitle.setText(GenreTitle);
        genreSubTitle.setText(GenreSubTitle);

        recyclerView = (RecyclerView) view.findViewById(R.id.product_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        items = new ArrayList<>();

        items.add(new FoodItem("Chicken Adobo", "$27.00"));
        items.add(new FoodItem("Dildo Adobo", "$100.00"));
        items.add(new FoodItem("Buang Adobo", "$32.00"));
        items.add(new FoodItem("Ungart Adobo", "$13.00"));



        mAdapter = new ProductAdapter(requireContext(), items);
        recyclerView.setAdapter(mAdapter);

        return view;
    }


}