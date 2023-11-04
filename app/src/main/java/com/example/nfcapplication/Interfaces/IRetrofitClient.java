package com.example.nfcapplication.Interfaces;

import com.example.nfcapplication.Models.ApprovalResponseModel;
import com.example.nfcapplication.Models.TransactionRequestModel;
import com.example.nfcapplication.Models.TransactionRequestResultModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitClient {
//    @POST("/api/TransactionRequest/ApproveOrDeclineTransaction")
//    Call<ApprovalResponseModel> postTransactionDetails(@Body ApprovalResponseModel approvalResponseModel);

    // TODO: This is not supposed to be used here, remove and use sendTransactionRequestData() to send client data
    @POST("/api/TransactionApproval/ApproveOrDeclineTransaction")
    Call<TransactionRequestResultModel> postTransactionDetails(@Body TransactionRequestResultModel requestResultModel);

    @POST("/api/Notification/notification")
    Call<TransactionRequestModel> sendTransactionRequestNotification();

    @POST("/api/Notification/entityTransactionData")
    Call<TransactionRequestModel> sendTransactionRequestData();
}
