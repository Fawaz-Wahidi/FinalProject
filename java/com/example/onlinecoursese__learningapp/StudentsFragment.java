package com.example.onlinecoursese__learningapp;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StudentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        studentsAdapter = new StudentsAdapter();
        recyclerView.setAdapter(studentsAdapter);

        studentsAdapter.setStudentsList(getSampleStudents());

        return view;
    }

    private List<String> getSampleStudents() {
        return List.of("Benny Spanbauer", "Freida Varnes", "Francene Vandyne", "Tanner Stafford");
    }
}
