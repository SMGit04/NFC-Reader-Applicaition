//package com.example.nfcapplication.Tests;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static org.mockito.Mockito.*;
//
//import com.example.nfcapplication.Activities.MainActivity;
//import com.example.nfcapplication.Interfaces.IRetrofitClient;
//import com.example.nfcapplication.Models.TransactionRequestModel;
//
//public class MainActivityTest {
//
//    @Mock
//    private IRetrofitClient mockRetrofitClient;
//
//    @Mock
//    private Call<TransactionRequestModel> mockCall;
//
//    private MainActivity mainActivity;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mainActivity = Mockito.spy(new MainActivity());
//
//        // Mock the Retrofit client instance to return the mock call
//        when(RetrofitClient.getRetrofit().create(IRetrofitClient.class)).thenReturn(mockRetrofitClient);
//        when(mockRetrofitClient.postTransactionDetails(any())).thenReturn(mockCall);
//    }
//
//    @Test
//    public void testSendData_SuccessfulResponse() {
//        // Mock a successful response
//        TransactionRequestModel mockResponse = new TransactionRequestModel();
//        mockResponse.setStatus("Success");
//        Response<TransactionRequestModel> successResponse = Response.success(mockResponse);
//
//        // Configure the mock call to return the success response when enqueued
//        doAnswer(invocation -> {
//            Callback<TransactionRequestModel> callback = invocation.getArgument(0);
//            callback.onResponse(mockCall, successResponse);
//            return null;
//        }).when(mockCall).enqueue(any());
//
//        // Call the method to be tested
//        mainActivity.sendData();
//
//        // Verify that the screenDisplay method is called
//        verify(mainActivity).screenDisplay();
//    }
//
//    @Test
//    public void testSendData_UnsuccessfulResponse() {
//        // Mock an unsuccessful response
//        Response<TransactionRequestModel> errorResponse = Response.error(404, mockResponseBody);
//
//        // Configure the mock call to return the error response when enqueued
//        doAnswer(invocation -> {
//            Callback<TransactionRequestModel> callback = invocation.getArgument(0);
//            callback.onResponse(mockCall, errorResponse);
//            return null;
//        }).when(mockCall).enqueue(any());
//
//        // Call the method to be tested
//        mainActivity.sendData();
//
//        // Verify that screenDisplay method is not called for an unsuccessful response
//        verify(mainActivity, never()).screenDisplay();
//    }
//}
//
