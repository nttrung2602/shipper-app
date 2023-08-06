package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.List;

public class HistoryFragmentViewmodel extends ViewModel {
    public final static int ORDERS_WERE_SHIPPED = 4;

    public MutableLiveData<List<Order>> getOrderedList() {
        return orderedList;
    }

    private MutableLiveData<List<Order>> orderedList = new MutableLiveData<>();
    OrdersRepository ordersRepository = new OrdersRepository();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public HistoryFragmentViewmodel() {
        getAllDeliveredOrders();
    }

    void getAllDeliveredOrders() {
        ordersRepository.getAllOrdersOfAShipperWithStatus(ORDERS_WERE_SHIPPED, new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                orderedList.postValue(data);
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }
}
