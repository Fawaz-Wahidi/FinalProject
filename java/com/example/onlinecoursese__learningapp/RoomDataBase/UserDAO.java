package com.example.onlinecoursese__learningapp.RoomDataBase;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
     void deleteUser(User user);

    @Query("Select * From users_table Where id=:userId")
    User getUserById(long userId);

@Query("Select * From users_table")
LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("UPDATE users_table SET username = :newUsername, email = :newEmail WHERE id = :userId")
    void updateUserProfile(long userId, String newUsername, String newEmail);

}
