package com.example.appdev_finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.model.FoodItem;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<FoodItem> mItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(FoodItem item);
    }

    public ProductAdapter(Context context, ArrayList<FoodItem> items, OnItemClickListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fooditem, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final FoodItem currentItem = mItems.get(position);
        holder.foodImageView.setImageResource(currentItem.getImageResource());
        holder.nameTextView.setText(currentItem.getName());
        holder.priceTextView.setText(currentItem.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(currentItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public ImageView foodImageView;
        public TextView nameTextView;
        public TextView priceTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.fooditemimg);
            nameTextView = itemView.findViewById(R.id.food_item_name);
            priceTextView = itemView.findViewById(R.id.food_item_price);
        }
    }
}
