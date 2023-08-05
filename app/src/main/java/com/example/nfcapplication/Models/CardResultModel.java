package com.example.nfcapplication.Models;

public class CardResultModel {

    private boolean isValidCard;
    private String responseMessage;

    public CardResultModel(boolean isValidCard) {
        this.isValidCard = isValidCard;
        this.responseMessage = "";
    }

    public CardResultModel(boolean isValidCard, String responseMessage) {
        this.isValidCard = isValidCard;
        this.responseMessage = responseMessage;
    }

    public void setValidCard(boolean validCard) {
        isValidCard = validCard;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isValid() {
        return isValidCard;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

}
