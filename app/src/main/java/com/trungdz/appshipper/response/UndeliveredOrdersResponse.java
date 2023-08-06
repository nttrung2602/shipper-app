package com.trungdz.appshipper.response;

import com.trungdz.appshipper.service.model.Order;

import java.util.List;

public class UndeliveredOrdersResponse {
    public List<Order> getOrderList() {
        return orderList;
    }

    List<Order> orderList;


}
