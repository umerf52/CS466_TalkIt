package com.apps.talkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapterChats extends RecyclerView.Adapter<RecyclerViewAdapterChats.notificationsViewHolder>{
    private Context mCtx;
    private static RecyclerViewAdapterChats.MyClickListener myClickListener;
    private ArrayList<String> notifications;

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public RecyclerViewAdapterChats(Context context, ArrayList<String> items) {
        mCtx = context;
        notifications = items;
    }

    public void setOnItemClickListener(RecyclerViewAdapterChats.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public notificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.chats_list, parent, false);
        CardView cardView = view.findViewById(R.id.cardChat);
        cardView.setCardBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
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
            notification = itemView.findViewById(R.id.chat);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
