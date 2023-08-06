package com.trungdz.appshipper.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    public static final String MY_SHARED_PREFERENCE = "MY_SHARED_PREFERENCE";
    public static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    public static final String USER_INFO = "USER_INFO";

    private static MySharedPreference instance; //singleton
    private SharedPreferences mySharedPreferences;

    public static void init(Context context) {
        if (instance == null) {
            instance = new MySharedPreference();
        }
        instance.mySharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCE, context.MODE_PRIVATE);


    }

    // singleton
    public static MySharedPreference getInstance() {

        return instance;
    }

    public void putToken(String accessToken) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public void putUserInfo(String userInfo) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(USER_INFO, userInfo);
        editor.apply();
    }

    public String retrieveUserInfo(){
        return mySharedPreferences.getString(USER_INFO,"");
    }
    public String retrieveToken() {
        return mySharedPreferences.getString(KEY_ACCESS_TOKEN, "");
    }


}
