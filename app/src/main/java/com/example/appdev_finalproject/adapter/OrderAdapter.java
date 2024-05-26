package com.example.appdev_finalproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.model.CartItem;
import com.example.appdev_finalproject.model.OrderItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private Context context;
    private ArrayList<OrderItem> orderItems;
    private String type;

    public OrderAdapter(Context context, ArrayList<OrderItem> orderItems, String type) {
        this.context = context;
        this.orderItems = orderItems;
        this.type = type;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderItemImage;
        TextView orderItemName;
        TextView orderItemDate;
        Button orderItemCancel;
        Button orderitemReview;



        Button orderItemConfirm;
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
            orderItemConfirm = itemView.findViewById(R.id.orderitem_confirm);
            orderitemReview = itemView.findViewById(R.id.orderitem_review);
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type == "COMPLETED"){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercompleted, parent, false);
        } else if (type == "ACTIVE"){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitem, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercanceled, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderItem orderItem = orderItems.get(position);
        holder.orderItemImage.setImageResource(orderItem.getImage());
        holder.orderItemName.setText(orderItem.getName());
        holder.orderItemDate.setText(orderItem.getDate());
        holder.orderItemPrice.setText(orderItem.getPrice());
        holder.orderItemQuantity.setText(orderItem.getQuantity());

        if(type == "ACTIVE"){
            holder.orderItemCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the orderId of the current item
                    String orderId = orderItem.getOrderId();

                    Log.d("OrderAdapter", "Order ID: " + orderId);

                    // Create a reference to the specific order in the Firebase database
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference orderRef = db.child("orders").child(orderId).child("cartItem");

                    // Update the state of the order to "CANCELLED"
                    orderRef.child("state").setValue("CANCELLED")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Remove the item from the list and notify the adapter
                                    orderItems.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Log.e("Firebase", "Error updating order state: " + e.getMessage());
                                }
                            });
                }
            });

            holder.orderItemConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the orderId of the current item
                    String orderId = orderItem.getOrderId();

                    Log.d("OrderAdapter", "Order ID: " + orderId);

                    // Create a reference to the specific order in the Firebase database
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference orderRef = db.child("orders").child(orderId).child("cartItem");

                    // Update the state of the order to "COMPLETED"
                    orderRef.child("state").setValue("COMPLETED")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Remove the item from the list and notify the adapter
                                    orderItems.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Log.e("Firebase", "Error updating order state: " + e.getMessage());
                                }
                            });
                }
            });
        } else if (type == "COMPLETED") {
            holder.orderitemReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("OrderName", orderItem.getName());
                    bundle.putInt("OrderImage", orderItem.getImage());
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.reviewFragment, bundle);
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return orderItems.size();
    }
}
