package com.example.appdev_finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.model.CartItem;

import java.text.DecimalFormat;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;

    public MyCartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.itemNameTextView.setText(item.getName());
        DecimalFormat df = new DecimalFormat("0.00");
        holder.itemPriceTextView.setText("₱" + df.format(item.getPrice()));
        holder.itemQuantityTextView.setText(item.getQuantity() + " items");
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged(); // Notify the adapter that the dataset has changed
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNameTextView;
        public TextView itemPriceTextView;
        public TextView itemQuantityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.cart_item_name);
            itemPriceTextView = itemView.findViewById(R.id.cart_item_price);
            itemQuantityTextView = itemView.findViewById(R.id.cart_item_quantity);

        }
    }
}
