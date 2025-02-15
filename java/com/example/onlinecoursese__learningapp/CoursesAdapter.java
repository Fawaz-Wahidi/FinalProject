package com.example.onlinecoursese__learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import java.util.ArrayList;
import java.util.List;



public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
        default void onBookmarkClick(Course course) {}
    }

    private List<Course> courseList = new ArrayList<>();
    private final OnCourseClickListener listener;

    public CoursesAdapter(OnCourseClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.bind(course, listener);
    }

    @Override
    public int getItemCount() {
        return courseList == null ? 0 : courseList.size();
    }

    public void setCourses(List<Course> courses) {
        if (courses != null) {
            this.courseList = new ArrayList<>(courses);
        } else {
            this.courseList.clear();
        }
        notifyDataSetChanged();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitle;
        private final ImageView bookmarkButton;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.course_title);
            bookmarkButton = itemView.findViewById(R.id.bookmark_icon);
        }

        public void bind(Course course, OnCourseClickListener listener) {
            courseTitle.setText(course.getTitle());
            itemView.setOnClickListener(v -> listener.onCourseClick(course));

            if (course.getIsBookmarked() == 1) {
                bookmarkButton.setImageResource(R.drawable.bookmarked);
            } else {
                bookmarkButton.setImageResource(R.drawable.bookmark);
            }

            bookmarkButton.setVisibility(View.VISIBLE);

            bookmarkButton.setOnClickListener(v -> listener.onBookmarkClick(course));
        }
    }
}


