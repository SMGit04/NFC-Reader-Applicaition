//package com.example.nfcapplication.Tests;
//
//import static org.junit.Assert.assertNotNull;
//
//import com.example.nfcapplication.Interfaces.IRetrofitClient;
//import com.example.nfcapplication.Models.ApprovalResponse;
//import com.example.nfcapplication.Models.TransactionRequestModel;
//import com.example.nfcapplication.Remote.RetrofitClient;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class SendNotificaitionTest {
//
//    IRetrofitClient retrofitClient = RetrofitClient.getRetrofit().create(IRetrofitClient.class);
//    Call<ApprovalResponse> modelCall = retrofitClient.sendTransactionRequestNotification();
//
//    @Test
//    public void testSendTransactionRequestNotification() throws IOException, IOException {
//        // Prepare test data
//        String text = "Test Notification Text";
//        ApprovalResponse expectedResponse = new ApprovalResponse(); // Set your expected response here
//
//        // Create a mock Call object
//        Call<ApprovalResponse> call = mock(Call.class);
//
//        // Mock the behavior of yourApi.sendTransactionRequestNotification
//        when(yourApi.sendTransactionRequestNotification(text)).thenReturn(call);
//
//        // Mock the response
//        when(call.execute()).thenReturn(Response.success(expectedResponse));
//
//        // Make the actual API call
//        ApprovalResponse response = yourApiService.sendTransactionRequestNotification(text).execute().body();
//
//        // Verify the response
//        assertNotNull(response);
//        // Add more assertions to validate the response as needed
//    }
//    }
//
//}
