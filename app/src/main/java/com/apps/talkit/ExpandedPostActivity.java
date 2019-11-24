package com.apps.talkit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.PostInfo;
import com.apps.talkit.recyclers_fragments.RecyclerViewAdapterComments;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Map;

public class ExpandedPostActivity extends BaseActivity {

    private TextView numUpvotes;
    private ImageButton upvoteButton;
    private PostInfo post;
    private Button chatButton;
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
        post = (PostInfo) i.getSerializableExtra("post");
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
        else if(theme==3){
            setTheme(R.style.AppThemeFour);
            colorPrimary = getResources().getColor(R.color.colorPrimary4);
            colorSecondary = getResources().getColor(R.color.colorSecondary4);
        }
        else if(theme==4){
            setTheme(R.style.AppThemeFive);
            colorPrimary = getResources().getColor(R.color.colorPrimary5);
            colorSecondary = getResources().getColor(R.color.colorSecondary5);
        }
        setContentView(R.layout.activity_expanded_post);
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

        TextView title = findViewById(R.id.primary_text);
        TextView supportingText = findViewById(R.id.supporting_text);
        numUpvotes = findViewById(R.id.num_upvotes);
        upvoteButton = findViewById(R.id.action_button_1);
        chatButton = findViewById(R.id.action_button_3);

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
        RecyclerView.Adapter mAdapter = new RecyclerViewAdapterComments(this, post.getComments());
        recyclerView.setAdapter(mAdapter);

        if (!post.getChatEnabled()) {
            chatButton.setVisibility(View.INVISIBLE);
        }

        final EditText comment = findViewById(R.id.edittext_chatbox);
        Button button = findViewById(R.id.button_chatbox_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String toPost = comment.getText().toString();
                if(!toPost.isEmpty()){
                    Map<String, String> temp = Collections.emptyMap();
                    if (post.getComments() != null) {
                        temp = post.getComments();
                    }
                    temp.put("You", toPost.trim());
                    post.setComments(temp);
                    comment.getText().clear();
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

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
