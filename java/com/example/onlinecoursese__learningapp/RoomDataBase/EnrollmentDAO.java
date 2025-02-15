package com.example.onlinecoursese__learningapp.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface EnrollmentDAO {
    @Insert
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);


    @Query("SELECT * FROM Enrollment WHERE userId = :userId AND courseId = :courseId")
    Enrollment getEnrollmentByUserAndCourse(int userId, int courseId);

    @Query("SELECT * FROM Enrollment WHERE userId = :userId")
    LiveData<List<Enrollment>> getEnrollmentsByUserId(int userId);

    @Query("SELECT * FROM Enrollment WHERE courseId = :courseId")
    LiveData <List<Enrollment>> getEnrollmentsByCourseId(int courseId);

    @Query("SELECT * FROM Enrollment")
    LiveData<List<Enrollment>> getAllEnrollment();
}
