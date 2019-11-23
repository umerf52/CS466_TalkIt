package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;

import java.util.Map;

public class RecyclerViewAdapterComments extends RecyclerView.Adapter<RecyclerViewAdapterComments.commentsViewHolder> {
    private static RecyclerViewAdapterComments.MyClickListener myClickListener;
    private Context mCtx;
    private Map<String, String> comments;

    public RecyclerViewAdapterComments(Context context, Map<String, String> items) {
        mCtx = context;
        comments = items;
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
        String key = comments.keySet().toArray()[position].toString();
        String value = comments.get(key);
        holder.commentTitle.setText(key);
        holder.commentText.setText(value);
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class commentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView commentTitle;
        TextView commentText;

        public commentsViewHolder(View itemView) {
            super(itemView);
            commentTitle = itemView.findViewById(R.id.primary_text);
            commentText = itemView.findViewById(R.id.supporting_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
