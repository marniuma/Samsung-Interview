package com.marni.sample.samsungtest.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ResultModel {

    private static ResultModel sResultModel;
    private static List<Result> mResults;

    public static ResultModel get(Context context)
    {
        if(sResultModel == null)
        {
            sResultModel = new ResultModel(context);
        }
        return  sResultModel;
    }

    private ResultModel(Context context)
    {
        mResults = new ArrayList<>();
    }

    public void setResults(List<Result> mResults) {
        this.mResults = mResults;
        Log.d("****", mResults.toString());
    }

    public List<Result> getResults() {
        Log.d("***", mResults.toString());
        return mResults;
    }
}
