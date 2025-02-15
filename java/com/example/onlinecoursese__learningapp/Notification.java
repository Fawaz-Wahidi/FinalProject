package com.example.onlinecoursese__learningapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        RecyclerView recyclerView = findViewById(R.id.notification_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Object> notificationItems = new ArrayList<>();
        notificationItems.add("Today");
        notificationItems.add(new NotificationItem("Payment Successful!", "You have made a course payment"));
        notificationItems.add(new NotificationItem("Special Offer!", "You get a special promo today!"));
        notificationItems.add("Yesterday");
        notificationItems.add(new NotificationItem("New Course Added!", "Check out the new 3D Design course"));
        notificationItems.add(new NotificationItem("Account Updated!", "Your account information has been updated"));

        notificationItems.add("Today");
        notificationItems.add(new NotificationItem("New Course Available!", "A new Python programming course is now available."));
        notificationItems.add(new NotificationItem("Promotion Extended!", "The special offer has been extended for another week."));

        notificationItems.add("2 Days ago");
        notificationItems.add(new NotificationItem("Payment Reminder", "Your payment is due tomorrow."));
        notificationItems.add(new NotificationItem("System Maintenance", "Scheduled maintenance for the platform tomorrow."));

        notificationItems.add("3 Days ago");
        notificationItems.add(new NotificationItem("Payment Reminder", "Your payment is due tomorrow."));
        notificationItems.add(new NotificationItem("System Maintenance", "Scheduled maintenance for the platform tomorrow."));

        notificationItems.add("4 Days ago");
        notificationItems.add(new NotificationItem("Payment Reminder", "Your payment is due tomorrow."));
        notificationItems.add(new NotificationItem("System Maintenance", "Scheduled maintenance for the platform tomorrow."));

        notificationItems.add("5 Days ago");
        notificationItems.add(new NotificationItem("Payment Reminder", "Your payment is due tomorrow."));
        notificationItems.add(new NotificationItem("System Maintenance", "Scheduled maintenance for the platform tomorrow."));

        NotificationAdapter adapter = new NotificationAdapter(notificationItems);
        recyclerView.setAdapter(adapter);
    }

    public static class NotificationItem {
        private final String title;
        private final String description;

        public NotificationItem(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
