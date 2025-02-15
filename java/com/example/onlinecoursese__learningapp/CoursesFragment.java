package com.example.onlinecoursese__learningapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.onlinecoursese__learningapp.RoomDataBase.Course;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;





public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CoursesAdapter coursesAdapter;
    private MyViewModel myViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // ✅ تمرير `OnCourseClickListener` الصحيح عند إنشاء الـ Adapter
        coursesAdapter = new CoursesAdapter(new CoursesAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClick(Course course) {
                // هنا يمكنك التعامل مع النقر على الكورس (فتح التفاصيل مثلًا)
            }

        });

        recyclerView.setAdapter(coursesAdapter);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getAllCourses().observe(getViewLifecycleOwner(), courses -> {
            if (courses != null) {
                coursesAdapter.setCourses(courses);
            }
        });

        return view;
    }

    private void toggleBookmark(Course course) {
        int newBookmarkStatus = course.getIsBookmarked() == 0 ? 1 : 0;
        course.setIsBookmarked(newBookmarkStatus);

        myViewModel.updateBookmarkStatus(course.getId(), newBookmarkStatus);

        coursesAdapter.notifyDataSetChanged();
    }
}
