package com.example.onlinecoursese__learningapp;

public class Section {
    private final String title;
    private final String duration;

    public Section(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }
}
