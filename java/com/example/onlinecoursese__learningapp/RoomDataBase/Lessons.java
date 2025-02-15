package com.example.onlinecoursese__learningapp.RoomDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lessons {
    @PrimaryKey(autoGenerate = true)
    public int lessonId;

    public int courseId;
    public String title;
    public boolean isCompleted;

    public Lessons(int courseId, String title, boolean isCompleted) {
        this.courseId = courseId;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
