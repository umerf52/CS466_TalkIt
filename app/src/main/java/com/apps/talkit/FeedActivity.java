package com.apps.talkit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedActivity extends BaseActivity {

    private ArrayList<String> postsList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        setTitle("Feed");
        postsList.add("This is a sample post");
        postsList.add("This is another sample post \nWhat's up?");
        postsList.add("This is 3rd sample post");

        RecyclerView recyclerView = findViewById(R.id.posts);
//        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterPosts(this, postsList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ((RecyclerViewAdapterPosts) mAdapter).setOnItemClickListener(new RecyclerViewAdapterPosts.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String x = postsList.get(position);
                Toast.makeText(getTalkitContext(), x, Toast.LENGTH_LONG).show();
            }
        });
    }
}
