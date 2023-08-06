package com.trungdz.appshipper.service;

import com.trungdz.appshipper.service.model.InfoLogin;
import com.trungdz.appshipper.response.AuthLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPIService {


    @POST("account/shipper/login")
    Call<AuthLoginResponse> login(@Body InfoLogin infoLogin);
}
