package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.talkit.classes.UserInfo;

public class StartupActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.hide();
        }
        setContentView(R.layout.activity_startup);

        Button continue_button = findViewById(R.id.continueButton);
        final EditText nickname = findViewById(R.id.nickname);


        continue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = nickname.getText().toString();
                if (!name.isEmpty()){
                    UserInfo myUser = new UserInfo(nickname.getText().toString());
                    Intent myIntent = new Intent(StartupActivity.this, HomeActivity.class);
                    myIntent.putExtra("name", myUser);
                    StartupActivity.this.startActivity(myIntent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Please enter a name to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else
            Toast.makeText(getBaseContext(), "Press back again in order to exit", Toast.LENGTH_SHORT).show();

        mBackPressed = System.currentTimeMillis();
    }

}
