package com.example.onlinecoursese__learningapp;


import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.onlinecoursese__learningapp.RoomDataBase.MyViewModel;
import com.example.onlinecoursese__learningapp.RoomDataBase.User;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinecoursese__learningapp.databinding.ActivityMyProfileBinding;
import com.google.android.material.tabs.TabLayoutMediator;
import android.content.Intent;



public class MyProfile extends AppCompatActivity {

    private ActivityMyProfileBinding binding;
    private MyViewModel myViewModel;
    private User currentUser; // تخزين بيانات المستخدم

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // جلب بيانات المستخدم
        loadUserData();

        // عند الضغط على زر التعديل
        binding.editProfileButton.setOnClickListener(v -> showEditProfileDialog());
    }

    private void loadUserData() {
        myViewModel.getAllUsers().observe(this, users -> {
            if (users != null && !users.isEmpty()) {
                currentUser = users.get(0); // يفترض أن لدينا مستخدم واحد
                binding.userName.setText(currentUser.getUsername());
            }
        });
    }

    private void showEditProfileDialog() {
        if (currentUser == null) {
            Toast.makeText(this, "لا يوجد بيانات مستخدم متاحة", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("تعديل الملف الشخصي");

        // إنشاء EditText لكل حقل بيانات
        final EditText editUsername = new EditText(this);
        final EditText editEmail = new EditText(this);
        editUsername.setText(currentUser.getUsername());
        editEmail.setText(currentUser.getEmail());

        // إضافة الحقول إلى Dialog
        builder.setView(editUsername);
        builder.setView(editEmail);

        // زر الحفظ
        builder.setPositiveButton("حفظ", (dialog, which) -> {
            String newUsername = editUsername.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();

            if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
                currentUser.setUsername(newUsername);
                currentUser.setEmail(newEmail);
                myViewModel.updateUser(currentUser);
                binding.userName.setText(newUsername);
                Toast.makeText(MyProfile.this, "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MyProfile.this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("إلغاء", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
