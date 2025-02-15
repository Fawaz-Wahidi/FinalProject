package com.example.onlinecoursese__learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class NavigationActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    Button nextButton;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        slideViewPager = findViewById(R.id.slideViewPager);
        nextButton = findViewById(R.id.nextButton);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = slideViewPager.getCurrentItem();
                if (currentItem < viewPagerAdapter.getCount() - 1) {
                    slideViewPager.setCurrentItem(currentItem + 1);
                } else {
                    Intent intent = new Intent(NavigationActivity.this, get_started.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
