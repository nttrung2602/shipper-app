package com.trungdz.appshipper.response;

import com.google.gson.annotations.SerializedName;
import com.trungdz.appshipper.service.model.UserInfo;


public class AuthLoginResponse {

    private String token;
    private String message;
    private String refreshToken;
    private int expireTimeToken;
    private int expireTimeRefreshToken;


    @SerializedName("shipperInfo")
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpireTimeToken() {
        return expireTimeToken;
    }

    public void setExpireTimeToken(int expireTimeToken) {
        this.expireTimeToken = expireTimeToken;
    }

    public int getExpireTimeRefreshToken() {
        return expireTimeRefreshToken;
    }

    public void setExpireTimeRefreshToken(int expireTimeRefreshToken) {
        this.expireTimeRefreshToken = expireTimeRefreshToken;
    }
}
