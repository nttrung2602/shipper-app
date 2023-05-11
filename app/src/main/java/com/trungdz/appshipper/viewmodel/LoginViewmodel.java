package com.trungdz.appshipper.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.model.InfoLogin;
import com.trungdz.appshipper.repository.AuthRepository;
import com.trungdz.appshipper.response.AuthLoginResponse;
import com.trungdz.appshipper.storage.MySharedPreference;

public class LoginViewmodel extends ViewModel {

    MutableLiveData<Boolean> loggedInSuccessfully = new MutableLiveData<>();
    AuthRepository authRepository = new AuthRepository();

    MutableLiveData<String> msg = new MutableLiveData<>();

    public MutableLiveData<String> getMsg() {
        return msg;
    }

    public LiveData<Boolean> getLoggedInSuccessfully() {
        return loggedInSuccessfully;
    }

    // Init data for ViewModel
    public LoginViewmodel() {
        loggedInSuccessfully.postValue(false);

    }

    public void login(InfoLogin infoLogin) {
        authRepository.loginRemote(infoLogin, new AuthRepository.IResponse<String>() {
            @Override
            public void onResponse(String data) {
                msg.setValue(data);

                if(msg.getValue().equals("Đăng nhập thành công!")) {
                    loggedInSuccessfully.postValue(true);
                }
            }
        });
    }

}
