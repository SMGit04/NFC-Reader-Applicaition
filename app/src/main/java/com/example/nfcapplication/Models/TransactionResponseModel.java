package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionResponseModel {

    @SerializedName("status")
    String responseMessage;

    public TransactionResponseModel(String responseMessage) {
        this.responseMessage = responseMessage;

    }
    public TransactionResponseModel() {}

    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
