package com.example.onlinecoursese__learningapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.databinding.ActivityHomeBinding;
import android.util.Log;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private CoursesAdapter coursesAdapter;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        setupTabLayout();

        setupBottomNavigation();

        binding.profileImage.setOnClickListener(v -> startActivity(new Intent(Home.this, CourseDetailesActivity.class)));
        binding.bookmarkIcon.setOnClickListener(v -> startActivity(new Intent(Home.this, BookmarkScreen.class)));
        binding.userName.setOnClickListener(v -> startActivity(new Intent(Home.this, MyProfile.class)));
        binding.notificationCL.setOnClickListener(v -> startActivity(new Intent(Home.this, Notification.class)));

        myViewModel.getAllCourses().observe(this, courses -> {
            if (courses != null && !courses.isEmpty()) {
                Log.d("RecyclerView", "Courses loaded: " + courses.size());
                coursesAdapter.setCourses(courses);
            } else {
                Log.d("RecyclerView", "No courses loaded");
                coursesAdapter.setCourses(null);
            }
        });
    }

    private void setupRecyclerView() {
        coursesAdapter = new CoursesAdapter(new CoursesAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClick(Course course) {
                if (course != null) {
                    Intent intent = new Intent(Home.this, CourseDetailesActivity.class);
                    intent.putExtra("courseId", course.getId());
                    startActivity(intent);
                }
            }

            @Override
            public void onBookmarkClick(Course course) {
                if (course != null) {
                    int newStatus = (course.getIsBookmarked() == 1) ? 0 : 1;
                    course.setIsBookmarked(newStatus);
                    myViewModel.updateBookmarkStatus(course.getId(), newStatus);
                    coursesAdapter.notifyDataSetChanged();
                }
            }
        });

        binding.coursesRecyclerHome.setLayoutManager(new LinearLayoutManager(this));
        binding.coursesRecyclerHome.setAdapter(coursesAdapter);
    }

    private void setupTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("3D Design"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Business"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Development"));

        binding.tabLayout.addOnTabSelectedListener(new com.google.android.material.tabs.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(com.google.android.material.tabs.TabLayout.Tab tab) {
                String category = tab.getText().toString();

                if (category.equals("All")) {
                    myViewModel.getAllCourses().observe(Home.this, coursesAdapter::setCourses);
                } else {
                    myViewModel.getCoursesByCategory(category).observe(Home.this, coursesAdapter::setCourses);
                }
            }

            @Override
            public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(com.google.android.material.tabs.TabLayout.Tab tab) {}
        });
    }

    private void setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                return true;
            } else if (item.getItemId() == R.id.nav_courses) {
                startActivity(new Intent(Home.this, MyCoursesScreen.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(Home.this, MyProfile.class));
                return true;
            }
            return false;
        });
    }



}


