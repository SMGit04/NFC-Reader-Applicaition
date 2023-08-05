package com.example.nfcapplication.Remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;


    // https://localhost:7126/api/ApproveOrDeclineTransaction
    // http://10.0.2.2:5174/api/
    // http://192.168.88.249:5174/api/
    // http://192.168.88.249:5174/api/
    public static Retrofit getRetrofit() {

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5174/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;

        // TODO: Configure baseURL to work with a real device
    }
}
