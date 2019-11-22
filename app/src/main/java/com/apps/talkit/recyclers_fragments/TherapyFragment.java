package com.apps.talkit.recyclers_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.R;

import java.util.ArrayList;

public class TherapyFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Integer> therapyList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_therapy,null);
        therapyList = getArguments().getIntegerArrayList("therapy");
        RecyclerView recyclerView = v.findViewById(R.id.therapy);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterTherapy(getContext(),therapyList);
        recyclerView.setAdapter(mAdapter);
        return v;
    }

}
