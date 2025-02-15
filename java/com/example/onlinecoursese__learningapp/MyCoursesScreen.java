package com.example.onlinecoursese__learningapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.databinding.ActivityMyCoursesScreenBinding;

public class MyCoursesScreen extends AppCompatActivity {

    private ActivityMyCoursesScreenBinding binding;
    private CoursesAdapter coursesAdapter;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyCoursesScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        setupTabLayout();

        setupBottomNavigation();
    }

    private void setupRecyclerView() {
        coursesAdapter = new CoursesAdapter(course -> {});

        binding.coursesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.coursesRecyclerView.setAdapter(coursesAdapter);
    }

    private void setupTabLayout() {
        binding.tabLayoutCourses.addTab(binding.tabLayoutCourses.newTab().setText("Ongoing"));
        binding.tabLayoutCourses.addTab(binding.tabLayoutCourses.newTab().setText("Completed"));

        binding.tabLayoutCourses.addOnTabSelectedListener(new com.google.android.material.tabs.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(com.google.android.material.tabs.TabLayout.Tab tab) {
                String status = tab.getText().toString().toLowerCase();
                myViewModel.getCoursesByStatus(status).observe(MyCoursesScreen.this, coursesAdapter::setCourses);
            }

            @Override
            public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(com.google.android.material.tabs.TabLayout.Tab tab) {}
        });

        binding.tabLayoutCourses.selectTab(binding.tabLayoutCourses.getTabAt(0));
    }

    private void setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_courses) {
                return true;
            } else if (item.getItemId() == R.id.nav_home) {
                startActivity(new Intent(MyCoursesScreen.this, Home.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(MyCoursesScreen.this, MyProfile.class));
                return true;
            }
            return false;
        });
    }
}


