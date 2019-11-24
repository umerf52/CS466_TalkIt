package com.apps.talkit.recyclers_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.ChatActivity;
import com.apps.talkit.ExpandedPostActivity;
import com.apps.talkit.HomeActivity;
import com.apps.talkit.R;
import com.apps.talkit.classes.PostInfo;
import com.apps.talkit.classes.UserInfo;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<String> notificationsList = new ArrayList<>();
    private ArrayList<String> chats = new ArrayList<>();
    private RecyclerView.Adapter mAdapterN;
    private RecyclerView.Adapter mAdapterC;
    private UserInfo myUser;
    private int theme;
    ArrayList<PostInfo> notifyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        myUser = (UserInfo) getArguments().getSerializable("username");
        theme = getArguments().getInt("theme");
        final Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(theme==0){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("theme",1);
                    intent.putExtra("name", myUser);
                    getActivity().startActivity(intent);
                }
                else if(theme==1){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("theme",2);
                    intent.putExtra("name", myUser);
                    getActivity().startActivity(intent);
                }
                else if(theme==2){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("theme",3);
                    intent.putExtra("name", myUser);
                    getActivity().startActivity(intent);
                }
                else if(theme==3){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("theme",4);
                    intent.putExtra("name", myUser);
                    getActivity().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("theme",0);
                    intent.putExtra("name", myUser);
                    getActivity().startActivity(intent);
                }

            }
        });
        TextView tv1 = v.findViewById(R.id.nickname);
        String textToDisplay = "Welcome back!";
        if (myUser != null) {
            textToDisplay = "Hi " + myUser.getNickname() + "!";
        }
        tv1.setText(textToDisplay);
        fillTheLists();
        RecyclerView recyclerViewN = v.findViewById(R.id.notifications);
        RecyclerView recyclerViewC = v.findViewById(R.id.chats);
        recyclerViewN.setHasFixedSize(true);
        recyclerViewC.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManagerN = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewN.setLayoutManager(mLayoutManagerN);
        mAdapterN = new RecyclerViewAdapterNotifications(getContext(), notificationsList);
        recyclerViewN.setAdapter(mAdapterN);

        RecyclerView.LayoutManager mLayoutManagerC = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewC.setLayoutManager(mLayoutManagerC);
        mAdapterC = new RecyclerViewAdapterChats(getContext(), chats);
        recyclerViewC.setAdapter(mAdapterC);
        return v;
    }

    public void putArguments(ArrayList<PostInfo> myList){
        for(int i=0;i<myList.size(); i++){
            notifyList.add(myList.get(i));
        }
    }

    private void fillTheLists() {
        notificationsList.add("Your post received up votes");
        notificationsList.add("Aiza commented on your post");
        notificationsList.add("Ahmad commented on your post");

        chats.add("Anon: I know right! Things are not the best");
        chats.add("Anon: Hi! I would like to talk to you");
        chats.add("You: Can I ask you something?");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecyclerViewAdapterNotifications) mAdapterN).setOnItemClickListener(new RecyclerViewAdapterNotifications.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String x = notificationsList.get(position);
                if(position<notifyList.size()){
                    Intent intent = new Intent(getContext(), ExpandedPostActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("post", notifyList.get(position));
                    intent.putExtra("theme",theme);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Loading please wait!", Toast.LENGTH_LONG).show();
                }
            }
        });
        ((RecyclerViewAdapterChats) mAdapterC).setOnItemClickListener(new RecyclerViewAdapterChats.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
//                String x = chats.get(position);
//                Toast.makeText(getContext(), x, Toast.LENGTH_LONG).show();
                String title;
                if(position==0 || position==1){
                    title = "Anon";
                }
                else{
                    title = "Zaid";
                }
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",title);
                intent.putExtra("theme",theme);
                intent.putExtra("history",position+1);
                startActivity(intent);

            }
        });
    }
}
