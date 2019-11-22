package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;

import java.util.ArrayList;

class RecyclerViewAdapterNotifications extends RecyclerView.Adapter<RecyclerViewAdapterNotifications.notificationsViewHolder>{
    private Context mCtx;
    private static RecyclerViewAdapterNotifications.MyClickListener myClickListener;
    private ArrayList<String> notifications;

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public RecyclerViewAdapterNotifications(Context context, ArrayList<String> items) {
        mCtx = context;
        notifications = items;
    }

    public void setOnItemClickListener(RecyclerViewAdapterNotifications.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public notificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.notifications_list, parent, false);
        return new notificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationsViewHolder holder, int position) {
        holder.notification.setText(notifications.get(position));
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class notificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView notification;
        public notificationsViewHolder(View itemView) {
            super(itemView);
            notification = itemView.findViewById(R.id.notification);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
