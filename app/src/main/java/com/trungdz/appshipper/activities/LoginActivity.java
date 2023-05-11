package com.trungdz.appshipper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trungdz.appshipper.databinding.ActivityLoginBinding;
import com.trungdz.appshipper.model.InfoLogin;
import com.trungdz.appshipper.repository.AuthRepository;
import com.trungdz.appshipper.response.AuthLoginResponse;
import com.trungdz.appshipper.storage.MySharedPreference;
import com.trungdz.appshipper.viewmodel.LoginViewmodel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    LoginViewmodel loginViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // Get views in layout
        View view = binding.getRoot();
        setContentView(view);

        loginViewmodel = ViewModelProviders.of(this).get(LoginViewmodel.class);

        // init SharedPreference for global app
        MySharedPreference.init(getApplicationContext());

        initHanleEvent();
        initObserver();


    }

    void initHanleEvent() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.edtAccount.getText().toString();
                String password=binding.edtPassword.getText().toString();

                InfoLogin infoLogin = new InfoLogin(username, password);
                loginViewmodel.login(infoLogin);
            }
        });
    }

    void startMainActivity(){
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
    }

    void initObserver() {
        loginViewmodel.getLoggedInSuccessfully().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if(isSuccess){
                    startMainActivity();
                }
            }
        });

        loginViewmodel.getMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}