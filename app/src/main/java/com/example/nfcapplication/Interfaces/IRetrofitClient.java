package com.example.nfcapplication.Interfaces;

import com.example.nfcapplication.Models.TransactionResponseModel;
import com.example.nfcapplication.Models.TransactionDetailsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitClient {
    @POST("/api/TransactionRequest/ApproveOrDeclineTransaction")
    Call<TransactionDetailsModel> postTransactionDetails(@Body TransactionDetailsModel transactionDetailsModel);

    @GET("/api/Notification/authorizationResponse")
    Call<TransactionResponseModel> TransactionResponse();
    @POST("/api/Notification/notification")
    Call<TransactionResponseModel> sendTransactionRequestNotification();
}
