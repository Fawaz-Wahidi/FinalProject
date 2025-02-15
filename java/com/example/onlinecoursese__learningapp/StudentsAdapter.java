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

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private List<String> studentsList = new ArrayList<>();

    public void setStudentsList(List<String> newStudentsList) {
        studentsList.clear();
        if (newStudentsList != null && !newStudentsList.isEmpty()) {
            studentsList.addAll(newStudentsList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_fragment_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        String studentName = studentsList.get(position);
        holder.studentName.setText(studentName);

        holder.itemView.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        ImageView studentProfile;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            studentProfile = itemView.findViewById(R.id.student_profile_image);
        }
    }
}
