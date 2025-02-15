package com.example.onlinecoursese__learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LessonSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SECTION = 0;
    private static final int VIEW_TYPE_LESSON = 1;
    private final List<Object> items;

    public LessonSectionAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Section) {
            return VIEW_TYPE_SECTION;
        } else {
            return VIEW_TYPE_LESSON;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SECTION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.section_item, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lesson_item, parent, false);
            return new LessonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SectionViewHolder) {
            ((SectionViewHolder) holder).bind((Section) items.get(position));
        } else {
            ((LessonViewHolder) holder).bind((Lesson) items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView sectionTitle, sectionDuration;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionTitle = itemView.findViewById(R.id.section_title);
            sectionDuration = itemView.findViewById(R.id.section_duration);
        }

        public void bind(Section section) {
            sectionTitle.setText(section.getTitle());
            sectionDuration.setText(section.getDuration());
        }
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        private final TextView lessonTitle, lessonDuration;
        private final ImageView lessonStatusIcon;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonTitle = itemView.findViewById(R.id.lesson_title);
            lessonDuration = itemView.findViewById(R.id.lesson_duration);
            lessonStatusIcon = itemView.findViewById(R.id.lesson_status_icon);
        }

        public void bind(Lesson lesson) {
            lessonTitle.setText(lesson.getTitle());
            lessonDuration.setText(lesson.getDuration());
            lessonStatusIcon.setImageResource(
                    lesson.isUnlocked() ? R.drawable.ic_play : R.drawable.ic_lock);
        }

    }
}
