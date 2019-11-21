package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.apps.talkit.classes.PostInfo;
import com.apps.talkit.classes.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private final String TAG = "HomeActivity.java";
    private SpaceNavigationView spaceNavigationView;
    private ArrayList<PostInfo> postsList = new ArrayList<>();
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private FirebaseFirestore db;

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
        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("name");
        setContentView(R.layout.activity_home);
        setTitle("My Activity");

        Bundle bundle = new Bundle();
        bundle.putSerializable("username", userInfo);
        bundle.putInt("theme",theme);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        loadFragment(homeFragment);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Feed",R.drawable.feed));
        spaceNavigationView.addSpaceItem(new SpaceItem("Therapy", R.drawable.therapy));
        spaceNavigationView.changeCurrentItem(-1);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getTalkitContext(),"New Post", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Fragment fragment = null;
                if(itemIndex==0){
                    setTitle("Feed");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("posts", postsList);
                    fragment = new FeedFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }
                if(itemIndex==1){
                    Toast.makeText(getTalkitContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseApp.initializeApp((this));
        db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            postsList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PostInfo p = document.toObject(PostInfo.class);
                                postsList.add(p);
                            }
                            postsList.add(new PostInfo("", 0, "", "You are all catched up :)"));
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_feed, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        } else
            Toast.makeText(getTalkitContext(), "Press back again in order to exit", Toast.LENGTH_SHORT).show();

        mBackPressed = System.currentTimeMillis();
    }
}
