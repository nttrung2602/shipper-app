package com.trungdz.appshipper.service.model;

public class Item {
    private int id_item;
    private int id_order;
    private int quantity;
    private String image;
    private String name;
    private int price;
    private int amount;

    public int getId_item() {
        return id_item;
    }

    public int getId_order() {
        return id_order;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
