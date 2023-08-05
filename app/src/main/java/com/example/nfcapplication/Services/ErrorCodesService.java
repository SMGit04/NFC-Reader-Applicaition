package com.example.nfcapplication.Services;

import com.example.nfcapplication.Enums.ErrorCodes;
import com.example.nfcapplication.Interfaces.IErrorCodesService;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodesService implements IErrorCodesService {
    private final Map<ErrorCodes, String> errorCodeMap;

    public ErrorCodesService() {
        errorCodeMap = new HashMap<>();
        errorCodeMap.put(ErrorCodes.InvalidCardNumber, "Invalid card");
    }

    @Override
    public String getErrorCode(ErrorCodes errorCode) {
        return errorCodeMap.getOrDefault(errorCode, "Unknown error");
    }
}
