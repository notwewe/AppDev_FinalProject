package com.example.appdev_finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Base {

    EditText signupName, signupStudID, signupEmail, signupPass;
    Button signupBtn;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_sign_up_landscape);
        } else {
            setContentView(R.layout.activity_sign_up);
        }


        signupName = findViewById(R.id.nameEditText);
        signupStudID = findViewById(R.id.loginstudentIDEditText);
        signupEmail = findViewById(R.id.emailEditText);
        signupPass = findViewById(R.id.loginpasswordEditText);
        signupBtn = findViewById(R.id.signUpButton);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String studID = signupStudID.getText().toString();
                String email = signupEmail.getText().toString();
                String pass = signupPass.getText().toString();

                UserHelper UH = new UserHelper(name, studID, email, pass);
                reference.child(studID).setValue(UH);

                Toast.makeText(SignUp.this, "You have sign up successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });


        TextView loginText = findViewById(R.id.LoginText);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });
    }
}
