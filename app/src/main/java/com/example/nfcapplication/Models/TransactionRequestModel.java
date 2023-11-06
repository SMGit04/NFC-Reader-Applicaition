package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionRequestModel {
    @SerializedName("idNumber")
    private String IDNumber;

    @SerializedName("cardNumber")
    private String cardNumber;

    @SerializedName("accountNumber")
    private String accountNumber;

    @SerializedName("expiryDate")
    private String expiryDate;

    @SerializedName("transactionAmount")
    private double amount;

    @SerializedName("merchantName")
    private String merchantName;
    @SerializedName("cvv")
    private String CVV;

    @SerializedName("accountBalance")
    private double accountBalance;
    @SerializedName("isApproved")
    boolean isApproved;
    public TransactionRequestModel() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCvv() {
        return CVV;
    }

    public static double getAmount() {
        double randomValue = Math.random() * (100 - 10 + 1) + 10;
        return Math.round(randomValue * 100.0) / 100.0;
    }
    public boolean getApproved() {
        return isApproved;
    }
    public String getCVV() {
        return CVV;
    }

    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getIDNumber() {
        return IDNumber;
    }

}