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
import com.example.appdev_finalproject.model.OrderItem;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder>{
    private Context context;
    private ArrayList<OrderItem> orderItems;

    public CheckoutAdapter(Context context, ArrayList<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmorderitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.ViewHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);
        holder.orderItemImage.setImageDrawable(orderItem.getImage());
        holder.orderItemName.setText(orderItem.getName());
        holder.orderItemDate.setText(orderItem.getDate().toString());
        holder.orderItemPrice.setText(orderItem.getPrice().toString());
        holder.orderItemQuantity.setText(orderItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderItemImage;
        TextView orderItemName;
        TextView orderItemDate;
        Button orderItemCancel;
        TextView orderItemPrice;
        TextView orderItemQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemImage = itemView.findViewById(R.id.orderitem_image);
            orderItemName = itemView.findViewById(R.id.orderitem_name);
            orderItemDate = itemView.findViewById(R.id.orderitem_date);
            orderItemCancel = itemView.findViewById(R.id.orderitem_cancel);
            orderItemPrice = itemView.findViewById(R.id.orderitem_price);
            orderItemQuantity = itemView.findViewById(R.id.confirmorder_quantity);
        }
    }
}
