package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionDetailsModel {

    @SerializedName("Name")
    private String name;

    @SerializedName("Surname")
    private String surname;

    @SerializedName("IDNumber")
    private String IDNumber;

    @SerializedName("CardNumber")
    private String cardNumber;

    @SerializedName("AccountNumber")
    private String accountNumber;

    @SerializedName("ExpiryDate")
    private String expiryDate;

    @SerializedName("Amount")
    private int amount;

    @SerializedName("CVV")
    private String CVV;

    @SerializedName("PIN")
    private String PIN;

    public TransactionDetailsModel() {
    }

    public TransactionDetailsModel(String name, String surname, String cardNumber, String expiryDate, String accountNumber, String CVV, int amount, String PIN) {
        this.name = name;
        this.amount = amount;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public int getAmount() {
        return amount;
    }

    public String getCVV() {
        return CVV;
    }

    public String getPIN() {
        return PIN;
    }
}
