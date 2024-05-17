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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private Context context;
    private ArrayList<OrderItem> orderItems;
    private String type;

    public OrderAdapter(Context context, ArrayList<OrderItem> orderItems, String type) {
        this.context = context;
        this.orderItems = orderItems;
        this.type = type;
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
            orderItemQuantity = itemView.findViewById(R.id.orderitem_quantity);
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type == "Completed"){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercompleted, parent, false);
        } else if (type == "Active"){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitem, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercanceled, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
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
}
