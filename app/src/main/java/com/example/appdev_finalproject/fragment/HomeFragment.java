package com.example.appdev_finalproject.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.appdev_finalproject.R;

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
        CardView bestSeller_2 = view.findViewById(R.id.bestSeller_2);
        CardView bestSeller_3 = view.findViewById(R.id.bestSeller_3);
        CardView bestSeller_4 = view.findViewById(R.id.bestSeller_4);
        CardView recommended1 = view.findViewById(R.id.recommended1);
        CardView recommended2 = view.findViewById(R.id.recommended2);
        CardView facultyChoice = view.findViewById(R.id.facultyChoice);

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
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Meat Roll");
                bundle.putString("Rating", "4.5");
                bundle.putString("Description", "Tasty meat roll with a delicious filling.");
                bundle.putString("Price", "₱15.00");
                bundle.putInt("ImageResource", R.drawable.meatroll);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        bestSeller_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Hotcake");
                bundle.putString("Rating", "4.7");
                bundle.putString("Description", "Fluffy hotcakes served with syrup.");
                bundle.putString("Price", "₱10.00");
                bundle.putInt("ImageResource", R.drawable.hotcake);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        bestSeller_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Ham Sandwich");
                bundle.putString("Rating", "4.3");
                bundle.putString("Description", "A classic ham sandwich with fresh lettuce and tomatoes.");
                bundle.putString("Price", "₱30.00");
                bundle.putInt("ImageResource", R.drawable.hamsand);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        bestSeller_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Egg Sandwich");
                bundle.putString("Rating", "4.2");
                bundle.putString("Description", "A tasty egg sandwich with mayo and seasoning.");
                bundle.putString("Price", "₱25.00");
                bundle.putInt("ImageResource", R.drawable.eggsand);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        recommended1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Meat Roll");
                bundle.putString("Rating", "4.5");
                bundle.putString("Description", "Tasty meat roll with a delicious filling.");
                bundle.putString("Price", "₱15.00");
                bundle.putInt("ImageResource", R.drawable.meatroll);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        recommended2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Mango Shake");
                bundle.putString("Rating", "4.8");
                bundle.putString("Description", "Refreshing mango shake made with fresh mangoes.");
                bundle.putString("Price", "₱30.00");
                bundle.putInt("ImageResource", R.drawable.mangoshake);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        facultyChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the food fragment
                // Create a Bundle and put your parameters into it
                Bundle bundle = new Bundle();
                bundle.putString("Name", "Hotcake");
                bundle.putString("Rating", "4.7");
                bundle.putString("Description", "Fluffy hotcakes served with syrup.");
                bundle.putString("Price", "₱10.00");
                bundle.putInt("ImageResource", R.drawable.hotcake);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });

        return view;
    }
    private void navigateToProductFragment(View view, String genreTitle, String genreSubtitle) {
        Bundle bundle = new Bundle();
        bundle.putString("GenreTitle", genreTitle);
        bundle.putString("GenreSubtitle", genreSubtitle);

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.productFragment, bundle);
    }
}
