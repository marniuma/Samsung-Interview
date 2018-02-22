package com.marni.sample.samsungtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.marni.sample.samsungtest.R;


public class ImageViewHolder extends RecyclerView.ViewHolder {
    protected ImageView photo_image;

    public ImageViewHolder(View view) {
        super(view);
        this.photo_image = (ImageView) view.findViewById(R.id.photo_image);
    }
}
