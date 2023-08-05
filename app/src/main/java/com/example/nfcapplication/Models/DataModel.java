package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class DataModel {

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

    @SerializedName("accountBalance")
    private int balance;

    @SerializedName("cvv")
    private String CVV;

    @SerializedName("pin")
    private String PIN;

    public DataModel(String name, String surname, String IDNumber, String cardNumber, String accountNumber, String expiryDate, int balance, String CVV, String PIN) {
        this.name = name;
        this.surname = surname;
        this.IDNumber = IDNumber;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
        this.balance = balance;
        this.CVV = CVV;
        this.PIN = PIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
