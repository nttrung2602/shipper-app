package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.trungdz.appshipper.service.model.UserInfo;
import com.trungdz.appshipper.storage.MySharedPreference;

public class ProfileFragmentViewmodel extends ViewModel {

    public UserInfo getUserInfo(){

        MySharedPreference mySharedPreference=MySharedPreference.getInstance();
//        String userInfo=mySharedPreference.retrieveUserInfo();
        UserInfo userInfo= new Gson().fromJson(mySharedPreference.retrieveUserInfo(),UserInfo.class);

        return userInfo;
    }

}
