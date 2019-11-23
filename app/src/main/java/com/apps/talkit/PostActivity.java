package com.apps.talkit;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ToggleButton;

public class PostActivity extends BaseActivity {

    ToggleButton toggleButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        EditText f= findViewById(R.id.postContent2);

        toggleButton1 = (ToggleButton) findViewById(R.id.t1);

    }
}
