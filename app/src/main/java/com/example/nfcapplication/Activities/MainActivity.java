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

import com.example.nfcapplication.Interfaces.NFCListener;
import com.example.nfcapplication.Models.DataModel;
import com.example.nfcapplication.Models.TransactionDetailsModel;
import com.example.nfcapplication.R;
import com.example.nfcapplication.Interfaces.IRetrofitClient;
import com.example.nfcapplication.Remote.RetrofitClient;
import com.example.nfcapplication.Services.NFCService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NFCListener {
    NfcAdapter nfcAdapter;
    private NFCService nfcService;
    TextView screenDisplay;
    TextView amountToBePayed;
    private Button enterButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenDisplay = findViewById(R.id.displayTextView);
        amountToBePayed = findViewById(R.id.totalTextView);
        enterButton = findViewById(R.id.enterButton);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Initialize the progressDialog
        progressDialog = new ProgressDialog(this);


//        if (NfcCapable()) return;
//        nfcService = new NFCService(this, MainActivity.this::onTagRead);
//        nfcService.onTagDetected(getIntent());

        sendData();
        // getData();
    }

    private void sendData() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<TransactionDetailsModel> modelCall = retrofitClient.postTransactionDetails(createSampleTransactionDetails());

        modelCall.enqueue(new Callback<TransactionDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionDetailsModel> call, @NonNull Response<TransactionDetailsModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                TransactionDetailsModel responseStatus = response.body();
                Toast.makeText(MainActivity.this, response.code() + " Response", Toast.LENGTH_LONG).show();
                assert responseStatus != null;
                Toast.makeText(MainActivity.this, responseStatus.getStatus(), Toast.LENGTH_LONG).show();
                printTransactionDetails(createSampleTransactionDetails());
            }

            @Override
            public void onFailure(@NonNull Call<TransactionDetailsModel> call, @NonNull Throwable t) {
                Log.d("onFailure: ", t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getData() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<DataModel> modelCall = retrofitClient.getUSer("4301049898686098");

        modelCall.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, response.code() + " Response", Toast.LENGTH_LONG).show();
                // Obtain the TransactionDetailsModel from the response
                DataModel responseModel = response.body();
                if (responseModel != null) {
                    printTransactionDetails(responseModel);
                    Toast.makeText(MainActivity.this, "Data retrieved: " + response.code(), Toast.LENGTH_LONG).show();
                } else {
                    Log.d("MainActivity", "Response body is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Log.d("onFailure: ", t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private TransactionDetailsModel createSampleTransactionDetails() {
        // Simulate the TransactionDetailsModel with sample data
        TransactionDetailsModel transactionDetails = new TransactionDetailsModel();
        transactionDetails.setName("Isaac");
        transactionDetails.setSurname("Newton");
        transactionDetails.setIDNumber("4301049898686098");
        transactionDetails.setCardNumber("657835675677");
        transactionDetails.setAccountNumber("56676576");
        transactionDetails.setExpiryDate("2023-05-04");
       // transactionDetails.setAmount();
        transactionDetails.setCVV("987");
        transactionDetails.setPIN("5667");
        return transactionDetails;
    }

    private void printTransactionDetails(TransactionDetailsModel transactionDetails) {
        Log.d("MainActivity", "Name: " + transactionDetails.getName());
        Log.d("MainActivity", "Surname: " + transactionDetails.getSurname());
        Log.d("MainActivity", "ID Number: " + transactionDetails.getIDNumber());
        Log.d("MainActivity", "Card Number: " + transactionDetails.getCardNumber());
        Log.d("MainActivity", "Account Number: " + transactionDetails.getAccountNumber());
        Log.d("MainActivity", "Expiry Date: " + transactionDetails.getExpiryDate());
        Log.d("MainActivity", "Amount: " + transactionDetails.getAmount());
        Log.d("MainActivity", "CVV: " + transactionDetails.getCVV());
        Log.d("MainActivity", "PIN: " + transactionDetails.getPIN());

    }

    private void printTransactionDetails(DataModel transactionDetails) {
        Log.d("MainActivity", "Name: " + transactionDetails.getName());
        Log.d("MainActivity", "Surname: " + transactionDetails.getSurname());
        Log.d("MainActivity", "ID Number: " + transactionDetails.getIDNumber());
        Log.d("MainActivity", "Card Number: " + transactionDetails.getCardNumber());
        Log.d("MainActivity", "Account Number: " + transactionDetails.getAccountNumber());
        Log.d("MainActivity", "Expiry Date: " + transactionDetails.getExpiryDate());
        Log.d("MainActivity", "Balance: " + transactionDetails.getBalance());
        Log.d("MainActivity", "CVV: " + transactionDetails.getCVV());
        Log.d("MainActivity", "PIN: " + transactionDetails.getPIN());

    }


    private boolean NfcCapable() {
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
