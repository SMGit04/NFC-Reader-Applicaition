package com.example.nfcapplication.Services;

import com.example.nfcapplication.Enums.ErrorCodes;
import com.example.nfcapplication.Interface.IErrorCodesService;
import com.example.nfcapplication.Models.CardResultModel;

public class CardValidationService {
    int digit;
    private final IErrorCodesService errorCodesService;

    public CardValidationService(IErrorCodesService errorCodesService) {
        this.errorCodesService = errorCodesService;
    }

    public CardResultModel cardNumberIsValid(String cardNumber) {
        if (luhnAlgorithm(cardNumber)) {
            return new CardResultModel(true);

        } else {

            return new CardResultModel(false, errorCodesService.getErrorCode(ErrorCodes.InvalidCardNumber));
        }
    }

    private boolean luhnAlgorithm(String cardNumber) {
        int total = 0;
        boolean isSecondDigit = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char currentChar = cardNumber.charAt(i);

            if (!Character.isDigit(currentChar)) {
                return false;
            }
            digit = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));

            if (isSecondDigit) {
                digit = doubleNumber(digit);
            }
            total += digit;
            isSecondDigit = !isSecondDigit;
        }

        return total % 10 == 0;
    }

    private static int doubleNumber(int digit) {
        int doubleNum = digit * 2;
        int sum = doubleNum > 9 ? doubleNum - 9 : doubleNum;
        return sum;
    }
}
