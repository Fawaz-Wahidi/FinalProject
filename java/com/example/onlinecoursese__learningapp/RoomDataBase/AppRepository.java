package com.example.onlinecoursese__learningapp.RoomDataBase;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Without Lessons Entity
public class AppRepository {
    private final CourseDAO courseDao;
    private final EnrollmentDAO enrollmentDao;
    private final UserDAO userDao;

    private final LiveData<List<Course>> allCourses;
    private final LiveData<List<User>> allUsers;
    private final LiveData<List<Enrollment>> allEnrollments;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public AppRepository(Application application) {
        MyRoomDataBase db = MyRoomDataBase.getInstance(application);
        courseDao = db.CourseDAO();
        enrollmentDao = db.EnrollmentDAO();
        userDao = db.UserDAO();

        allCourses = courseDao.getAllCourses();
        allUsers = userDao.getAllUsers();
        allEnrollments = enrollmentDao.getAllEnrollment();
    }


    public void insertCourse(Course course) {
        databaseWriteExecutor.execute(() -> courseDao.insert(course));
    }

    public void updateCourse(Course course) {
        databaseWriteExecutor.execute(() -> courseDao.update(course));
    }

    public void deleteCourse(Course course) {
        databaseWriteExecutor.execute(() -> courseDao.delete(course));
    }

    public LiveData<List<Enrollment>> getAllEnrollments() {
        return allEnrollments;
    }

    public void insertEnrollment(Enrollment enrollment) {
        databaseWriteExecutor.execute(() -> enrollmentDao.insert(enrollment));
    }

    public void deleteEnrollment(Enrollment enrollment) {
        databaseWriteExecutor.execute(() -> enrollmentDao.delete(enrollment));
    }


    public void insertUser(User user) {
        databaseWriteExecutor.execute(() -> userDao.insertUser(user));
    }

    public void updateUser(User user) {
        databaseWriteExecutor.execute(() -> userDao.updateUser(user));
    }

    public void deleteUser(User user) {
        databaseWriteExecutor.execute(() -> userDao.deleteUser(user));
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }


    public LiveData<List<Course>> getCoursesByCategory(String category) {
        return courseDao.getCoursesByCategory(category);
    }

    public LiveData<List<Course>> getCoursesByStatus(String status) {
        return courseDao.getCoursesByStatus(status); // استخدم courseDao بدلاً من استدعائها كـ static
    }

    public LiveData<List<Course>> getBookmarkedCourses() {
        Log.d("ViewModel", "Fetching bookmarked courses...");
        return courseDao.getBookmarkedCourses();
    }

    public void updateBookmarkStatus(int courseId, int isBookmarked) {
        databaseWriteExecutor.execute(() -> courseDao.updateBookmarkStatus(courseId, isBookmarked));
    }


    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }


    public int getCourseById(int courseId) {
        return courseId;
    }



}

