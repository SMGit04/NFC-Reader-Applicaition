package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionRequestModel {

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

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
    public TransactionRequestModel(String name, String surname, String IDNumber, String merchantName, String cardNumber, String accountNumber, String expiryDate, String CVV, double accountBalance, boolean status) {
        this.name = name;
        this.surname = surname;
        this.IDNumber = IDNumber;
        this.merchantName = merchantName;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
        this.accountBalance = accountBalance;
        this.isApproved = status;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

//    public String getPIN() {
//        return PIN;
//    }

    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

//    public void setPIN(String PIN) {
//        this.PIN = PIN;
//    }

    public void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public boolean getIsApproved() {
        return isApproved;
    }
}