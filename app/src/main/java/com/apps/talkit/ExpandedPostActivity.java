package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.PostInfo;
import com.apps.talkit.recyclers_fragments.RecyclerViewAdapterComments;

public class ExpandedPostActivity extends AppCompatActivity {

    private TextView title;
    private TextView supportingText;
    private TextView numUpvotes;
    private ImageButton upvoteButton;
    private PostInfo post;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_post);
        Intent i = getIntent();
        post = (PostInfo) i.getSerializableExtra("post");

        title = findViewById(R.id.primary_text);
        supportingText = findViewById(R.id.supporting_text);
        numUpvotes = findViewById(R.id.num_upvotes);
        upvoteButton = findViewById(R.id.action_button_1);

        title.setText(post.getPostTitle());
        supportingText.setText(post.getPostText());
        numUpvotes.setText(String.valueOf(post.getNumberOfUpvotes()));
        if (post.getUpvoted()) {
            upvoteButton.setImageResource(R.drawable.arrow_upvoted);
        } else {
            upvoteButton.setImageResource(R.drawable.arrow_normal);
        }

        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!post.getUpvoted()) {
                    post.setUpvoted(true);
                    post.setNumberOfUpvotes(post.getNumberOfUpvotes() + 1);
                    numUpvotes.setText(String.valueOf(post.getNumberOfUpvotes()));
                    upvoteButton.setImageResource(R.drawable.arrow_upvoted);
                } else {
                    post.setUpvoted(false);
                    post.setNumberOfUpvotes(post.getNumberOfUpvotes() - 1);
                    numUpvotes.setText(String.valueOf(post.getNumberOfUpvotes()));
                    upvoteButton.setImageResource(R.drawable.arrow_normal);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.comments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterComments(this, post.getComments());
        recyclerView.setAdapter(mAdapter);
    }
}
