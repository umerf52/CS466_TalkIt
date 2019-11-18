package com.apps.talkit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends BaseActivity {

    private final String TAG = "FeedActivity.java";

    private ArrayList<String> postsList = new ArrayList<>();
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

        FirebaseApp.initializeApp((this));
        db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> temp = document.getData();
                                for (Map.Entry<String, Object> me : temp.entrySet()) {
//                                    Log.d(TAG, document.getId() + " => " + me.getValue().toString());
                                    postsList.add(me.getValue().toString());
                                }
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
                String x = postsList.get(position);
                Toast.makeText(getTalkitContext(), x, Toast.LENGTH_LONG).show();
            }
        });
    }
}
