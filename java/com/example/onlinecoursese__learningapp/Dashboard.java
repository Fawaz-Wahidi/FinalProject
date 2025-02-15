package com.example.onlinecoursese__learningapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private MyViewModel myViewModel;
    private CoursesAdapter coursesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        setupRecyclerView();
        observeDashboardData();

        binding.addCourseButton.setOnClickListener(v -> showAddCourseDialog());
    }

    private void observeDashboardData() {
        myViewModel.getAllCourses().observe(this, courses -> {
            if (courses != null) {
                binding.totalCourses.setText(String.valueOf(courses.size()));
                coursesAdapter.setCourses(courses);
            } else {
                binding.totalCourses.setText("0");
            }
        });

        myViewModel.getAllUsers().observe(this, users -> {
            if (users != null) {
                binding.totalUsers.setText(String.valueOf(users.size()));
            } else {
                binding.totalUsers.setText("0");
            }
        });
    }

    private void setupRecyclerView() {
        coursesAdapter = new CoursesAdapter(new CoursesAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClick(Course course) {
                showEditOrDeleteDialog(course);
            }

            @Override
            public void onBookmarkClick(Course course) {

            }
        });

        binding.coursesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.coursesRecyclerView.setAdapter(coursesAdapter);
    }

    private void showAddCourseDialog() {
        CourseDialog.showCourseDialog(this, myViewModel, course -> {
            Toast.makeText(this, "تمت إضافة الكورس!", Toast.LENGTH_SHORT).show();
        });
    }

    private void showEditOrDeleteDialog(Course course) {
        new AlertDialog.Builder(this)
                .setTitle("إدارة الكورس")
                .setMessage("ماذا تريد أن تفعل بهذا الكورس؟")
                .setPositiveButton("تعديل", (dialog, which) -> showEditCourseDialog(course))
                .setNegativeButton("حذف", (dialog, which) -> showDeleteCourseDialog(course))
                .setNeutralButton("إلغاء", null)
                .show();
    }

    private void showEditCourseDialog(Course course) {
        CourseDialog.showCourseDialog(this, myViewModel, updatedCourse -> {
            updatedCourse.setId(course.getId());
            myViewModel.updateCourse(updatedCourse);
            Toast.makeText(this, "تم تحديث بيانات الكورس!", Toast.LENGTH_SHORT).show();
        });
    }

    private void showDeleteCourseDialog(Course course) {
        new AlertDialog.Builder(this)
                .setTitle("حذف الكورس")
                .setMessage("هل أنت متأكد أنك تريد حذف هذا الكورس؟")
                .setPositiveButton("نعم", (dialog, which) -> {
                    myViewModel.deleteCourse(course);
                    Toast.makeText(Dashboard.this, "تم حذف الكورس!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("إلغاء", null)
                .show();
    }
}
