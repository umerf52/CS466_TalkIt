package com.apps.talkit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends BaseActivity {

    final private String TAG = "PostActivity.java";
    private EditText postText;
    private EditText postTitle;
    private CheckBox chatCheck;
    private CheckBox triggerCheck;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postText = findViewById(R.id.postContent2);
        postTitle = findViewById(R.id.title);
        chatCheck = findViewById(R.id.chatCheck);
        triggerCheck = findViewById(R.id.triggerCheck);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
    }

    public void onShareButton(View v){
//        Intent myIntent = new Intent(this, HomeActivity.class);
//        startActivity(myIntent);
        boolean isChat = false;
        boolean isTrigger = false;
        String textString = postText.getText().toString().trim();
        String titleString = postTitle.getText().toString().trim();
        Map<String, String> comments = Collections.emptyMap();
        int numUpvotes = 0;
        boolean upvoted = false;

        if (chatCheck.isChecked()) isChat = true;
        if (triggerCheck.isChecked()) isTrigger = true;
        Map<String, Object> post = new HashMap<>();
        post.put("chatEnabled", isChat);
        post.put("comments", comments);
        post.put("isTrigger", isTrigger);
        post.put("numberOfUpvotes", numUpvotes);
        post.put("postText", textString);
        post.put("postTitle", titleString);
        post.put("upvoted", upvoted);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        // Set up the input
        final ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.isShown();
        builder.setView(progressBar);

        final AlertDialog alertDialog = builder.show();
        // Add a new document with a generated ID
        db.collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        alertDialog.dismiss();
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        alertDialog.dismiss();
                        Toast.makeText(getTalkitContext(), "Error creating post", Toast.LENGTH_LONG).show();
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
    public void onAddTag(View v){
        LinearLayout lLayout = findViewById(R.id.tagscontainer);
        EditText t_name = findViewById(R.id.tagstext);
        TextView tv = new TextView(this);
        tv.setText("  "+ t_name.getText());
//        tv.setId("1");
        t_name.setText("");
        lLayout.addView(tv);
    }


}
