package com.example.nfcapplication.Interfaces;

import com.example.nfcapplication.Models.TransactionRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitClient {
    @POST("/api/TransactionRequest/ApproveOrDeclineTransaction")
    Call<TransactionRequestModel> postTransactionDetails(@Body TransactionRequestModel transactionDetailsModel);
}
