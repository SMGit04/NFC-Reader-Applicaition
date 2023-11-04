package com.example.nfcapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nfcapplication.Interfaces.IRetrofitClient;
import com.example.nfcapplication.Interfaces.NFCListener;
import com.example.nfcapplication.Models.ApprovalResponseModel;
import com.example.nfcapplication.Models.TransactionRequestModel;
import com.example.nfcapplication.Models.TransactionRequestResultModel;
import com.example.nfcapplication.R;
import com.example.nfcapplication.Remote.RetrofitClient;
import com.example.nfcapplication.Services.NFCService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NFCListener {
    NfcAdapter nfcAdapter;
    private NFCService nfcService;
    TextView screenDisplay;
    private final String SCREEN_DISPLAY = "Authorize transaction of: ";
    TextView amountToBePayed;
    private Button enterButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenDisplay = findViewById(R.id.displayTextView);
        //  amountToBePayed = findViewById(R.id.totalTextView);
        enterButton = findViewById(R.id.enterButton);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Initialize the progressDialog
        progressDialog = new ProgressDialog(this);

       // sendTransactionNotification();
        sendData();
      //  if (nfcCapable()) return;
        nfcService = new NFCService(this, MainActivity.this::onTagRead);
        nfcService.onTagDetected(getIntent());


        // TODO: Create an interface of SendData() and call it when the Tag is detected
        // TODO: Code refator
    }


        private void sendTransactionNotification() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<TransactionRequestModel> modelCall = retrofitClient.sendTransactionRequestNotification();
        //screenDisplay("Notification Sent \nProcessing");
        modelCall.enqueue(new Callback<TransactionRequestModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionRequestModel> call, @NonNull Response<TransactionRequestModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code() + "Failure Sending Message", Toast.LENGTH_LONG).show();
                    System.out.println(response.code() + "Failure Sending Message");
                }

            }
            @Override
            public void onFailure(Call<TransactionRequestModel> call, Throwable t) {

            }
        });
    }

    public void sendData() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        // Make a network request to the C# REST API
        ApprovalResponseModel approvalResponse;
        TransactionRequestResultModel requestResultModel = new TransactionRequestResultModel();
        Call<TransactionRequestResultModel> modelCall = retrofitClient.postTransactionDetails(requestResultModel);

        modelCall.enqueue(new Callback<TransactionRequestResultModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionRequestResultModel> call, @NonNull Response<TransactionRequestResultModel> response) {
                if (response.isSuccessful()) {

                    TransactionRequestResultModel approvalResponse = response.body();
                    if (approvalResponse != null && approvalResponse.getIsApprovedMessage()) {
                        screenDisplay("Approved.");

                    } else {
                        screenDisplay("Declined.");
                    }
                } else {
                    screenDisplay("Network Error."+ response.code());
                    System.err.println("Failed to get approval status.");
                }
            }
            @Override
            public void onFailure(@NonNull Call<TransactionRequestResultModel> call, @NonNull Throwable t) {
                System.err.println("Network request failed: " + t.getMessage());
            }
        });
    }

    private void screenDisplay(String display) {

        screenDisplay.setText(display);
    }

    private ApprovalResponseModel approvalResponseModel() {
        ApprovalResponseModel approvalResponseModel = new ApprovalResponseModel();
        approvalResponseModel.setApproved(approvalResponseModel.isApproved());
        return approvalResponseModel;
    }
    // Simulate the TransactionRequestModel with sample data
    private TransactionRequestModel createSampleTransactionDetails() {
        TransactionRequestModel transactionDetails = new TransactionRequestModel();
        transactionDetails.setName("Isaac");
        transactionDetails.setSurname("Newton");
        transactionDetails.setIDNumber("4301049898686098");
        transactionDetails.setCardNumber("657835675677");
        transactionDetails.setAccountNumber("56676576");
        transactionDetails.setExpiryDate("2023-05-04");
        transactionDetails.setMerchantName("Pep");
        // transactionDetails.setAmount();
        transactionDetails.setCVV("987");
       // transactionDetails.setPIN("5667");
        return transactionDetails;
    }

    private void printTransactionDetails(TransactionRequestModel transactionDetails) {
        Log.d("MainActivity", "Name: " + transactionDetails.getName());
        Log.d("MainActivity", "Surname: " + transactionDetails.getSurname());
        Log.d("MainActivity", "ID Number: " + transactionDetails.getIDNumber());
        Log.d("MainActivity", "Card Number: " + transactionDetails.getCardNumber());
        Log.d("MainActivity", "Account Number: " + transactionDetails.getAccountNumber());
        Log.d("MainActivity", "Expiry Date: " + transactionDetails.getExpiryDate());
        Log.d("MainActivity", "Amount: " + TransactionRequestModel.getAmount());
        Log.d("MainActivity", "CVV: " + transactionDetails.getCVV());
      //  Log.d("MainActivity", "PIN: " + transactionDetails.getPIN());

    }

    private boolean nfcCapable() {
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not supported on this device.", Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is disabled.", Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfcService.onNewIntent(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onTagRead(String text) {
        screenDisplay.setText(text);
    }

}
