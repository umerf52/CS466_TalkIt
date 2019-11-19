package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.PostInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class FeedActivity extends BaseActivity {

    private final String TAG = "FeedActivity.java";

    private ArrayList<PostInfo> postsList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        setTitle("Feed");

        RecyclerView recyclerView = findViewById(R.id.posts);
//        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterPosts(this, postsList);
        recyclerView.setAdapter(mAdapter);

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Feed",R.drawable.feed));
        spaceNavigationView.addSpaceItem(new SpaceItem("Therapy", R.drawable.therapy));
        spaceNavigationView.changeCurrentItem(0);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getTalkitContext(),"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseApp.initializeApp((this));
        db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PostInfo p = document.toObject(PostInfo.class);
                                postsList.add(p);
                            }
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ((RecyclerViewAdapterPosts) mAdapter).setOnItemClickListener(new RecyclerViewAdapterPosts.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String x = postsList.get(position).getPostText();
                Toast.makeText(getTalkitContext(), x, Toast.LENGTH_LONG).show();
            }
        });
    }

}
