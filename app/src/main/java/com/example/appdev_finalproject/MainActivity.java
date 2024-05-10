package com.example.appdev_finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListeners();
    }

    private void setupListeners() {
        View constraintLayout = findViewById(R.id.startclick);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });
    }
}
