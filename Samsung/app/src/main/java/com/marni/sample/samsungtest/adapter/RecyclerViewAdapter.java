package com.marni.sample.samsungtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marni.sample.samsungtest.R;
import com.marni.sample.samsungtest.model.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_LIST = 51;
    public final static int VIEW_TYPE_IMAGE = 52;
    public static int view;

    private List<Result> mResultsList;
    private Context mContext;
    private final String LOG_TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, List<Result> resultsList) {
        mContext = context;
        this.mResultsList = resultsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (view) {

            case VIEW_TYPE_LIST:
                View mainView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse, null);
                viewHolder = new RecyclerViewHolder(mainView);;
                break;

            case VIEW_TYPE_IMAGE:
                View detailedView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image, null);
                viewHolder = new ImageViewHolder(detailedView);;
                break;
        }

        return viewHolder;

    }


    //add image as we scroll
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int lastRowNumberOnScreen) {

        switch (view) {

            case VIEW_TYPE_LIST:
                 final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
                final Result resultItem = mResultsList.get(lastRowNumberOnScreen);
                Picasso.with(mContext).load(resultItem.getThumbnailUrl()).fetch(new Callback(){
                @Override
                public void onSuccess() {
                    recyclerViewHolder.thumbnail.setAlpha(0f);
                    Picasso.with(mContext).load(resultItem.getThumbnailUrl()).into(recyclerViewHolder.thumbnail);
                    recyclerViewHolder.thumbnail.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError() {

                }
            });
                break;

            case VIEW_TYPE_IMAGE:
                final ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
                final Result resultItem1 = mResultsList.get(lastRowNumberOnScreen);
                Picasso.with(mContext).load(resultItem1.getUrl()).fetch(new Callback(){
                    @Override
                    public void onSuccess() {
                        imageViewHolder.photo_image.setAlpha(0f);
                        Picasso.with(mContext).load(resultItem1.getUrl()).into(imageViewHolder.photo_image);
                        imageViewHolder.photo_image.animate().setDuration(300).alpha(1f).start();
                    }

                    @Override
                    public void onError() {

                    }
                });;
                break;
        }

    }

    @Override
    public int getItemCount() {
        return (null != mResultsList ? mResultsList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {

        return position % 2;

    }

    public void loadNewData(List<Result> newResults) {
        mResultsList = newResults;
        notifyDataSetChanged();
    }

    public Result getResult(int position) {
        return (null != mResultsList ? mResultsList.get(position) : null);
    }


}

