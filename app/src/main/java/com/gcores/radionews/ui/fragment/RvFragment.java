package com.gcores.radionews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.view.base.adapter.RvListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RvFragment extends AppFragment {

    private RecyclerView mRvList;
    private RvListAdapter rvListAdapter;

    private List<String> arr = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_rv,container,false);
        poluteRvData();
        rvListAdapter = new RvListAdapter(arr);
        mRvList =  root.findViewById(R.id.frag_list);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(rvListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRvList.setLayoutManager(llm);
        return root;
    }

    //填充数据
    private void poluteRvData() {
        arr.clear();
        arr.add("test one");
        arr.add("test two");
        arr.add("test three");
        arr.add("test four");
        arr.add("test five");
    }


}
