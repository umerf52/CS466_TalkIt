package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.ChatActivity;
import com.apps.talkit.R;

import java.util.Map;

public class RecyclerViewAdapterComments extends RecyclerView.Adapter<RecyclerViewAdapterComments.commentsViewHolder> {
    private static RecyclerViewAdapterComments.MyClickListener myClickListener;
    private Context mCtx;
    private Map<String, String> comments;
    private int theme;

    public RecyclerViewAdapterComments(Context context, Map<String, String> items, int the) {
        mCtx = context;
        comments = items;
        theme = the;
    }

    @Override
    public int getItemCount() {
        try {
            return comments.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void setOnItemClickListener(RecyclerViewAdapterComments.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public commentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.comments_list, parent, false);
        return new commentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull commentsViewHolder holder, final int position) {
        final String key = comments.keySet().toArray()[position].toString();
        String value = comments.get(key);
        holder.commentTitle.setText(key);
        holder.commentText.setText(value);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title", key);
                intent.putExtra("theme",theme);
                mCtx.startActivity(intent);
            }
        });
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class commentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView commentTitle;
        TextView commentText;
        Button button;

        public commentsViewHolder(View itemView) {
            super(itemView);
            commentTitle = itemView.findViewById(R.id.primary_text);
            commentText = itemView.findViewById(R.id.supporting_text);
            button = itemView.findViewById(R.id.action_button_3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
