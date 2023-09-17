package com.example.nfcapplication.Interfaces;

import com.example.nfcapplication.Models.DataModel;
import com.example.nfcapplication.Models.SendNotificationModel;
import com.example.nfcapplication.Models.TransactionDetailsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRetrofitClient {
    @POST("/api/ApproveOrDeclineTransaction")
    Call<TransactionDetailsModel> postTransactionDetails(@Body TransactionDetailsModel transactionDetailsModel);

    @POST("/api/notification")
    Call<SendNotificationModel> sendTransactionRequestNotification();
}
