package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class SendNotificationModel {

    @SerializedName("status")
    String responseMessage;
    @SerializedName("idNumber")
    private String IDNumber;

    public SendNotificationModel(String responseMessage, String IDNumber) {
        this.responseMessage = responseMessage;
        this.IDNumber = IDNumber;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }
}
