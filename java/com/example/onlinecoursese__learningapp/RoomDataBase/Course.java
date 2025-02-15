package com.example.onlinecoursese__learningapp.RoomDataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String instructor;
    @NonNull
    private String category;
    @NonNull
    private String status;

    private int isBookmarked =0;



    public Course(int id, @NonNull String title, @NonNull String description, @NonNull String instructor,
                  @NonNull String category, @NonNull String status, int isBookmarked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.category = category;
        this.status = status;
        this.isBookmarked = isBookmarked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(@NonNull String instructor) {
        this.instructor = instructor;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    public int getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(int isBookmarked) {
        this.isBookmarked = isBookmarked;
    }
}
