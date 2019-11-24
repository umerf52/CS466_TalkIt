package com.apps.talkit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private TextView textviewTitle;
    private View viewActionBar;
    private ActionBar abar;
    private ActionBar.LayoutParams params;
    private Drawable upArrow;
    private int colorPrimary;
    private int colorSecondary;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colorPrimary = getResources().getColor(R.color.colorPrimary1);
        colorSecondary = getResources().getColor(R.color.colorSecondary1);
        Intent i = getIntent();
        title = i.getStringExtra("title");
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
        setContentView(R.layout.activity_chat);
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
        textviewTitle.setText(title);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setHomeAsUpIndicator(upArrow);
        abar.setHomeButtonEnabled(true);
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
