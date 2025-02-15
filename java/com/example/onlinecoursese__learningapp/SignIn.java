package com.example.onlinecoursese__learningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyRoomDataBase;
import com.example.onlinecoursese__learningapp.RoomDataBase.User;
import com.example.onlinecoursese__learningapp.databinding.ActivitySignInBinding;
public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private MyRoomDataBase db;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "CourseHubPrefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = MyRoomDataBase.getInstance(getApplicationContext());
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        boolean isRemembered = sharedPreferences.getBoolean(KEY_REMEMBER, false);
        if (isRemembered) {
            String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            binding.usernameInput.setText(savedUsername);
            binding.passwordInput.setText(savedPassword);
            binding.rememberMe.setChecked(true);

            navigateToAppropriateScreen(savedUsername, savedPassword);
        }

        binding.signinbutton.setOnClickListener(v -> {
            String username = binding.usernameInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                User user = db.UserDAO().getUserByUsernameAndPassword(username, password);
                runOnUiThread(() -> {
                    if (user != null || (username.equals("admin") && password.equals("admin123"))) {
                        Toast.makeText(this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

                        if (binding.rememberMe.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(KEY_REMEMBER, true);
                            editor.putString(KEY_USERNAME, username);
                            editor.putString(KEY_PASSWORD, password);
                            editor.apply();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                        }

                        navigateToAppropriateScreen(username, password);
                    } else {
                        Toast.makeText(this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        binding.signup.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.
                    this, SignUp.class);
            startActivity(intent);
        });
    }

    private void navigateToAppropriateScreen(String username, String password) {
        Intent intent;
        if (username.equals("admin") && password.equals("admin123")) {
            intent = new Intent(SignIn.this, Dashboard.class);
        } else {
            intent = new Intent(SignIn.this, Home.class);
        }
        startActivity(intent);
        finish();
    }
}