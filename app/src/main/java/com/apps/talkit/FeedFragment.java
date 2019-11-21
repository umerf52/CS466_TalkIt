package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.PostInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FeedFragment extends Fragment {
    private ArrayList<PostInfo> postsList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, null);
        postsList = (ArrayList<PostInfo>) getArguments().getSerializable("posts");
        RecyclerView recyclerView = v.findViewById(R.id.posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterPosts(getContext(), postsList);
        recyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterPosts) mAdapter).setOnItemClickListener(new RecyclerViewAdapterPosts.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), ExpandedPostActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("post", postsList.get(position));
                startActivity(intent);
            }
        });
//        FirebaseApp.initializeApp((getContext()));
//        db = FirebaseFirestore.getInstance();
//        db.collection("posts")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            postsList.clear();
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                PostInfo p = document.toObject(PostInfo.class);
//                                postsList.add(p);
//                            }
//                            mAdapter.notifyDataSetChanged();
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });
        return v;
    }

}
