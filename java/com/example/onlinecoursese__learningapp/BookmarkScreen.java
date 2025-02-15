package com.example.onlinecoursese__learningapp;

import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.databinding.ActivityBookmarkScreenBinding;

//public class BookmarkScreen extends AppCompatActivity {
//
//    private ActivityBookmarkScreenBinding binding;
//    private CoursesAdapter coursesAdapter;
//    private MyViewModel myViewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityBookmarkScreenBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//
//        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
//
//
//        setupRecyclerView();
//
//
//        myViewModel.getBookmarkedCourses().observe(this, courses -> {
//            if (courses != null && !courses.isEmpty()) {
//                Log.d("RecyclerView", "Courses loaded: " + courses.size());
//                coursesAdapter.setCourses(courses);
//            } else {
//                Log.d("RecyclerView", "No courses loaded");
//                coursesAdapter.setCourses(null);
//            }
//        });
//    }
//
//    private void setupRecyclerView() {
//        coursesAdapter = new CoursesAdapter(new CoursesAdapter.OnCourseClickListener() {
//
//            @Override
//            public void onCourseClick(Course course) {
//            }
//
//            @Override
//            public void onBookmarkClick(Course course) {
//
//            }
//        });
//
//        binding.recyclerViewBookmarkedCourses.setLayoutManager(new LinearLayoutManager(this));
//        binding.recyclerViewBookmarkedCourses.setAdapter(coursesAdapter);
//    }
//
//    private void toggleBookmark(Course course) {
//        int newBookmarkStatus = course.getIsBookmarked() == 0 ? 1 : 0;
//        course.setIsBookmarked(newBookmarkStatus);
//
//        myViewModel.updateBookmarkStatus(course.getId(), newBookmarkStatus);
//
//        coursesAdapter.notifyDataSetChanged();
//    }
//
//
//}

public class BookmarkScreen extends AppCompatActivity {

    private ActivityBookmarkScreenBinding binding;
    private CoursesAdapter coursesAdapter;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookmarkScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        setupRecyclerView();

        myViewModel.getBookmarkedCourses().observe(this, courses -> {
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
                // فتح تفاصيل الكورس (مستقبلاً)
            }

            @Override
            public void onBookmarkClick(Course course) {
                showDeleteConfirmationDialog(course);
            }
        });

        binding.recyclerViewBookmarkedCourses.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewBookmarkedCourses.setAdapter(coursesAdapter);
    }

    private void showDeleteConfirmationDialog(Course course) {
        new AlertDialog.Builder(this)
                .setTitle("حذف الكورس")
                .setMessage("هل أنت متأكد أنك تريد إزالة هذا الكورس من المفضلة؟")
                .setPositiveButton("نعم", (dialog, which) -> deleteBookmarkedCourse(course))
                .setNegativeButton("إلغاء", null)
                .show();
    }

    private void deleteBookmarkedCourse(Course course) {
        myViewModel.updateBookmarkStatus(course.getId(), 0); // إزالة الكورس من المفضلة
        Toast.makeText(this, "تم حذف الكورس من المفضلة", Toast.LENGTH_SHORT).show();
    }
}





