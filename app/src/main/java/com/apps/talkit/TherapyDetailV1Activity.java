package com.apps.talkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class TherapyDetailV1Activity extends AppCompatActivity {

    private ArrayList<Integer> pictures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_therapy_detail_v1);
        pictures = getIntent().getIntegerArrayListExtra("images");

        RecyclerView recyclerView = findViewById(R.id.therapy_detail);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerViewAdapterTherapyDetail(this, pictures);
        recyclerView.setAdapter(mAdapter);
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

class RecyclerViewAdapterTherapyDetail extends RecyclerView.Adapter<RecyclerViewAdapterTherapyDetail.therapyViewHolder> {
    private static RecyclerViewAdapterTherapyDetail.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<Integer> therapyList;

    public RecyclerViewAdapterTherapyDetail(Context context, ArrayList<Integer> items) {
        mCtx = context;
        therapyList = items;
    }

    @Override
    public int getItemCount() {
        return therapyList.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterTherapyDetail.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public therapyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.therapy_detail_list1, parent, false);
        return new therapyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull therapyViewHolder holder, final int position) {
        holder.myImage.setImageResource(therapyList.get(position));
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class therapyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImage;
        public therapyViewHolder(View itemView) {
            super(itemView);
            myImage = itemView.findViewById(R.id.picture);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
