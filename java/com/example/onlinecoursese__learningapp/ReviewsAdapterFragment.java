package com.example.onlinecoursese__learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapterFragment extends RecyclerView.Adapter<ReviewsAdapterFragment.ReviewViewHolder> {

    private List<String> reviewsList = new ArrayList<>();

    public void setReviewsList(List<String> newReviewsList) {
        reviewsList.clear();
        if (newReviewsList != null && !newReviewsList.isEmpty()) {
            reviewsList.addAll(newReviewsList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_reviews, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        String reviewText = reviewsList.get(position);
        holder.reviewText.setText(reviewText);

        holder.itemView.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewText;
        ImageView reviewerProfile;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewText = itemView.findViewById(R.id.review_text);
            reviewerProfile = itemView.findViewById(R.id.reviewer_profile_image);
        }
    }
}

