package com.apps.talkit.recyclers_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;
import com.apps.talkit.classes.PostInfo;

import java.util.ArrayList;

class RecyclerViewAdapterTherapy extends RecyclerView.Adapter<RecyclerViewAdapterTherapy.therapyViewHolder> {
    private static RecyclerViewAdapterTherapy.MyClickListener myClickListener;
    private Context mCtx;
    private ArrayList<Integer> therapyList;

    public RecyclerViewAdapterTherapy(Context context, ArrayList<Integer> items) {
        mCtx = context;
        therapyList = items;
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
        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "Clicked: "+position, Toast.LENGTH_SHORT).show();
            }
        });
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
