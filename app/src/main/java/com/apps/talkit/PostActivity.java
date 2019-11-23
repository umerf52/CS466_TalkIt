package com.apps.talkit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PostActivity extends BaseActivity {

    ToggleButton toggleButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        EditText f= findViewById(R.id.postContent2);
//        LinearLayout lLayout = findViewById(R.id.tagscontainer); // Root ViewGroup in which you want to add textviews
//        for (int i = 0; i < 5; i++) {
//            TextView tv = new TextView(this); // Prepare textview object programmatically
//            tv.setText("Dynamic TextView" + i);
//            tv.setId(i + 5);
//            lLayout.addView(tv); // Add to your ViewGroup using this method
//        }

    }
    public void onShareButton(View v){
        Intent myIntent = new Intent(this, HomeActivity.class);
        startActivity(myIntent);
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
