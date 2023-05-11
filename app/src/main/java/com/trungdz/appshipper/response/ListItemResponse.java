package com.trungdz.appshipper.response;

import com.trungdz.appshipper.model.Item;

import java.util.List;

public class ListItemResponse {
    public List<Item> getItemList() {
        return itemList;
    }

    List<Item> itemList;
}
