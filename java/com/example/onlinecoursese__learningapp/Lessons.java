package com.example.onlinecoursese__learningapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlinecoursese__learningapp.databinding.FragmentLessonsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class Lessons extends Fragment {

    private FragmentLessonsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLessonsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Object> items = new ArrayList<>();
        items.add(new Section("Section 1 - Introduction", "15 mins"));
        items.add(new Lesson(1, "Why Using Figma", "10 mins", true));
        items.add(new Lesson(2, "Set up Your Figma Account", "5 mins", false));
        items.add(new Section("Section 2 - Figma Basic", "60 mins"));
        items.add(new Lesson(3, "Take a Look Figma Interface", "15 mins", false));
        items.add(new Lesson(4, "Working with Frame & Layer", "10 mins", false));
        items.add(new Lesson(5, "Working with Text & Grids", "10 mins", false));

        binding.lessonsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.lessonsRecyclerView.setAdapter(new LessonSectionAdapter(items));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
