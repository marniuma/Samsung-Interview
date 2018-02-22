package com.marni.sample.samsungtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.marni.sample.samsungtest.adapter.RecyclerViewAdapter;
import com.marni.sample.samsungtest.controller.DetailedController;
import com.marni.sample.samsungtest.controller.DetailedControllerImpl;
import com.marni.sample.samsungtest.controller.FirstControllerImpl;
import com.marni.sample.samsungtest.model.Result;
import com.marni.sample.samsungtest.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailedController {

    private RecyclerViewPager mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final Result result = (Result) intent.getParcelableExtra("RESULT_TRANSFER");
        pos = intent.getIntExtra("POSITION" , 0);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerViewAdapter.view = RecyclerViewAdapter.VIEW_TYPE_IMAGE;
        new DetailedControllerImpl(this);

    }

    private void init() {
        RecyclerViewAdapter.view = RecyclerViewAdapter.VIEW_TYPE_IMAGE;
        mRecyclerView = (RecyclerViewPager) findViewById(R.id.pager);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(DetailActivity.this, new ArrayList<Result>());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onUIUpdate() {
        mRecyclerViewAdapter.loadNewData(ResultModel.get(ApplicationContextProvider.getContext()).getResults());
        mRecyclerView.getLayoutManager().scrollToPosition(pos);
    }
}
