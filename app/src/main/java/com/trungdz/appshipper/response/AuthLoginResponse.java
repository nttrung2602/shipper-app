package com.trungdz.appshipper.response;

import com.google.gson.annotations.SerializedName;
import com.trungdz.appshipper.model.UserInfo;


public class AuthLoginResponse {

    private String token;
    private String message;
    private int expireTime;

    @SerializedName("shipperInfo")
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getToken() {
        return token;
    }


    public String getMessage() {
        return message;
    }


    public int getExpireTime() {
        return expireTime;
    }

}
