package com.example.onlinecoursese__learningapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.databinding.ActivityCourseDialogBinding;

public class CourseDialog {

    public interface DialogListener {
        void onCourseSaved(Course course);
    }

    public static void showCourseDialog(Context context, MyViewModel myViewModel, DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ActivityCourseDialogBinding binding = ActivityCourseDialogBinding.inflate(LayoutInflater.from(context));
        builder.setView(binding.getRoot());

        AlertDialog dialog = builder.create();

        binding.btnSaveCourse.setOnClickListener(v -> {
            String title = binding.editCourseTitle.getText().toString().trim();
            String description = binding.editCourseDescription.getText().toString().trim();
            String instructor = binding.editCourseInstructor.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || instructor.isEmpty()) {
                Toast.makeText(context, "الرجاء ملء جميع الحقول", Toast.LENGTH_SHORT).show();
            } else {
                Course newCourse = new Course(0, title, description, instructor, "عام", "ongoing", 0);
                myViewModel.insertCourse(newCourse);
                listener.onCourseSaved(newCourse);
                dialog.dismiss();
            }
        });


        binding.btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
