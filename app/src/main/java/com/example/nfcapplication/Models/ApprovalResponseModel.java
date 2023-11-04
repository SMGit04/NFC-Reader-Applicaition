package com.example.nfcapplication.Models;
import com.google.gson.annotations.SerializedName;

public class ApprovalResponseModel {

    @SerializedName("isApproved")
    private boolean isApproved;

    public ApprovalResponseModel() {
    }

    public ApprovalResponseModel(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
