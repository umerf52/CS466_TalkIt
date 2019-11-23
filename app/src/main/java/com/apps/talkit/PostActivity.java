package com.apps.talkit;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class PostActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int theme = getIntent().getIntExtra("theme",0);
        if(theme==1){
            setTheme(R.style.AppThemeTwo);
        }
        else if(theme==2){
            setTheme(R.style.AppThemeThree);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText f= findViewById(R.id.Shareyourthoughts);

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
