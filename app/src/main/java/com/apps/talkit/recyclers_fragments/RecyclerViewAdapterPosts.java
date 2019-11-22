package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;
import com.apps.talkit.classes.PostInfo;

import java.util.ArrayList;

class RecyclerViewAdapterPosts extends RecyclerView.Adapter<RecyclerViewAdapterPosts.postsViewHolder> {
    private static RecyclerViewAdapterPosts.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<PostInfo> posts;

    public RecyclerViewAdapterPosts(Context context, ArrayList<PostInfo> items) {
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
    public void onBindViewHolder(final @NonNull postsViewHolder holder, final int position) {
        holder.post.setText(posts.get(position).getPostText());
        holder.title.setText(posts.get(position).getPostTitle());
        if (position == posts.size()-1) {
            holder.title.setTextSize(15);
        }
        holder.numUpvotes.setText(String.valueOf(posts.get(position).getNumberOfUpvotes()));
        if(posts.get(position).getUpvoted()){
            holder.ib.setImageResource(R.drawable.arrow_upvoted);
        }

        holder.ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!posts.get(position).getUpvoted()) {
                            posts.get(position).setUpvoted(true);
                            posts.get(position).setNumberOfUpvotes(posts.get(position).getNumberOfUpvotes() + 1);
                            holder.numUpvotes.setText(String.valueOf(posts.get(position).getNumberOfUpvotes()));
                            holder.ib.setImageResource(R.drawable.arrow_upvoted);
                        } else {
                            posts.get(position).setUpvoted(false);
                            posts.get(position).setNumberOfUpvotes(posts.get(position).getNumberOfUpvotes() - 1);
                            holder.numUpvotes.setText(String.valueOf(posts.get(position).getNumberOfUpvotes()));
                            holder.ib.setImageResource(R.drawable.arrow_normal);
                        }
                    }
                });
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class postsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView post;
        TextView title;
        TextView numUpvotes;
        ImageButton ib;

        public postsViewHolder(View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.supporting_text);
            title = itemView.findViewById(R.id.primary_text);
            numUpvotes = itemView.findViewById(R.id.num_upvotes);
            ib = itemView.findViewById(R.id.action_button_1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
