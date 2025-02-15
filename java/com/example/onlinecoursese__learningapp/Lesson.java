package com.example.onlinecoursese__learningapp;

public class Lesson {

    private final int number;
    private final String title;
    private final String duration;
    private final boolean isUnlocked;

    public Lesson(int number, String title, String duration, boolean isUnlocked) {
        this.number = number;
        this.title = title;
        this.duration = duration;
        this.isUnlocked = isUnlocked;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }


}
