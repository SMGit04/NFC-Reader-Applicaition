package com.example.nfcapplication.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.nfcapplication.Models.CardResultModel;
import com.example.nfcapplication.Services.CardValidationService;
import com.example.nfcapplication.Services.ErrorCodesService;

import org.junit.Before;
import org.junit.Test;

public class CardValidationServiceTest {

    private CardValidationService cardValidationService;

    @Before
    public void setUp() {
        ErrorCodesService errorCodesService = new ErrorCodesService();
        cardValidationService = new CardValidationService(errorCodesService);
    }

    @Test
    public void testValidCardNumber() {
        String validCardNumber = "5192602421108275";
        CardResultModel resultModel = cardValidationService.cardNumberIsValid(validCardNumber);
        assertTrue(resultModel.isValid());
    }

    @Test
    public void testInvalidCardNumber1() {
        String invalidCardNumber = "4556789012345678";
        CardResultModel resultModel = cardValidationService.cardNumberIsValid(invalidCardNumber);
        assertFalse(resultModel.isValid());
        assertEquals("Invalid card number", resultModel.getResponseMessage());
    }
    @Test
    public void testValidCardNumber2() {
        String validCardNumber = "519260242i108275";
        CardResultModel resultModel = cardValidationService.cardNumberIsValid(validCardNumber);
        assertFalse(resultModel.isValid());
        assertEquals("Invalid card number", resultModel.getResponseMessage());
    }
}

