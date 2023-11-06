package com.example.nfcapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nfcapplication.Interfaces.IRetrofitClient;
import com.example.nfcapplication.Interfaces.NFCListener;
import com.example.nfcapplication.Models.TransactionRequestModel;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenDisplay = findViewById(R.id.displayTextView);
        //enterButton = findViewById(R.id.enterButton);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Initialize the progressDialog
        progressDialog = new ProgressDialog(this);

        //sendTransactionNotification();
        sendTransactionRequest();
        //  if (nfcCapable()) return;
        nfcService = new NFCService(this, MainActivity.this::onTagRead);
        nfcService.onTagDetected(getIntent());

        // TODO: Create an interface of SendData() and call it when the Tag is detected
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
            public void onFailure(@NonNull Call<TransactionRequestModel> call, @NonNull Throwable t) {
                System.err.println("Connection request failed: " + t.getMessage());
            }
        });
    }

    public void sendTransactionRequest() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);

        Call<TransactionRequestModel> modelCall = retrofitClient.sendTransactionRequestData(createSampleTransactionDetails());
        modelCall.enqueue(new Callback<TransactionRequestModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionRequestModel> call, @NonNull Response<TransactionRequestModel> response) {

                if (response.isSuccessful()) {
                    TransactionRequestModel approvalResponse = response.body();

                    if (approvalResponse != null && approvalResponse.getApproved()) {
                        screenDisplay("Approved.");

                    } else {
                        screenDisplay("Declined.");
                    }
                } else {
                    screenDisplay("Network Error " + response.message());
                    System.err.println("Failed to get approval status." + response.message());
                }
            }
            @Override
            public void onFailure(@NonNull Call<TransactionRequestModel> call, @NonNull Throwable t) {
                screenDisplay("Timeout\nDo you need more time?");
                System.err.println("Network request failed: " + t.getMessage());
            }
        });
    }

    private void screenDisplay(String display) {

        screenDisplay.setText(display);
    }

    // Simulate the TransactionRequestModel with sample data
    private TransactionRequestModel createSampleTransactionDetails() {
        TransactionRequestModel transactionDetails = new TransactionRequestModel();
        transactionDetails.setIDNumber("4301049898686098");
        transactionDetails.setCardNumber("657835675677");
        transactionDetails.setAccountNumber("56676576");
        transactionDetails.setExpiryDate("2023-05-04");
        transactionDetails.setMerchantName("Pep");
        transactionDetails.setCVV("987");
        return transactionDetails;
    }

    private void printTransactionDetails(TransactionRequestModel transactionDetails) {
        Log.d("MainActivity", "ID Number: " + transactionDetails.getIDNumber());
        Log.d("MainActivity", "Card Number: " + transactionDetails.getCardNumber());
        Log.d("MainActivity", "Account Number: " + transactionDetails.getAccountNumber());
        Log.d("MainActivity", "Expiry Date: " + transactionDetails.getExpiryDate());
        Log.d("MainActivity", "Amount: " + TransactionRequestModel.getAmount());
        Log.d("MainActivity", "CVV: " + transactionDetails.getCVV());
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
