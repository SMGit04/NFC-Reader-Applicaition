package com.example.nfcapplication.Remote;

import android.database.Observable;

import com.example.nfcapplication.Models.TransactionDetailsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitClient {

    // https://localhost:7126/api/Encryption
    // https://localhost:7126/api/ApproveOrDeclineTransaction

    @POST("/api/ApproveOrDeclineTransaction")
    Call<TransactionDetailsModel> postTransactionDetails(@Body TransactionDetailsModel transactionDetailsModel);
}
