package com.example.appdev_finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.fragment.MyCartFragment;
import com.example.appdev_finalproject.model.CartItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;

    private MyCartFragment myCartFragment;

    public MyCartAdapter(Context context, List<CartItem> cartItems, MyCartFragment myCartFragment) {
        this.context = context;
        this.cartItems = cartItems;
        this.myCartFragment = myCartFragment;
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
        holder.orderItemImage.setImageResource(item.getImage());
        holder.itemNameTextView.setText(item.getName());
        DecimalFormat df = new DecimalFormat("0.00");
        holder.itemPriceTextView.setText("₱" + df.format(item.getComputedPrice()));
        holder.itemQuantityTextView.setText(item.getQuantity() + "");

        holder.orderItemDate.setText(item.getDate());

        holder.orderItemAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setQuantity(item.getQuantity() + 1);
                holder.itemQuantityTextView.setText(item.getQuantity() + "");
                notifyDataSetChanged();
                myCartFragment.updateSubtotal();
            }
        });

        holder.orderItemSubtractQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    holder.itemQuantityTextView.setText(item.getQuantity() + "");
                    notifyDataSetChanged();
                    myCartFragment.updateSubtotal();
                }
            }
        });
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
        public ImageView orderItemImage;
        public TextView itemNameTextView;
        public TextView itemPriceTextView;
        public TextView itemQuantityTextView;

        public TextView orderItemDate;

        public Button orderItemAddQuantity;
        public Button orderItemSubtractQuantity;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemImage = itemView.findViewById(R.id.orderitem_image);
            itemNameTextView = itemView.findViewById(R.id.cart_item_name);
            itemPriceTextView = itemView.findViewById(R.id.cart_item_price);
            itemQuantityTextView = itemView.findViewById(R.id.cart_item_quantity);
            orderItemAddQuantity = itemView.findViewById(R.id.cart_item_add);
            orderItemSubtractQuantity = itemView.findViewById(R.id.cart_item_subtract);
            orderItemDate = itemView.findViewById(R.id.orderitem_date);
        }
    }
}
