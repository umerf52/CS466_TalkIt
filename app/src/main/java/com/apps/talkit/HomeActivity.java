package com.apps.talkit;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.apps.talkit.classes.PostInfo;
import com.apps.talkit.classes.UserInfo;
import com.apps.talkit.recyclers_fragments.FeedFragment;
import com.apps.talkit.recyclers_fragments.HomeFragment;
import com.apps.talkit.recyclers_fragments.TherapyFragment;
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
    private ArrayList<PostInfo> notifyList = new ArrayList<>();
    private ArrayList<Integer> myTherapy = new ArrayList<>();
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
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
        final int theme = getIntent().getIntExtra("theme",0);
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
        setPics();
        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("name");
        setContentView(R.layout.activity_home);

        abar = getSupportActionBar();
        viewActionBar = getLayoutInflater().inflate(R.layout.title_layout, null);
        params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setTextColor(colorSecondary);
        textviewTitle.setText("Home");
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(colorSecondary, PorterDuff.Mode.SRC_ATOP);
        Bundle bundle = new Bundle();
        bundle.putSerializable("username", userInfo);
        bundle.putInt("theme",theme);
        final HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        loadFragment(homeFragment);
        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Feed",R.drawable.feed));
        spaceNavigationView.addSpaceItem(new SpaceItem("Therapy", R.drawable.therapy));
        spaceNavigationView.changeCurrentItem(-1);
        spaceNavigationView.setSpaceBackgroundColor(colorPrimary);
        spaceNavigationView.setCentreButtonColor(colorSecondary);
        spaceNavigationView.setInActiveSpaceItemColor(colorSecondary);
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent myIntent = new Intent(HomeActivity.this, PostActivity.class);
                myIntent.putExtra("theme",theme);
                HomeActivity.this.startActivity(myIntent);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Fragment fragment;
                if(itemIndex==0){
                    textviewTitle.setText("Feed");
                    abar.setCustomView(viewActionBar, params);
                    abar.setDisplayShowCustomEnabled(true);
                    abar.setDisplayShowTitleEnabled(false);
                    abar.setDisplayHomeAsUpEnabled(true);
                    abar.setHomeAsUpIndicator(upArrow);
                    abar.setHomeButtonEnabled(true);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("posts", postsList);
                    bundle.putInt("theme",theme);
                    fragment = new FeedFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }
                if(itemIndex==1){
                    textviewTitle.setText("Therapy");
                    abar.setCustomView(viewActionBar, params);
                    abar.setDisplayShowCustomEnabled(true);
                    abar.setDisplayShowTitleEnabled(false);
                    abar.setHomeAsUpIndicator(upArrow);
                    abar.setDisplayHomeAsUpEnabled(true);
                    abar.setHomeButtonEnabled(true);
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("therapy",myTherapy);
                    bundle.putInt("theme",theme);
                    fragment = new TherapyFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }
            }


            @Override
            public void onItemReselected(int itemIndex, String itemName) {
//                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
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
//                                Log.e(TAG, p.getCommentsKeys().toString());
                            }
                            postsList.add(new PostInfo(0, "", "You are all caught up :)", false, false));
                            for(int i=0; i<postsList.size(); i++){
                                String title = postsList.get(i).getPostTitle();
                                if(title.equals("New Friends Needed") || title.equals("Bestfriend") || title.equals("Dire Situation")){
                                    notifyList.add(postsList.get(i));
                                }
                            }
                            homeFragment.putArguments(notifyList);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    private void setPics(){
        myTherapy.add(R.drawable.quote);
        myTherapy.add(R.drawable.lifestyle);
        myTherapy.add(R.drawable.meditation);
        myTherapy.add(R.drawable.exercise);
        myTherapy.add(R.drawable.tvserials);
        myTherapy.add(R.drawable.movies);
        myTherapy.add(R.drawable.music);
        myTherapy.add(R.drawable.memes);
        myTherapy.add(R.drawable.relationship);
        myTherapy.add(R.drawable.spiritual);
        myTherapy.add(R.drawable.professional);
        myTherapy.add(R.drawable.end);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Bundle bundle = new Bundle();
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                loadFragment(homeFragment);
                textviewTitle.setText("Home");
                abar.setCustomView(viewActionBar, params);
                abar.setDisplayShowCustomEnabled(true);
                abar.setDisplayShowTitleEnabled(false);
                spaceNavigationView.changeCurrentItem(-1);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            default:
                return super.onOptionsItemSelected(item);
        }
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
