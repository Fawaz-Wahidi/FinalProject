package com.example.onlinecoursese__learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecoursese__learningapp.databinding.ActivityGetstartedBinding;

public class get_started extends AppCompatActivity {
ActivityGetstartedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_getstarted);

        binding= ActivityGetstartedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signInBtnGetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(get_started.this, SignIn.class);
                startActivity(intent);
            }
        });
        binding.signupTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(get_started.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}