package com.example.appdev_finalproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appdev_finalproject.R;
import com.example.appdev_finalproject.SignIn;
import com.example.appdev_finalproject.adapter.CheckoutAdapter;
import com.example.appdev_finalproject.model.CartItem;
import com.example.appdev_finalproject.model.CartModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<CartItem> orderItems;


    TextView checkoutTitle;
    TextView checkoutTotal;
    TextView checkoutSubtotal;
    TextView checkoutDelivery;

    Button btnPlaceOrder;

    String deliveryType;




    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        deliveryType = getArguments().getString("deliveryType");

        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);


        checkoutTitle = view.findViewById(R.id.txtTypeOfDelivery);
        checkoutTotal = view.findViewById(R.id.txtTotal);
        checkoutSubtotal = view.findViewById(R.id.txtSubtotal);
        checkoutDelivery = view.findViewById(R.id.txtDelivery);

        //get the spinner from the xml.
        Spinner dropdown = view.findViewById(R.id.SpinnerOptions);
        //create a list of items for the spinner.
        String[] items = new String[]{};


        if(deliveryType.equals("Delivery")){
            checkoutTitle.setText("Building/Classroom No. ");

            items = new String[]{
                    "GLE | 9:00 AM",
                    "GLE | 9:30 AM",
                    "GLE | 10:00 AM",
                    "GLE | 10:30 AM",
                    "GLE | 11:00 AM",
                    "GLE | 11:30 AM",
                    "GLE | 12:00 PM",
                    "GLE | 12:30 PM",
                    "GLE | 1:00 PM",
                    "GLE | 1:30 PM",
                    "GLE | 2:00 PM",
                    "GLE | 2:30 PM",
                    "GLE | 3:00 PM",
                    "GLE | 3:30 PM",
                    "GLE | 4:00 PM",
                    "GLE | 4:30 PM",
                    "GLE | 5:00 PM",
                    "GLE | 5:30 PM",
                    "GLE | 6:00 PM"
            };
        } else if(deliveryType.equals("Pickup")){
            checkoutTitle.setText("Location | Pickup Time");

            items = new String[]{
                    "Canteen 1 | 9:00 AM",
                    "Canteen 1 | 9:30 AM",
                    "Canteen 1 | 10:00 AM",
                    "Canteen 1 | 10:30 AM",
                    "Canteen 1 | 11:00 AM",
                    "Canteen 1 | 11:30 AM",
                    "Canteen 1 | 12:00 PM",
                    "Canteen 1 | 12:30 PM",
                    "Canteen 1 | 1:00 PM",
                    "Canteen 1 | 1:30 PM",
                    "Canteen 1 | 2:00 PM",
                    "Canteen 1 | 2:30 PM",
                    "Canteen 1 | 3:00 PM",
                    "Canteen 1 | 3:30 PM",
                    "Canteen 1 | 4:00 PM",
                    "Canteen 1 | 4:30 PM",
                    "Canteen 1 | 5:00 PM",
                    "Canteen 1 | 5:30 PM",
                    "Canteen 1 | 6:00 PM",
                    "Canteen 2 | 9:00 AM",
                    "Canteen 2 | 9:30 AM",
                    "Canteen 2 | 10:00 AM",
                    "Canteen 2 | 10:30 AM",
                    "Canteen 2 | 11:00 AM",
                    "Canteen 2 | 11:30 AM",
                    "Canteen 2 | 12:00 PM",
                    "Canteen 2 | 12:30 PM",
                    "Canteen 2 | 1:00 PM",
                    "Canteen 2 | 1:30 PM",
                    "Canteen 2 | 2:00 PM",
                    "Canteen 2 | 2:30 PM",
                    "Canteen 2 | 3:00 PM",
                    "Canteen 2 | 3:30 PM",
                    "Canteen 2 | 4:00 PM",
                    "Canteen 2 | 4:30 PM",
                    "Canteen 2 | 5:00 PM",
                    "Canteen 2 | 5:30 PM",
                    "Canteen 2 | 6:00 PM",
                    "Canteen 3 | 9:00 AM",
                    "Canteen 3 | 9:30 AM",
                    "Canteen 3 | 10:00 AM",
                    "Canteen 3 | 10:30 AM",
                    "Canteen 3 | 11:00 AM",
                    "Canteen 3 | 11:30 AM",
                    "Canteen 3 | 12:00 PM",
                    "Canteen 3 | 12:30 PM",
                    "Canteen 3 | 1:00 PM",
                    "Canteen 3 | 1:30 PM",
                    "Canteen 3 | 2:00 PM",
                    "Canteen 3 | 2:30 PM",
                    "Canteen 3 | 3:00 PM",
                    "Canteen 3 | 3:30 PM",
                    "Canteen 3 | 4:00 PM",
                    "Canteen 3 | 4:30 PM",
                    "Canteen 3 | 5:00 PM",
                    "Canteen 3 | 5:30 PM",
                    "Canteen 3 | 6:00 PM"
            };
        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        recyclerView = (RecyclerView) view.findViewById(R.id.confirmOrderRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        Date date = new Date();

        orderItems = CartModel.getInstance().getCartItems();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, hh:mm a");
        String formattedDate = sdf.format(date);

        mAdapter = new CheckoutAdapter(requireContext(), orderItems, this);
        recyclerView.setAdapter(mAdapter);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Place order and set it to Active.

                for (CartItem item: orderItems) {
                    sendOrder(item);

                }

                CartModel.getInstance().clearCart();
                orderItems.clear();
                mAdapter.notifyDataSetChanged();
                updateTotalPrice();

                // Navigate to the order confirmation page
                if(deliveryType.equals("Pickup")){
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_checkoutFragment_to_pickupConfirmedFragment);
                } else {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_checkoutFragment_to_deliveryConfirmedFragment);
                }

                //TODO: Add to active orders

            }
        });

        updateTotalPrice();




        return view;

    }

    public void updateTotalPrice() {
        float total = 0;
        float subtotal = 0;
        float delivery = (deliveryType.equals("Delivery")) ? 15 : 0;

        for(CartItem item: orderItems){
            subtotal += item.getPrice() * item.getQuantity();
        }

        total = subtotal + delivery;

        checkoutTotal.setText("₱" + total);
        checkoutSubtotal.setText("₱" + subtotal);
        checkoutDelivery.setText("₱" + delivery);
    }

    public void sendOrder(CartItem item) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        // Get the userId from the SessionManager
        String userId = SignIn.sessionManager.getUserId();

        // Create a new orderId
        String orderId = db.child("orders").push().getKey();

        // Convert the CartItem to a Map
        Map<String, Object> itemMap = item.toMap();

        // Create the order
        Map<String, Object> order = new HashMap<>();
        order.put("userId", userId);
        order.put("cartItem", itemMap);
        order.put("orderId", orderId);
//        order.put("state", "ACTIVE");

        // Write the order to the database
        db.child("orders").child(orderId).setValue(order)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firebase", "Order successfully written!");

                        // Empty the user's cart in the database
                        db.child("cart").child(userId).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Firebase", "Cart successfully emptied!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("Firebase", "Error emptying cart", e);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase", "Error writing order", e);
                    }
                });
    }


}