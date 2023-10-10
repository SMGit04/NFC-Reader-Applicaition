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
import com.example.nfcapplication.Models.TransactionResponseModel;
import com.example.nfcapplication.Models.TransactionDetailsModel;
import com.example.nfcapplication.R;
import com.example.nfcapplication.Interfaces.IRetrofitClient;
import com.example.nfcapplication.Remote.RetrofitClient;
import com.example.nfcapplication.Services.NFCService;

import java.util.Objects;

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


       // if (nfcCapable()) return;
        nfcService = new NFCService(this, MainActivity.this::onTagRead);
        nfcService.onTagDetected(getIntent());
         screenDisplay();
      //  sendData();
        // TODO: Create an interface of SendData() and call it when the Tag is detected
        // sendTransactionNotification();
    }

    private void sendTransactionNotification() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<TransactionResponseModel> modelCall = retrofitClient.sendTransactionRequestNotification();

        modelCall.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionResponseModel> call, @NonNull Response<TransactionResponseModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code() + "Failure Sending Message", Toast.LENGTH_LONG).show();
                    System.out.println(response.code() + "Failure Sending Message");
                }
                TransactionResponseModel responseStatus = response.body();
                //  Toast.makeText(MainActivity.this, response.code() + " Message Sent", Toast.LENGTH_LONG).show();
                assert responseStatus != null;
                Toast.makeText(MainActivity.this, responseStatus.getResponseMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<TransactionResponseModel> call, @NonNull Throwable t) {
            }
        });
    }

    public void sendData() {
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<TransactionDetailsModel> modelCall = retrofitClient.postTransactionDetails(createSampleTransactionDetails());

        modelCall.enqueue(new Callback<TransactionDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionDetailsModel> call, @NonNull Response<TransactionDetailsModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TransactionDetailsModel responseStatus = response.body();
                // Toast.makeText(MainActivity.this, response.code() + " Response", Toast.LENGTH_LONG).show();
//                assert responseStatus != null;
//                Toast.makeText(MainActivity.this, responseStatus.getStatus(), Toast.LENGTH_LONG).show();
               // screenDisplay();
                // printTransactionDetails(createSampleTransactionDetails());
            }

            @Override
            public void onFailure(@NonNull Call<TransactionDetailsModel> call, @NonNull Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void screenDisplay() {
        final String[] responseMessage = {"Processing"}; // Default message if response is not successful
        IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
        Call<TransactionResponseModel> modelCall = retrofitClient.TransactionResponse();
        modelCall.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionResponseModel> call, @NonNull Response<TransactionResponseModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    // You can choose to handle the error here or keep the default message.
                    // For now, I'm just setting the default message.
                } else {
                    TransactionResponseModel responseModel = response.body();
                    if (responseModel != null) {
                        responseMessage[0] = responseModel.getResponseMessage();
                    }
                }
                screenDisplay.setText(responseMessage[0]);
                Toast.makeText(MainActivity.this, String.valueOf(response.code()) + responseMessage[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<TransactionResponseModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error Processing Transaction", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Simulate the TransactionDetailsModel with sample data
    private TransactionDetailsModel createSampleTransactionDetails() {
        TransactionDetailsModel transactionDetails = new TransactionDetailsModel();
        transactionDetails.setName("Isaac");
        transactionDetails.setSurname("Newton");
        transactionDetails.setIDNumber("4301049898686098");
        transactionDetails.setCardNumber("657835675677");
        transactionDetails.setAccountNumber("56676576");
        transactionDetails.setExpiryDate("2023-05-04");
        transactionDetails.setMerchantName("Pep");
        // transactionDetails.setAmount();
        transactionDetails.setCVV("987");
        transactionDetails.setPIN("5667");
        return transactionDetails;
    }

//    //
//    -- Narrate the story mimicking the journey a user would
//    //


    private void printTransactionDetails(TransactionDetailsModel transactionDetails) {
        Log.d("MainActivity", "Name: " + transactionDetails.getName());
        Log.d("MainActivity", "Surname: " + transactionDetails.getSurname());
        Log.d("MainActivity", "ID Number: " + transactionDetails.getIDNumber());
        Log.d("MainActivity", "Card Number: " + transactionDetails.getCardNumber());
        Log.d("MainActivity", "Account Number: " + transactionDetails.getAccountNumber());
        Log.d("MainActivity", "Expiry Date: " + transactionDetails.getExpiryDate());
        Log.d("MainActivity", "Amount: " + TransactionDetailsModel.getAmount());
        Log.d("MainActivity", "CVV: " + transactionDetails.getCVV());
        Log.d("MainActivity", "PIN: " + transactionDetails.getPIN());

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
