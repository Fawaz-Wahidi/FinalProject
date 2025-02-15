package com.example.onlinecoursese__learningapp.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course_table WHERE id = :courseId")
    Course getCourseById(int courseId);

    @Query("SELECT * FROM course_table")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE category = :category")
    LiveData<List<Course>> getCoursesByCategory(String category);

    @Query("SELECT * FROM course_table WHERE status = :status")
    LiveData<List<Course>> getCoursesByStatus(String status);

    @Query("SELECT * FROM course_table WHERE isBookmarked = 1")
    LiveData<List<Course>> getBookmarkedCourses();

    @Query("UPDATE course_table SET isBookmarked = :isBookmarked WHERE id = :courseId")
    int updateBookmarkStatus(int courseId, int isBookmarked);



}






