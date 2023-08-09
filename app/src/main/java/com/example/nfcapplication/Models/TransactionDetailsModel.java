package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionDetailsModel {

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

    @SerializedName("amount")
    private static int amount;

    @SerializedName("cvv")
    private String CVV;

    @SerializedName("pin")
    private String PIN;
    @SerializedName("status")
    private String status;


    public TransactionDetailsModel() {
    }

    public TransactionDetailsModel(String idNumber) {
    }

    public TransactionDetailsModel(String name, String surname, String cardNumber, String expiryDate, String accountNumber, String CVV, int amount, String PIN) {
        this.name = name;
        TransactionDetailsModel.amount = amount;
        this.surname = surname;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.accountNumber = accountNumber;
        this.CVV = CVV;
        this.PIN = PIN;
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

    public static int getAmount() {
        return amount = (int) (Math.random() * (100 - 10 + 1) + 10);
    }

    public String getStatus() {
        return status;
    }

    public String getCVV() {
        return CVV;
    }

    public String getPIN() {
        return PIN;
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

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIDNumber() {
        return IDNumber;
    }

}