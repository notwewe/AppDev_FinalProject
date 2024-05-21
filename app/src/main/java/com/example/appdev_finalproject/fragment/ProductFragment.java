package com.example.appdev_finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.adapter.ProductAdapter;
import com.example.appdev_finalproject.model.FoodItem;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private ArrayList<FoodItem> items;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // You can handle any other parameters here if needed
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_page, container, false);

        TextView genreTitle = view.findViewById(R.id.genreTitle);
        TextView genreSubTitle = view.findViewById(R.id.genreDescription);

        recyclerView = view.findViewById(R.id.product_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        items = new ArrayList<>();
        mAdapter = new ProductAdapter(requireContext(), items, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FoodItem item) {
                // Navigate to the food fragment
                Bundle bundle = new Bundle();
                bundle.putString("Name", item.getName());
                bundle.putString("Price", item.getPrice());
                bundle.putString("Description", item.getDescription());
                bundle.putString("Rating", item.getRating());
                bundle.putInt("ImageResource", item.getImageResource());

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.foodFragment, bundle);
            }
        });
        recyclerView.setAdapter(mAdapter);

        if (getArguments() != null) {
            String genre = getArguments().getString("GenreTitle");
            genreTitle.setText(genre);
            genreSubTitle.setText(getArguments().getString("GenreSubtitle"));

            switch (genre) {
                case "Meal":
                    setMealItems();
                    break;
                case "Vegan":
                    setVeganItems();
                    break;
                case "Dessert":
                    setDessertItems();
                    break;
                case "Drinks":
                    setDrinkItems();
                    break;
                case "Snacks":
                    setSnackItems();
                    break;
            }
        }

        return view;
    }

    private void setSnackItems() {
        items.clear();
        items.add(new FoodItem("Meat Roll", "₱15.00", "Tasty meat roll!", "4.5", R.drawable.meatroll));
        items.add(new FoodItem("Ham Sandwich", "₱30.00", "Delicious ham sandwich!", "4.2", R.drawable.hamsand));
        items.add(new FoodItem("Egg Sandwich", "₱25.00", "Egg sandwich with fresh veggies!", "4.3", R.drawable.eggsand));
        items.add(new FoodItem("Hotcake", "₱10.00", "Creamy and Fluffy hotcake!", "4.8", R.drawable.hotcake));
        mAdapter.notifyDataSetChanged();
    }

    private void setVeganItems() {
        items.clear();
        items.add(new FoodItem("Vegan Burger", "₱40.00", "Healthy vegan burger!", "4.7", R.drawable.vegan_burger));
        items.add(new FoodItem("Salad Bowl", "₱35.00", "Fresh and healthy salad bowl!", "4.5", R.drawable.salad_bowl));
        items.add(new FoodItem("Tofu Stir Fry", "₱30.00", "Delicious tofu stir fry!", "4.6", R.drawable.tofu_stir_fry));
        items.add(new FoodItem("Vegan Wrap", "₱25.00", "Tasty vegan wrap!", "4.4", R.drawable.vegan_wrap));
        mAdapter.notifyDataSetChanged();
    }

    private void setDessertItems() {
        items.clear();
        items.add(new FoodItem("Chocolate Cake", "₱20.00", "Rich chocolate cake!", "4.9", R.drawable.chocolate_cake));
        items.add(new FoodItem("Ice Cream", "₱15.00", "Creamy vanilla ice cream!", "4.8", R.drawable.ice_cream));
        items.add(new FoodItem("Fruit Tart", "₱25.00", "Fresh fruit tart!", "4.7", R.drawable.fruit_tart));
        items.add(new FoodItem("Cupcake", "₱10.00", "Delicious cupcake!", "4.6", R.drawable.cupcake));
        mAdapter.notifyDataSetChanged();
    }

    private void setDrinkItems() {
        items.clear();
        items.add(new FoodItem("Mango Shake", "₱30.00", "Refreshing mango shake!", "4.7", R.drawable.mangoshake));
        items.add(new FoodItem("Lemonade", "₱20.00", "Cool and refreshing lemonade!", "4.5", R.drawable.lemonade));
        items.add(new FoodItem("Coffee", "₱15.00", "Hot brewed coffee!", "4.6", R.drawable.coffee));
        items.add(new FoodItem("Milo", "₱30.00", "Delicious Milo!", "4.8", R.drawable.milo));
        mAdapter.notifyDataSetChanged();
    }

    private void setMealItems() {
        items.clear();
        items.add(new FoodItem("Spaghetti", "₱25.00", "Classic spaghetti with meat sauce and parmesan cheese.", "4.5", R.drawable.spag));
        items.add(new FoodItem("Chicken Adobo", "₱30.00", "Tender chicken cooked in soy sauce, vinegar, and garlic.", "4.4", R.drawable.chickenado));
        items.add(new FoodItem("Burger Steak", "₱40.00", "Juicy burger patty served with flavorful gravy and rice.", "4.6", R.drawable.burgersteak));
        items.add(new FoodItem("Pork Silog", "₱35.00", "Savory marinated pork served with garlic fried rice and egg.", "4.3", R.drawable.porksilog));
        mAdapter.notifyDataSetChanged();
    }
}
