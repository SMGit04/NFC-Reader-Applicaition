package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionRequestResultModel {
    // Yes or No
    private boolean responseMessage;

    // Biometrics recognised or not
    private  boolean biometricAuthenticated;
    @SerializedName("isApproved")
    boolean isApproved;
    public TransactionRequestResultModel() {
    }

    public TransactionRequestResultModel(boolean responseMessage, boolean biometricAuthenticated) {
        this.responseMessage = responseMessage;
        this.biometricAuthenticated = biometricAuthenticated;
    }

    public boolean getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(boolean responseMessage) {
        this.responseMessage = responseMessage;
    }
    public void setIsApprovedMessage(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean getIsApprovedMessage() {
        return isApproved;
    }
    public boolean isBiometricAuthenticated() {
        return biometricAuthenticated;
    }

    public void setBiometricAuthenticated(boolean biometricAuthenticated) {
        this.biometricAuthenticated = biometricAuthenticated;
    }
}
