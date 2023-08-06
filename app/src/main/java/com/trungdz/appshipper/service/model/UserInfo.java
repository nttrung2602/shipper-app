package com.trungdz.appshipper.service.model;

public class UserInfo {
    private int id_shipper;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String description;
    private int id_account;
    private int id_shipping_partner;

    public int getId_shipper() {
        return id_shipper;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getId_account() {
        return id_account;
    }

    public int getId_shipping_partner() {
        return id_shipping_partner;
    }

}
