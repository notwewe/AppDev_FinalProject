package com.example.appdev_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignIn extends Base {
    EditText loginStudID, loginPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_sign_in_landscape);
        } else {
            setContentView(R.layout.activity_sign_in);
        }

        loginStudID = findViewById(R.id.loginstudentIDEditText);
        loginPass = findViewById(R.id.loginpasswordEditText);


        TextView signupRedirect = findViewById(R.id.signUpText);
        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateStudID() | !validatePass()){

                } else {
                    checkUser();
                }

            }
        });


    }


    public Boolean validateStudID (){
        String val = loginStudID.getText().toString();
        if(val.isEmpty()){
            loginStudID.setError("Student ID field cannot be empty");
            return false;
        } else {
            loginStudID.setError(null);
            return true;
        }
    }

    public Boolean validatePass(){
        String val = loginPass.getText().toString();
        if(val.isEmpty()){
            loginPass.setError("Password field cannot be empty");
            return false;
        } else {
            loginPass.setError(null);
            return true;
        }
    }

    public void checkUser (){
        Log.d("SignIn", "Checking user...");
        String userStudID = loginStudID.getText().toString().trim();
        String userPass = loginPass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDB = reference.orderByChild("studID").equalTo(userStudID);

        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("SignIn", "DataSnapshot exists: " + snapshot.exists());
                if (snapshot.exists()){
                    loginStudID.setError(null);
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Log.d("SignIn", "User found: " + ds.getKey());
                        String passwordFromDB = ds.child("userPass").getValue(String.class);
                        if (passwordFromDB.equals(userPass)){
                            loginStudID.setError(null);
                            Intent intent = new Intent(SignIn.this, HomePage.class);
                            startActivity(intent);
                            return; // Exit the loop after finding the user
                        } else {
                            loginPass.setError("Invalid Credentials!");
                            loginPass.requestFocus();
                        }
                    }
                } else{
                    loginStudID.setError("User does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SignIn", "Database error: " + error.getMessage());
            }
        });
    }


//    public void checkUser (){
//        String userStudID = loginStudID.getText().toString().trim();
//        String userPass = loginPass.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDB = reference.orderByChild("studID").equalTo(userStudID);
//
//        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String passwordFromDB = ds.child("userPass").getValue(String.class);
//                    if (passwordFromDB.equals(userPass)) {
//                        loginStudID.setError(null);
//                        Intent intent = new Intent(SignIn.this, HomePage.class);
//                        startActivity(intent);
//                    } else {
//                        loginPass.setError("Invalid Credentials!");
//                        loginPass.requestFocus();
//                    }
//                    return; // Exit the loop after finding the user
//                }
//                // User not found
//                loginStudID.setError("User does not exist");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}
