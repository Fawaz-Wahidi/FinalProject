package com.example.onlinecoursese__learningapp.RoomDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Enrollment")

public class Enrollment {
//    id,userId,courseId

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;

    private int courseId;

    public Enrollment(){

    }

    public Enrollment(int id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}

