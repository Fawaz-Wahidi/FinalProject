package com.example.onlinecoursese__learningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecoursese__learningapp.RoomDataBase.MyRoomDataBase;
import com.example.onlinecoursese__learningapp.RoomDataBase.User;
import com.example.onlinecoursese__learningapp.databinding.ActivitySignUpBinding;


public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private MyRoomDataBase db;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CourseHubPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = MyRoomDataBase.getInstance(getApplicationContext());

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)) {
            navigateToHome();
        }

        binding.backbutton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });

        binding.signupbutton.setOnClickListener(v -> {
            String username = binding.usernameInput.getText().toString().trim();
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);

                db.UserDAO().insertUser(newUser);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USERNAME, username);
                editor.putString(KEY_EMAIL, email);
                editor.putString(KEY_PASSWORD, password);
                editor.putBoolean(KEY_IS_LOGGED_IN, true);
                editor.apply();

                runOnUiThread(() -> {
                    Toast.makeText(this, "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show();
                    navigateToHome();
                });
            }).start();
        });

        binding.signinTV.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(SignUp.this, Home.class);
        startActivity(intent);
        finish();
    }
}