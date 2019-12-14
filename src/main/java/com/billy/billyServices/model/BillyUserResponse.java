package com.billy.billyServices.model;

import java.util.UUID;

public class BillyUserResponse {

    private UUID billy_userID;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;

    public BillyUserResponse(UUID billy_userID, String first_name, String last_name, String address, String phone) {
        this.billy_userID = billy_userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
    }

    public UUID getBilly_userID() {
        return billy_userID;
    }

    public void setBilly_userID(UUID billy_userID) {
        this.billy_userID = billy_userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
