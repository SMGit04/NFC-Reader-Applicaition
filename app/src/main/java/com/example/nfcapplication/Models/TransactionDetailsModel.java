package com.example.nfcapplication.Models;

import com.google.gson.annotations.SerializedName;

public class TransactionDetailsModel {

    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("idNumber")
    private String idNumber;
    @SerializedName("cardNumber")
    private String cardNumber;
    @SerializedName("expiryDate")
    private String expiryDate;
    @SerializedName("accountNumber")
    private String accountNumber;
    @SerializedName("accountBalance")
    private String amount;
    @SerializedName("cvv")
    private String cvv;

    public TransactionDetailsModel() {}

    public TransactionDetailsModel(String name, String surname, String cardNumber, String expiryDate, String accountNumber, String cvv, String amount) {
        this.name = name;
        this.amount = amount;
        this.surname = surname;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.accountNumber = accountNumber;
        this.cvv = cvv;
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
        return cvv;
    }


}
