package com.example.onlinecoursese__learningapp;

import android.annotation.SuppressLint;
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

public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewsAdapterFragment reviewsAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_reviews);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        reviewsAdapter = new ReviewsAdapterFragment();
        recyclerView.setAdapter(reviewsAdapter);

        reviewsAdapter.setReviewsList(getSampleReviews());

        return view;
    }

    private List<String> getSampleReviews() {
        return List.of("The course is very good.", "Awesome! This is what I was looking for.");
    }
}

