package com.example.nfcapplication.Interfaces;

import com.example.nfcapplication.Models.DataModel;
import com.example.nfcapplication.Models.TransactionDetailsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRetrofitClient {
    // https://localhost:7126/api/ApproveOrDeclineTransaction
    // https://localhost:7126/api/GetUser/4301049898686098'

    @POST("/api/ApproveOrDeclineTransaction")
    Call<TransactionDetailsModel> postTransactionDetails(@Body TransactionDetailsModel transactionDetailsModel);

    @GET("/api/GetUser/{IDNumber}")
    Call<DataModel> getUSer(@Path("IDNumber") String userID);
}
