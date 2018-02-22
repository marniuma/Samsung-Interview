package com.marni.sample.samsungtest.Network;

import com.marni.sample.samsungtest.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("/photos")
    Call<List<Result>> getJSON();
}
