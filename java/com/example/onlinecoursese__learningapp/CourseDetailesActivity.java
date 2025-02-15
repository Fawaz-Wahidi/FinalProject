package com.example.onlinecoursese__learningapp;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlinecoursese__learningapp.databinding.ActivityCourseDetailesBinding;
import com.google.android.material.tabs.TabLayoutMediator;


public class CourseDetailesActivity extends AppCompatActivity {

    private ActivityCourseDetailesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseDetailesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViewPager();
        setupSeeMoreButton();

    }

    private void setupViewPager() {
        binding.viewPager.setAdapter(new CourseDetailsPagerAdapter(this));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("About");
                            tab.setContentDescription("About Tab");
                            break;
                        case 1:
                            tab.setText("Lessons");
                            tab.setContentDescription("Lessons Tab");
                            break;
                        case 2:
                            tab.setText("Reviews");
                            tab.setContentDescription("Reviews Tab");
                            break;
                    }
                }).attach();
    }

    private void setupSeeMoreButton() {
        binding.seemore.setOnClickListener(v -> {
            binding.viewPager.setCurrentItem(1, true);
        });
    }


    private static class CourseDetailsPagerAdapter extends FragmentStateAdapter {
        public CourseDetailsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new About();
                case 1:
                    return new Lessons();
                case 2:
                    return new Reviews();
                default:
                    return new About();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
