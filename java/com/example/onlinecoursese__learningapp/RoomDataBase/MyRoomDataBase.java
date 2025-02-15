package com.example.onlinecoursese__learningapp.RoomDataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Enrollment.class, Course.class, User.class}, version = 6, exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {

    public abstract EnrollmentDAO EnrollmentDAO();
    public abstract UserDAO UserDAO();
    public abstract CourseDAO CourseDAO();
    public abstract LesoonsDAO LessonDao();

    private static volatile MyRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized MyRoomDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDataBase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("Database", "Adding default data...");

            databaseWriteExecutor.execute(() -> {
                CourseDAO courseDao = INSTANCE.CourseDAO();
                UserDAO userDao = INSTANCE.UserDAO();

                courseDao.insert(new Course(1, "Introduction to 3D Design", "Learn 3D design basics", "John Doe", "3D Design","completed",1));
                courseDao.insert(new Course(2, "Business Strategies", "Master business strategies", "Jane Smith", "Business","completed",0));
                courseDao.insert(new Course(3, "Advanced Development", "Explore advanced development topics", "Alice Brown", "Development","ongoing",1));

                User admin = new User(1, "admin", "admin@email.com", "admin123");
                userDao.insertUser(admin);

                Log.d("Database", "Default courses and admin user inserted.");
            });
        }
    };
}


