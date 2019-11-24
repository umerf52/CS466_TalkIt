package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;
import com.apps.talkit.TherapyDetailV1Activity;
import com.apps.talkit.TherapyDetailV2Activity;

import java.util.ArrayList;

class RecyclerViewAdapterTherapy extends RecyclerView.Adapter<RecyclerViewAdapterTherapy.therapyViewHolder> {
    private static RecyclerViewAdapterTherapy.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<Integer> therapyList;
    private Integer theme;

    public RecyclerViewAdapterTherapy(Context context, ArrayList<Integer> items, Integer t) {
        mCtx = context;
        therapyList = items;
        theme = t;
    }

    @Override
    public int getItemCount() {
        return therapyList.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterTherapy.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public therapyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.therapy_list, parent, false);
        return new therapyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull therapyViewHolder holder, final int position) {
        holder.myImage.setImageResource(therapyList.get(position));
        if(position==0){
            holder.textView.setText("Quotes");
        }
        else if(position==1){
            holder.textView.setText("Lifestyle");
        }
        else if(position==2){
            holder.textView.setText("Meditation");
        }
        else if(position==3){
            holder.textView.setText("Exercise");
        }
        else if(position==4){
            holder.textView.setText("TV Serials");
        }
        else if(position==5){
            holder.textView.setText("Movies");
        }
        else if(position==6){
            holder.textView.setText("Music");
        }
        else if(position==7){
            holder.textView.setText("Memes");
        }
        else if(position==8){
            holder.textView.setText("Relationship");
        }
        else if(position==9){
            holder.textView.setText("Spiritual");
        }
        else if(position==10){
            holder.textView.setText("Professional Help");
        }
        else if(position==11){
            holder.textView.setText(" ");
        }

        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                    ArrayList<Integer> myList = new ArrayList<>();
                    myList.add(R.drawable.quote1);
                    myList.add(R.drawable.quote2);
                    myList.add(R.drawable.quote3);
                    myList.add(R.drawable.quote4);
                    Intent intent = new Intent(mCtx, TherapyDetailV1Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putExtra("theme",theme);
                    mCtx.startActivity(intent);
                }
                else if(position==1){
                    ArrayList<Integer> myList = new ArrayList<>();
                    ArrayList<String> tips = new ArrayList<>();
                    myList.add(R.drawable.life1);
                    myList.add(R.drawable.life2);
                    myList.add(R.drawable.life3);
                    myList.add(R.drawable.life4);
                    myList.add(R.drawable.life5);
                    tips.add("Fill your plate with fresh, whole foods and drink plenty of water");
                    tips.add("Cut the sweetened beverages");
                    tips.add("Try going decaf");
                    tips.add("Beat procrastination");
                    tips.add("Pursue a hobby");
                    Intent intent = new Intent(mCtx, TherapyDetailV2Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putStringArrayListExtra("tips",tips);
                    intent.putExtra("theme",theme);
                    intent.putExtra("title","Lifestyle");
                    mCtx.startActivity(intent);
                }
                else if(position==2){
                    ArrayList<Integer> myList = new ArrayList<>();
                    ArrayList<String> tips = new ArrayList<>();
                    myList.add(R.drawable.med1);
                    myList.add(R.drawable.med2);
                    myList.add(R.drawable.med3);
                    tips.add("Mindfulness Meditation");
                    tips.add("Body Scan Meditation");
                    tips.add("Loving Kindness Meditation");
                    Intent intent = new Intent(mCtx, TherapyDetailV2Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putStringArrayListExtra("tips",tips);
                    intent.putExtra("theme",theme);
                    intent.putExtra("title","Meditation");
                    mCtx.startActivity(intent);
                }
                else if(position==3){
                    ArrayList<Integer> myList = new ArrayList<>();
                    ArrayList<String> tips = new ArrayList<>();
                    myList.add(R.drawable.exe1);
                    myList.add(R.drawable.exe2);
                    myList.add(R.drawable.exe3);
                    myList.add(R.drawable.exe4);
                    tips.add("Set Off That Runner's High");
                    tips.add("Build Your Muscles");
                    tips.add("Yoga");
                    tips.add("Tai Chi");
                    Intent intent = new Intent(mCtx, TherapyDetailV2Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putStringArrayListExtra("tips",tips);
                    intent.putExtra("theme",theme);
                    intent.putExtra("title","Exercise");
                    mCtx.startActivity(intent);
                }
                else if(position==4){
                    ArrayList<Integer> myList = new ArrayList<>();
                    ArrayList<String> tips = new ArrayList<>();
                    myList.add(R.drawable.tv1);
                    myList.add(R.drawable.tv2);
                    myList.add(R.drawable.tv3);
                    tips.add("The Office");
                    tips.add("Friends");
                    tips.add("Seinfeld");
                    Intent intent = new Intent(mCtx, TherapyDetailV2Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putStringArrayListExtra("tips",tips);
                    intent.putExtra("theme",theme);
                    intent.putExtra("title","TV Series");
                    mCtx.startActivity(intent);
                }
                else if(position==5){
                    ArrayList<Integer> myList = new ArrayList<>();
                    ArrayList<String> tips = new ArrayList<>();
                    myList.add(R.drawable.mov1);
                    myList.add(R.drawable.mov2);
                    myList.add(R.drawable.mov3);
                    tips.add("Shrek");
                    tips.add("The 21 Jump Street");
                    tips.add("Hera Pheri");
                    Intent intent = new Intent(mCtx, TherapyDetailV2Activity.class);
                    intent.putIntegerArrayListExtra("images",myList);
                    intent.putStringArrayListExtra("tips",tips);
                    intent.putExtra("theme",theme);
                    intent.putExtra("title","Movies");
                    mCtx.startActivity(intent);
                }
                else{
//                    Toast.makeText(mCtx, "Clicked: "+position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class therapyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImage;
        TextView textView;
        public therapyViewHolder(View itemView) {
            super(itemView);
            myImage = itemView.findViewById(R.id.picture);
            textView = itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
