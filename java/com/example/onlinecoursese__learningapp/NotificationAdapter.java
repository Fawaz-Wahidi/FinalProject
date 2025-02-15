package com.example.onlinecoursese__learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_DATE = 0;
    private static final int TYPE_NOTIFICATION = 1;

    private final List<Object> notificationItems;

    public NotificationAdapter(List<Object> items) {
        this.notificationItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (notificationItems.get(position) instanceof String) {
            return TYPE_DATE;
        } else {
            return TYPE_NOTIFICATION;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DATE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
            return new DateViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
            return new NotificationViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_DATE) {
            ((DateViewHolder) holder).bind((String) notificationItems.get(position));
        } else {
            ((NotificationViewHolder) holder).bind((Notification.NotificationItem) notificationItems.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return notificationItems.size();
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateText;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.date_text);
        }

        public void bind(String date) {
            dateText.setText(date);
        }
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notification_title);
           description = itemView.findViewById(R.id.notificationdescription);
        }

        public void bind(Notification.NotificationItem notification) {
            title.setText(notification.getTitle());
            description.setText(notification.getDescription());
        }
    }
}
