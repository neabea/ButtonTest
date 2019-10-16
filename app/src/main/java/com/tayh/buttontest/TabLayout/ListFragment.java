package com.tayh.buttontest.TabLayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tayh.buttontest.R;

/**
 * @author LZY
 * @time 2019/3/15
 */
public class ListFragment extends Fragment {
    private RecyclerView mRecycleView;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.list_fragment, container, false);
        mRecycleView = linearLayout.findViewById(R.id.recycle_view);
        return linearLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mRecycleView.getContext()));
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getActivity());
        mRecycleView.setAdapter(recycleViewAdapter);
    }
}
