package com.trungdz.appshipper.response;

import com.trungdz.appshipper.service.model.Order;

import java.util.ArrayList;

public class UnconfirmedOrdersForShipperResponse {
    ArrayList<Order> itemList=new ArrayList<>();

    public ArrayList<Order> getItemList() {
        return itemList;
    }
}
