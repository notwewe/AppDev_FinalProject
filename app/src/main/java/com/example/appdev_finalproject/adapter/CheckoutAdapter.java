package com.example.appdev_finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.fragment.CheckoutFragment;
import com.example.appdev_finalproject.model.CartItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder>{
    private Context context;
    private ArrayList<CartItem> orderItems;
    private CheckoutFragment checkoutFragment;

    public CheckoutAdapter(Context context, ArrayList<CartItem> orderItems, CheckoutFragment checkoutFragment) {
        this.context = context;
        this.orderItems = orderItems;
        this.checkoutFragment = checkoutFragment;
    }

    @NonNull
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmorderitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.ViewHolder holder, int position) {
        CartItem orderItem = orderItems.get(position);
        holder.orderItemImage.setImageResource(orderItem.getImage());
        holder.orderItemName.setText(orderItem.getName());
        holder.orderItemPrice.setText(String.format("%.2f", orderItem.getComputedPrice()));
        holder.orderItemQuantity.setText(String.format("%d", orderItem.getQuantity()));

        holder.orderItemDate.setText(orderItem.getDate());

        holder.orderItemAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderItem.setQuantity(orderItem.getQuantity() + 1);
                holder.orderItemQuantity.setText(String.format("%d", orderItem.getQuantity()));
                notifyDataSetChanged();
                checkoutFragment.updateTotalPrice();
            }
        });

        holder.orderItemSubtractQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderItem.getQuantity() > 1){
                    orderItem.setQuantity(orderItem.getQuantity() - 1);
                    holder.orderItemQuantity.setText(String.format("%d", orderItem.getQuantity()));
                    notifyDataSetChanged();
                    checkoutFragment.updateTotalPrice();
                }
            }
        });

        holder.orderItemCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderItems.remove(orderItem);

                if(orderItems.isEmpty()){
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.mycartFragment);
                }

                notifyDataSetChanged();
                checkoutFragment.updateTotalPrice();

            }
        });
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
        Button orderItemAddQuantity;
        Button orderItemSubtractQuantity;
        TextView orderItemPrice;
        TextView orderItemQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemImage = itemView.findViewById(R.id.orderitem_image);
            orderItemName = itemView.findViewById(R.id.orderitem_name);
            orderItemDate = itemView.findViewById(R.id.orderitem_date);
            orderItemCancel = itemView.findViewById(R.id.confirmorder_cancel);
            orderItemPrice = itemView.findViewById(R.id.orderitem_price);
            orderItemQuantity = itemView.findViewById(R.id.confirmorder_quantity);
            orderItemAddQuantity = itemView.findViewById(R.id.confirmorder_addquantity);
            orderItemSubtractQuantity = itemView.findViewById(R.id.confirmorder_minusquantity);


        }


    }
}
