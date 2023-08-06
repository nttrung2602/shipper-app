package com.trungdz.appshipper.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.List;

public class MainActivityViewmodel extends ViewModel {
    public final static int UNCONFIRMED_ORDERS = 1;
    public final static int ORDERS_IS_SHIPPING = 3;
    public final static int ORDERS_WERE_SHIPPED = 4;

    private MutableLiveData<Boolean> shippingStatus = new MutableLiveData<>(); //Test
    OrdersRepository ordersRepository = new OrdersRepository();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public void setShippingStatus(boolean status) {
        shippingStatus.setValue(status);
    }

    public MutableLiveData<Boolean> getShippingStatus() {
        return shippingStatus;
    }

    public MainActivityViewmodel() {
        checkOrderIsExisting();
    }

    public void checkOrderIsExisting() {
        ordersRepository.getAllOrdersOfAShipperWithStatus(ORDERS_IS_SHIPPING, new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                if (data.size() != 0) {
                    shippingStatus.setValue(true);
                } else {
                    shippingStatus.setValue(false);
                }
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }

    public void receiveOrder(int id_order) {
        ordersRepository.receiveOrder(id_order, new OrdersRepository.IResponse<String>() {
            @Override
            public void onResponse(String data) {
//                if (data == true) {
//                    Log.d("Dang giao don hang o viewModel", "onChanged: ");
//                    shippingStatus.setValue(true); // notify to change UI
//                }
                shippingStatus.setValue(true); // notify to change UI
                messageResponse.postValue(data);
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }

    public void confirmOrder(int id_order) {
        ordersRepository.receiveOrder(id_order, new OrdersRepository.IResponse<String>() {
            @Override
            public void onResponse(String data) {
//                if (data) {
//                    Log.d("TEST UI1", "onResponse: ");
//                    shippingStatus.setValue(false); // notify to change UI
//                }
                shippingStatus.setValue(false);
                messageResponse.postValue(data);
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }
}


