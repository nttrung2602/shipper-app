package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.service.model.Item;
import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.List;

public class OrderDetailFragmentViewmodel extends ViewModel {
    public final static int UNCONFIRMED_ORDERS = 1;
    public final static int ORDERS_IS_SHIPPING = 3;
    Order orderIsBeingShipped;
    MutableLiveData<List<Item>> itemList = new MutableLiveData<>();
    OrdersRepository ordersRepository = new OrdersRepository();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public Order getOrderIsBeingShipped() {
        return orderIsBeingShipped;
    }

    public MutableLiveData<List<Item>> getItemList() {
        return itemList;
    }

    public OrderDetailFragmentViewmodel() {
        getOrdersIsBeingShipped();
    }


    public void getOrdersIsBeingShipped() {
        ordersRepository.getAllOrdersOfAShipperWithStatus(ORDERS_IS_SHIPPING, new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                if (data.size() != 0) {
                    orderIsBeingShipped = data.get(0);

                    ordersRepository.getAllItemInOrder(orderIsBeingShipped.getId_order(), new OrdersRepository.IResponse<List<Item>>() {
                        @Override
                        public void onResponse(List<Item> data) {
                            itemList.postValue(data);
                        }

                        @Override
                        public void onError(String message) {
                            messageResponse.postValue(message);
                        }
                    });
                }
            }

            @Override
            public void onError(String message) {
                messageResponse.postValue(message);
            }
        });
    }
}
