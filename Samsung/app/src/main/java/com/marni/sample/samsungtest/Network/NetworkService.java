package com.marni.sample.samsungtest.Network;

import android.util.Log;

import com.marni.sample.samsungtest.ApplicationContextProvider;
import com.marni.sample.samsungtest.model.Result;
import com.marni.sample.samsungtest.model.ResultModel;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static String baseUrl = "https://jsonplaceholder.typicode.com";

    private NetworkInterface networkInterface;

    public NetworkService() {
        network(baseUrl);
    }

    public NetworkService(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
        network(baseUrl);
    }

    private void network(String baseUrl) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(ApplicationContextProvider.getContext().getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<List<Result>> call = request.getJSON();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                ResultModel resultModel = ResultModel.get(ApplicationContextProvider.getContext());
                resultModel.setResults(response.body());
                networkInterface.onNetworkSuccess();
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
