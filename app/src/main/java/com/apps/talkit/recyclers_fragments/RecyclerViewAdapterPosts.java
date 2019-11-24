package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.ChatActivity;
import com.apps.talkit.ExpandedPostActivity;
import com.apps.talkit.R;
import com.apps.talkit.classes.PostInfo;

import java.util.ArrayList;

class RecyclerViewAdapterPosts extends RecyclerView.Adapter<RecyclerViewAdapterPosts.postsViewHolder> {
    private static RecyclerViewAdapterPosts.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<PostInfo> posts;
    private int theme;

    public RecyclerViewAdapterPosts(Context context, ArrayList<PostInfo> items, int the) {
        mCtx = context;
        posts = items;
        theme = the;
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
        if (posts.get(position).getIsTrigger()) {
            holder.post.setTypeface(holder.post.getTypeface(), Typeface.ITALIC);
            holder.post.setText("This post contains a trigger warning");
            holder.title.setVisibility(View.GONE);
        } else {
            holder.post.setTypeface(holder.post.getTypeface(), Typeface.NORMAL);
            holder.post.setText(posts.get(position).getPostText());
            holder.title.setText(posts.get(position).getPostTitle());
            holder.title.setVisibility(View.VISIBLE);
        }
        if (!posts.get(position).getChatEnabled()) {
            holder.chatButton.setVisibility(View.INVISIBLE);
        }
        else{
            holder.chatButton.setVisibility(View.VISIBLE);
        }
        if (position == posts.size()-1) {
            holder.title.setTypeface(holder.title.getTypeface(), Typeface.ITALIC);
            holder.title.setTextSize(15);
            holder.cardView.setClickable(false);
        }
        holder.numUpvotes.setText(String.valueOf(posts.get(position).getNumberOfUpvotes()));
        if(posts.get(position).getUpvoted()){
            holder.ib.setImageResource(R.drawable.arrow_upvoted);
        }
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, ExpandedPostActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("post", posts.get(position));
                intent.putExtra("theme",theme);
                mCtx.startActivity(intent);
            }
        });

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

        holder.chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title", posts.get(position).getPostTitle());
                intent.putExtra("theme",theme);
                mCtx.startActivity(intent);
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
        Button commentButton;
        Button chatButton;
        CardView cardView;

        public postsViewHolder(View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.supporting_text);
            title = itemView.findViewById(R.id.primary_text);
            numUpvotes = itemView.findViewById(R.id.num_upvotes);
            ib = itemView.findViewById(R.id.action_button_1);
            commentButton = itemView.findViewById(R.id.action_button_2);
            chatButton = itemView.findViewById(R.id.action_button_3);
            cardView = itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
