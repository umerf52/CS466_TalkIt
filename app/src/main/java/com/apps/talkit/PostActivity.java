package com.apps.talkit;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
    private TextView textviewTitle;
    private View viewActionBar;
    private ActionBar abar;
    private ActionBar.LayoutParams params;
    private Drawable upArrow;
    private int colorPrimary;
    private int colorSecondary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colorPrimary = getResources().getColor(R.color.colorPrimary1);
        colorSecondary = getResources().getColor(R.color.colorSecondary1);
        Intent i = getIntent();
        int theme = i.getIntExtra("theme",0);
        if(theme==1){
            setTheme(R.style.AppThemeTwo);
            colorPrimary = getResources().getColor(R.color.colorPrimary2);
            colorSecondary = getResources().getColor(R.color.colorSecondary2);
        }
        else if(theme==2){
            setTheme(R.style.AppThemeThree);
            colorPrimary = getResources().getColor(R.color.colorPrimary3);
            colorSecondary = getResources().getColor(R.color.colorSecondary3);

        }
        setContentView(R.layout.activity_post);
        upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(colorSecondary, PorterDuff.Mode.SRC_ATOP);
        abar = getSupportActionBar();
        viewActionBar = getLayoutInflater().inflate(R.layout.title_layout, null);
        params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setTextColor(colorSecondary);
        textviewTitle.setText("Post");
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setHomeAsUpIndicator(upArrow);
        abar.setHomeButtonEnabled(true);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
