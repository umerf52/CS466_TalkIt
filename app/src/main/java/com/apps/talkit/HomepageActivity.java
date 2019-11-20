package com.apps.talkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.UserInfo;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class HomepageActivity extends BaseActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private UserInfo myUser;
    private ArrayList<String> notificationsList = new ArrayList<>();
    private ArrayList<String> chats = new ArrayList<>();
    private RecyclerView.Adapter mAdapterN;
    private RecyclerView.Adapter mAdapterC;
    private SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setTitle("My Activity");
        myUser = (UserInfo) getIntent().getSerializableExtra("name");
        TextView tv1 = findViewById(R.id.nickname);
        String textToDisplay = "Hi " + myUser.getNickname()+"!";
        tv1.setText(textToDisplay);
        fillTheLists();
        RecyclerView recyclerViewN = findViewById(R.id.notifications);
        RecyclerView recyclerViewC = findViewById(R.id.chats);
        recyclerViewN.setHasFixedSize(true);
        recyclerViewC.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerN = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewN.setLayoutManager(mLayoutManagerN);
        mAdapterN = new RecyclerViewAdapterNotifications(this, notificationsList);
        recyclerViewN.setAdapter(mAdapterN);

        RecyclerView.LayoutManager mLayoutManagerC = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewC.setLayoutManager(mLayoutManagerC);
        mAdapterC = new RecyclerViewAdapterChats(this, chats);
        recyclerViewC.setAdapter(mAdapterC);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Feed",R.drawable.feed));
        spaceNavigationView.addSpaceItem(new SpaceItem("Therapy", R.drawable.therapy));
        spaceNavigationView.setCentreButtonIcon(R.drawable.feed);
        spaceNavigationView.changeCurrentItem(-1);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getTalkitContext(),"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                if(itemIndex==0){
//                    Intent myIntent = new Intent(HomepageActivity.this, FeedActivity.class);
//                    HomepageActivity.this.startActivity(myIntent);
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getTalkitContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillTheLists() {
        notificationsList.add("Sam commented on your post");
        notificationsList.add("Your post received up votes");
        notificationsList.add("John commented on your post");

        chats.add("Anon: I know right! Things are not the best");
        chats.add("Anon: Hi! I would like to talk to you");
        chats.add("John: Nice talking to you");
    }


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else
            Toast.makeText(getTalkitContext(), "Press back again in order to exit", Toast.LENGTH_SHORT).show();

        mBackPressed = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        spaceNavigationView.changeCurrentItem(-1);
        super.onResume();
        ((RecyclerViewAdapterNotifications) mAdapterN).setOnItemClickListener(new RecyclerViewAdapterNotifications.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String x = notificationsList.get(position);
                Toast.makeText(getTalkitContext(), x, Toast.LENGTH_LONG).show();
//                Intent myIntent = new Intent(RateTutorActivity.this, RateDetailActivity.class);
//                myIntent.putExtra("result", x);

//                RateTutorActivity.this.startActivity(myIntent);
            }
        });
        ((RecyclerViewAdapterChats) mAdapterC).setOnItemClickListener(new RecyclerViewAdapterChats.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String x = chats.get(position);
                Toast.makeText(getTalkitContext(), x, Toast.LENGTH_LONG).show();
//                Intent myIntent = new Intent(RateTutorActivity.this, RateDetailActivity.class);
//                myIntent.putExtra("result", x);

//                RateTutorActivity.this.startActivity(myIntent);
            }
        });
    }
}
