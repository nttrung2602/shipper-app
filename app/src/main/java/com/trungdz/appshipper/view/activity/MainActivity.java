package com.trungdz.appshipper.view.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trungdz.appshipper.R;
import com.trungdz.appshipper.databinding.ActivityMainBinding;
import com.trungdz.appshipper.view.fragment.HistoryFragment;
import com.trungdz.appshipper.view.fragment.OrderDetailFragment;
import com.trungdz.appshipper.view.fragment.OrderFragment;
import com.trungdz.appshipper.view.fragment.ProfileFragment;
import com.trungdz.appshipper.viewmodel.MainActivityViewmodel;
import com.trungdz.appshipper.viewmodel.SharedMainActivityViewmodel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainActivityViewmodel activityViewmodel;
    SharedMainActivityViewmodel sharedMainActivityViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init ViewBinding and ViewModel for UI
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activityViewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel.class);
        sharedMainActivityViewmodel = ViewModelProviders.of(this).get(SharedMainActivityViewmodel.class);
        initUI();
        initObserver();

        // SystemDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showAlertDialog();
            }
        });
    }

    public void initObserver() {
        activityViewmodel.getShippingStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean status) {
                if (status) {
                    Log.d("Dang giao don hang", "onChanged: ");
                    getSupportActionBar().setTitle("ĐANG GIAO ĐƠN HÀNG");
                    replaceFragment(new OrderDetailFragment());
                } else {
                    getSupportActionBar().setTitle("ĐƠN HÀNG");
                    replaceFragment(new OrderFragment());
                }
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);

        fragmentTransaction.commit();
    }

    void initUI() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF03DAC5")));
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.order:
                        if (activityViewmodel.getShippingStatus().getValue()) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            getSupportActionBar().setTitle("ĐANG GIAO ĐƠN HÀNG");
                            replaceFragment(new OrderDetailFragment());

                            return true;
                        }
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setTitle("ĐƠN HÀNG");
                        replaceFragment(new OrderFragment());

                        break;
                    case R.id.history:
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setTitle("LỊCH SỬ ĐƠN HÀNG");
                        replaceFragment(new HistoryFragment());

                        break;
                    case R.id.profile:
                        getSupportActionBar().setTitle("THÔNG TIN CÁ NHÂN");
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        replaceFragment(new ProfileFragment());

                        break;
                }
                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                onBackPressed();
                getSupportFragmentManager().popBackStack();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void showAlertDialog() {
        new AlertDialog.Builder(this).setTitle("Cảnh báo").setMessage("Bạn có muốn thoát ứng dụng").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton(android.R.string.cancel, null).show();
    }

}