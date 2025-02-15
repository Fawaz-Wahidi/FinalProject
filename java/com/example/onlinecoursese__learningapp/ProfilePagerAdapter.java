package com.example.onlinecoursese__learningapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfilePagerAdapter extends FragmentStateAdapter {

    public ProfilePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CoursesFragment();
            case 1:
                return new StudentsFragment();
            case 2:
                return new ReviewsFragment();
            default:
                return new CoursesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
