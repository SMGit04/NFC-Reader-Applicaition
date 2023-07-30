package com.example.nfcapplication.Services;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.util.Log;

import com.example.nfcapplication.Interfaces.NFCListener;

import java.io.UnsupportedEncodingException;

public class NFCService {
    String text = "";
    Tag myTag;
    private final Context context;
    private final NFCListener listener;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] writingTagFilters;

    public NFCService(Context context, NFCListener listener) {
        this.context = context;
        this.listener = listener;
        nfcAdapter = NfcAdapter.getDefaultAdapter(context);
    }

    public void onTagDetected(Intent intent) {

        if (intent == null) {
            return;
        }
        readFromIntent(intent);
        pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, context.getClass())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writingTagFilters = new IntentFilter[]{tagDetected};
    }

    public void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages = null;
            if (rawMessages != null) {
                messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++)
                    messages[i] = (NdefMessage) rawMessages[i];
            }
            buildTagViews(messages);
        }
    }

    private void buildTagViews(NdefMessage[] ndefMessages) {
        if (ndefMessages == null || ndefMessages.length == 0) return;
        byte[] payload = ndefMessages[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 51; // Get the Language Code, e.g. "en"

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncodingException", e.toString());
        }
        listener.onTagRead(text);
    }

    public void onNewIntent(Intent intent) {
        readFromIntent(intent);

        readFromIntent(intent);
        if (intent.getAction() != null) {
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    public boolean isNFCEnabled() {
        return nfcAdapter != null && nfcAdapter.isEnabled();
    }
}