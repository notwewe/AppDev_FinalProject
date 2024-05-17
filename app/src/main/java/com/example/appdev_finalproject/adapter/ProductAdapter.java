package com.example.appdev_finalproject.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.model.FoodItem;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context context;
    private List<FoodItem> productItems;

    public ProductAdapter(Context context, List<FoodItem> productItems){
        this.context = context;
        this.productItems = productItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = productItems.get(position);
        holder.food_item_name.setText(item.getName());
        holder.food_item_price.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView food_item_name;
        public TextView food_item_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_item_name = itemView.findViewById(R.id.food_item_name);
            food_item_price = itemView.findViewById(R.id.food_item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the product fragment
                    // Create a Bundle and put your parameters into it
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", (String) food_item_name.getText());
                    bundle.putString("Rating", "5.0");
                    bundle.putString("Description", "Delicious!");
                    bundle.putString("Price", (String) food_item_price.getText());

                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.foodFragment, bundle);
                }
            });


        }


    }
}
