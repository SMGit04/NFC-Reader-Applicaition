package com.example.nfcapplication.Remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;


    // https://banksimulator.azurewebsites.net/api/Notification/notification
    public static Retrofit getRetrofit() {

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://banksimulator.azurewebsites.net/api/")
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;

        // TODO: Configure baseURL to work with a real device
        // Use wifi access point: use azure cloud from UJ
    }
}
