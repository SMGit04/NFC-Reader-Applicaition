package com.example.nfcapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nfcapplication.Interfaces.NFCListener;
import com.example.nfcapplication.Models.TransactionDetailsModel;
import com.example.nfcapplication.R;
import com.example.nfcapplication.Remote.IRetrofitClient;
import com.example.nfcapplication.Remote.RetrofitClient;
import com.example.nfcapplication.Services.NFCService;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements NFCListener {
    NfcAdapter nfcAdapter;
    private NFCService nfcService;
    TextView nfc_contents;
    private Button enterButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nfc_contents = findViewById(R.id.displayTextView);
        enterButton = findViewById(R.id.enterButton);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);


        if (NfcCapable()) return;
        nfcService = new NFCService(this, MainActivity.this::onTagRead);
        nfcService.onTagDetected(getIntent());

        TransactionDetailsModel transactionDetails = createSampleTransactionDetails();
        String jsonDataFromNFC = new Gson().toJson(transactionDetails);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(jsonDataFromNFC);
            }
        });
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

    private TransactionDetailsModel createSampleTransactionDetails() {
        // Simulate the TransactionDetailsModel with sample data
        TransactionDetailsModel transactionDetails = new TransactionDetailsModel();
        transactionDetails.setName("John");
        transactionDetails.setSurname("Doe");
        transactionDetails.setIDNumber("123456789");
        transactionDetails.setCardNumber("1234567890123456");
        transactionDetails.setAccountNumber("1234567890");
        transactionDetails.setExpiryDate("20230705");
        transactionDetails.setAmount(100);
        transactionDetails.setCVV("123");
        transactionDetails.setPIN("1234");
        return transactionDetails;
    }

    private void sendData(String jsonData) {

        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Creates the Retrofit client and service
        IRetrofitClient iRetrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);


        // Parse the JSON data
        Gson gson = new Gson();
        TransactionDetailsModel transactionDetailsModel = gson.fromJson(jsonData, TransactionDetailsModel.class);

        // final TransactionDetailsModel transactionDetailsModel = new TransactionDetailsModel();
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
        nfc_contents.setText(text);

    }

}
