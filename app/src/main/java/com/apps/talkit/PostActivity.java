package com.apps.talkit;
import android.os.Bundle;
import android.widget.EditText;

public class PostActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        EditText f= findViewById(R.id.Shareyourthoughts);
//        f.setText(
//                getIntent().getStringExtra(
//                        "test"
//                )
//        );

    }
}
