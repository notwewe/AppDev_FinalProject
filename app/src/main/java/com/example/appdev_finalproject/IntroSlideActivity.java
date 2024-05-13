package com.example.appdev_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class IntroSlideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPager titleViewPager;
    private IntroSlidePagerAdapter adapter;
    private List<IntroSlidePage> pages;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slide_page);

        viewPager = findViewById(R.id.viewPager);

        titleViewPager = findViewById(R.id.titleViewPager);

        Button getStartedButton = findViewById(R.id.getStartedButton);

        titleTextView = findViewById(R.id.titleTextView);

        //FullScreenHelper.setFullScreen(this);

        pages = new ArrayList<>();
        pages.add(new IntroSlidePage(R.drawable.imageslide1, "Welcome to \n CIT-U Wild Canteen"));
        pages.add(new IntroSlidePage(R.drawable.imageslide2, "Enjoy a Hassle-Free Experience"));
        pages.add(new IntroSlidePage(R.drawable.imageslide3, "Order Food \n in Advance"));

        adapter = new IntroSlidePagerAdapter(getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);

        IntroSlideTitlePagerAdapter titleAdapter = new IntroSlideTitlePagerAdapter(getSupportFragmentManager(), pages);
        titleViewPager.setAdapter(titleAdapter);

        titleTextView.setText(pages.get(0).getTitle());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                titleViewPager.setCurrentItem(position, true);
                titleTextView.setText(pages.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroSlideActivity.this, SignIn.class));
            }
        });
    }
}
