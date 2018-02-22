package com.marni.sample.samsungtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marni.sample.samsungtest.adapter.RecyclerViewAdapter;
import com.marni.sample.samsungtest.controller.FirstController;
import com.marni.sample.samsungtest.controller.FirstControllerImpl;
import com.marni.sample.samsungtest.model.Result;
import com.marni.sample.samsungtest.model.ResultModel;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements FirstController {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

    private void init() {
        RecyclerViewAdapter.view = RecyclerViewAdapter.VIEW_TYPE_LIST;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerViewAdapter = new RecyclerViewAdapter(FirstActivity.this,
                new ArrayList<Result>());

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(FirstActivity.this, DetailActivity.class);
                        intent.putExtra("RESULT_TRANSFER", mRecyclerViewAdapter.getResult(position));
                        intent.putExtra("POSITION", position);
                        startActivity(intent);
                    }
                    @Override
                    public void onItemLongClick(View view, int position) {
                        //Toast.makeText(MainActivity.this, "Long Tap", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(FirstActivity.this, DetailActivity.class);
                        intent.putExtra("RESULT_TRANSFER", mRecyclerViewAdapter.getResult(position));
                        startActivity(intent);
                    }
                }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        new FirstControllerImpl(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onUIUpdate() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerViewAdapter.loadNewData(ResultModel.get(ApplicationContextProvider.getContext()).getResults());
    }
}
