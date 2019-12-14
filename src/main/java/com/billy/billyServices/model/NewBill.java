package com.billy.billyServices.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewBill {

    @Valid
    private Bill bill;
    @NotNull
    private String username;

    public NewBill() {
    }

    public NewBill(@Valid Bill bill, @NotNull String username) {
        this.bill = bill;
        this.username = username;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
