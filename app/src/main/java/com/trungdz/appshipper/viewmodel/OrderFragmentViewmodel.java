package com.trungdz.appshipper.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderFragmentViewmodel extends ViewModel {

    OrdersRepository ordersRepository = new OrdersRepository();
    private MutableLiveData<List<Order>> unconfirmedOrderList = new MutableLiveData<>();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public MutableLiveData<List<Order>> getUnconfirmedOrderList() {
        return unconfirmedOrderList;
    }

    public OrderFragmentViewmodel() {
        getAllOrderForShipper();
        unconfirmedOrderList.postValue(new ArrayList<>());
    }

    public void getAllOrderForShipper() {
        ordersRepository.getAllOrderForShipper(new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                Log.d("OrderFragmentViewModel", "1");
                unconfirmedOrderList.getValue().clear();
                unconfirmedOrderList.postValue(data);
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }
}
