package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.service.model.InfoLogin;
import com.trungdz.appshipper.repository.AuthRepository;

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
