package com.example.onlinecoursese__learningapp.RoomDataBase;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.onlinecoursese__learningapp.Lesson;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private final AppRepository appRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Course>> getAllCourses() {
        Log.d("ViewModel", "Fetching all courses...");
        return appRepository.getAllCourses();
    }

    public LiveData<List<Course>> getCoursesByCategory(String category) {
        return appRepository.getCoursesByCategory(category);
    }

    public LiveData<List<Course>> getCoursesByStatus(String status) {
        Log.d("ViewModel", "Fetching courses by status: " + status);
        return appRepository.getCoursesByStatus(status);
    }

    public LiveData<List<Course>> getBookmarkedCourses() {
        Log.d("ViewModel", "Fetching bookmarked courses...");
        return appRepository.getBookmarkedCourses();
    }

    public void updateBookmarkStatus(int courseId, int isBookmarked) {
        Log.d("ViewModel", "Updating bookmark status for courseId: " + courseId + " to " + isBookmarked);
        appRepository.updateBookmarkStatus(courseId, isBookmarked);
    }

    public LiveData<List<User>> getAllUsers() {
        return appRepository.getAllUsers();
    }

    public void insertCourse(Course course) {
        appRepository.insertCourse(course);
    }


    public void updateCourse(Course course) {
        appRepository.updateCourse(course);
    }


    public void deleteCourse(Course course) {
        appRepository.deleteCourse(course);
    }

    public int getCourseById(int courseId) {
        return appRepository.getCourseById(courseId);
    }

    public void insertUser(User user) {
        appRepository.insertUser(user);
    }

    public void updateUser(User user) {
        appRepository.updateUser(user);
    }

    public void deleteUser(User user) {
        appRepository.deleteUser(user);
    }


    public LiveData<List<Enrollment>> getAllEnrollments() {
        return appRepository.getAllEnrollments();
    }




}

