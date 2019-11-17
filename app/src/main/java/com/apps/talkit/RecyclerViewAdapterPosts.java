package com.apps.talkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapterPosts extends RecyclerView.Adapter<RecyclerViewAdapterPosts.postsViewHolder> {
    private static RecyclerViewAdapterPosts.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<String> posts;

    public RecyclerViewAdapterPosts(Context context, ArrayList<String> items) {
        mCtx = context;
        posts = items;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterPosts.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public postsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.posts_list, parent, false);
        return new postsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postsViewHolder holder, int position) {
        holder.post.setText(posts.get(position));
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class postsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView post;

        public postsViewHolder(View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.post);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
