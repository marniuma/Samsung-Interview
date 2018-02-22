package com.marni.sample.samsungtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.marni.sample.samsungtest.R;


public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;

    public RecyclerViewHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
    }
}
