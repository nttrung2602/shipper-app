package com.trungdz.appshipper.repository;

import com.google.gson.Gson;
import com.trungdz.appshipper.service.model.InfoLogin;
import com.trungdz.appshipper.network.APIClient;
import com.trungdz.appshipper.response.AuthLoginResponse;
import com.trungdz.appshipper.response.MessageResponse;
import com.trungdz.appshipper.service.AuthAPIService;
import com.trungdz.appshipper.storage.MySharedPreference;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthRepository {

    private AuthAPIService authAPIService;

    public AuthRepository() {
        Retrofit retrofit = APIClient.getRetrofit();

        authAPIService = retrofit.create(AuthAPIService.class);
    }

    public void loginRemote(InfoLogin infoLogin, IResponse<String> callback) {
        authAPIService.login(infoLogin).enqueue(new Callback<AuthLoginResponse>() {
            @Override
            public void onResponse(Call<AuthLoginResponse> call, Response<AuthLoginResponse> response) {

                if (response.isSuccessful()) {
                    AuthLoginResponse authLoginResponse= response.body();
                    Gson gson=new Gson();
                    String userInfo=gson.toJson(authLoginResponse.getUserInfo());

                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();
                    mySharedPreference.putToken(authLoginResponse.getToken()); // Save Token from response
                    mySharedPreference.putUserInfo(userInfo);

                    callback.onResponse(authLoginResponse.getMessage());
                }else{
                    try {
                        String errorBody=response.errorBody().string();

                        // convert response body to POJO for bad request
                        Gson gson= new Gson();
                        MessageResponse messageResponse = gson.fromJson(errorBody, MessageResponse.class);

                        callback.onResponse(messageResponse.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthLoginResponse> call, Throwable t) {

            }
        });
    }

    public interface IResponse<T>{
        void onResponse(T data);
    }
}
