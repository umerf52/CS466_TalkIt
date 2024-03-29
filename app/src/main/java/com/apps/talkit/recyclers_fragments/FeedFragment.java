package com.apps.talkit.recyclers_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.ExpandedPostActivity;
import com.apps.talkit.R;
import com.apps.talkit.classes.PostInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FeedFragment extends Fragment {
    private ArrayList<PostInfo> postsList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, null);
        final int theme = getArguments().getInt("theme");
        RecyclerView recyclerView = v.findViewById(R.id.posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterPosts(getContext(), postsList,theme);
        recyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterPosts) mAdapter).setOnItemClickListener(new RecyclerViewAdapterPosts.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), ExpandedPostActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("post", postsList.get(position));
                intent.putExtra("theme",theme);
                startActivity(intent);
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);

        // Set up the input
        final ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.isShown();
        builder.setView(progressBar);

        final AlertDialog alertDialog = builder.show();

        FirebaseApp.initializeApp((getContext()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        alertDialog.dismiss();
                        if (task.isSuccessful()) {
                            postsList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PostInfo p = document.toObject(PostInfo.class);
                                postsList.add(p);
                            }
                            postsList.add(new PostInfo("", "You are all caught up :)", false, false));
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return v;
    }
}
