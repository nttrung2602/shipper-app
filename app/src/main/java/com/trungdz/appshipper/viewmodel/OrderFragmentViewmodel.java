package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderFragmentViewmodel extends ViewModel {

    OrdersRepository ordersRepository = new OrdersRepository();
    MutableLiveData<List<Order>> unconfirmedOrderList = new MutableLiveData<>();

    public MutableLiveData<List<Order>> getUnconfirmedOrderList() {
        return unconfirmedOrderList;
    }

    public OrderFragmentViewmodel(){
        getAllOrderForShipper();
        unconfirmedOrderList.postValue(new ArrayList<>());
    }
    public void getAllOrderForShipper() {
        ordersRepository.getAllOrderForShipper(new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                unconfirmedOrderList.getValue().clear();
                unconfirmedOrderList.postValue(data);
            }
        });
    }
}
