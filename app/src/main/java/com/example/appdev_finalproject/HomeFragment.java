package com.example.appdev_finalproject;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize Clickables
        LinearLayout btnDessert = view.findViewById(R.id.btnDessert);
        LinearLayout btnSnacks = view.findViewById(R.id.btnSnacks);
        LinearLayout btnMeal = view.findViewById(R.id.btnMeal);
        LinearLayout btnVegan = view.findViewById(R.id.btnVegan);
        LinearLayout btnDrinks = view.findViewById(R.id.btnDrinks);
        CardView bestSeller_1 = view.findViewById(R.id.bestSeller_1);


        // Initialize OnClickListeners
        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Dessert");
                bundle.putString("GenreSubtitle", "Sweet Treats!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productFragment, bundle);
            }
        });

        btnVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Vegan");
                bundle.putString("GenreSubtitle", "Healthy!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productFragment, bundle);
            }
        });

        btnSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Snacks");
                bundle.putString("GenreSubtitle", "Crunchy!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productFragment, bundle);
            }
        });

        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Meal");
                bundle.putString("GenreSubtitle", "Filling!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productFragment, bundle);
            }
        });

        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("GenreTitle", "Drinks");
                bundle.putString("GenreSubtitle", "Refreshing!");


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productFragment, bundle);
            }
        });


        bestSeller_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the product fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Chicken Joy");
                bundle.putString("Rating", "5.0");
                bundle.putString("Description", "Delicious!");
                bundle.putString("Price", "$5.00");

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        return view;
    }
}
