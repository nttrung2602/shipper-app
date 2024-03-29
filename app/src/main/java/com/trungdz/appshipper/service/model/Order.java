package com.trungdz.appshipper.service.model;

public class Order {
    private int id_order;
    private int delivery_fee;
    private int item_fee;
    private int total;
    private String name_customer;
    private String phone;
    private String description;


    private String address;
    private int status;
    private String time_order;
    private String time_confirm;
    private String name_payment;
    private String time_shipper_receive;
    private String time_shipper_delivered;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime_shipper_receive() {
        return time_shipper_receive;
    }

    public void setTime_shipper_receive(String time_shipper_receive) {
        this.time_shipper_receive = time_shipper_receive;
    }

    public String getTime_shipper_delivered() {
        return time_shipper_delivered;
    }

    public void setTime_shipper_delivered(String time_shipper_delivered) {
        this.time_shipper_delivered = time_shipper_delivered;
    }


    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(int delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public int getItem_fee() {
        return item_fee;
    }

    public void setItem_fee(int item_fee) {
        this.item_fee = item_fee;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime_order() {
        return time_order;
    }

    public void setTime_order(String time_order) {
        this.time_order = time_order;
    }

    public String getTime_confirm() {
        return time_confirm;
    }

    public void setTime_confirm(String time_confirm) {
        this.time_confirm = time_confirm;
    }

    public String getName_payment() {
        return name_payment;
    }

    public void setName_payment(String name_payment) {
        this.name_payment = name_payment;
    }


}
