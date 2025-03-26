package com.techtist.srikasiviswanathar.api;

/**
 * Created by Venkatesh on 11/29/2022.
 */



import com.techtist.srikasiviswanathar.forgotpassword.activities.models.ForgotpasswordResponse;
import com.techtist.srikasiviswanathar.login.models.LoginResponse;
import com.techtist.srikasiviswanathar.news.activities.models.NewsRequest;
import com.techtist.srikasiviswanathar.news.activities.models.NewsResponse;
import com.techtist.srikasiviswanathar.pooja.activities.models.PoojaRequest;
import com.techtist.srikasiviswanathar.pooja.activities.models.PoojaResponse;
import com.techtist.srikasiviswanathar.register.models.ApartmentResponse;
import com.techtist.srikasiviswanathar.register.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("app_control")
    Call<LoginResponse> loginReguest(@Body String loginRequestData);

    @POST("app_control")
    Call<ForgotpasswordResponse> forgotPassword(@Body String forgotPasswordRequest);
    @POST("app_control")
    Call<NewsResponse> getActiveNews(@Body String newsRequest);

    @POST("app_control")
    Call<RegisterResponse> registerUser(@Body String registerRequest);

    @POST("app_control")
    Call<List<ApartmentResponse>> getBlocks();

    @POST("app_control")
    Call<List<ApartmentResponse>> getDoorNumbers(@Body String blockName);

    @POST("app_control")
    Call<PoojaResponse> getPoojaDetails(@Body String PoojaRequest);



}