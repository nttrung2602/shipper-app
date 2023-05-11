package com.trungdz.appshipper.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.trungdz.appshipper.storage.MySharedPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        MySharedPreference mySharedPreference = MySharedPreference.getInstance();

        String token = mySharedPreference.retrieveToken();

        Request newRequest = originalRequest.newBuilder()
                .addHeader("access_token", token).build();
        return chain.proceed(newRequest);
    }
}
