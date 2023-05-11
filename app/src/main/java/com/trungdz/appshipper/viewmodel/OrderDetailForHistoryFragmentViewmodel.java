package com.trungdz.appshipper.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trungdz.appshipper.model.Item;
import com.trungdz.appshipper.model.Order;
import com.trungdz.appshipper.repository.OrdersRepository;

import java.util.List;

public class OrderDetailForHistoryFragmentViewmodel extends ViewModel {
    public final static int ORDERS_WERE_SHIPPED = 4;
    public final static int UNCONFIRMED_ORDERS = 1;
    public final static int ORDERS_IS_SHIPPING = 3;
    Order shippedOrder;
    MutableLiveData<List<Item>> itemList = new MutableLiveData<>();
    OrdersRepository ordersRepository = new OrdersRepository();

    public Order getShippedOrder() {
        return shippedOrder;
    }

    public MutableLiveData<List<Item>> getItemList() {
        return itemList;
    }

    public OrderDetailForHistoryFragmentViewmodel() {

    }

    public void getShippedOrderDetail(int id_order) {
        ordersRepository.getAllOrdersOfAShipperWithStatus(ORDERS_WERE_SHIPPED, new OrdersRepository.IResponse<List<Order>>() {
            @Override
            public void onResponse(List<Order> data) {
                if (data.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getId_order() == id_order) {
                            shippedOrder = data.get(i);
                            break;
                        }
                    }

                    ordersRepository.getAllItemInOrder(shippedOrder.getId_order(), new OrdersRepository.IResponse<List<Item>>() {
                        @Override
                        public void onResponse(List<Item> data) {
                            itemList.postValue(data);
                        }
                    });
                }
            }
        });
    }
}
