package com.billy.billyServices.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for post new bill
 */
public class NewBill {

    @Valid
    @NotNull
    private Bill bill;
    @NotNull
    private String username;

    /**
     * Instantiates a new New bill.
     */
    public NewBill() {
    }

    /**
     * Instantiates a new New bill.
     *
     * @param bill     the bill
     * @param username the username
     */
    public NewBill(@Valid Bill bill, @NotNull String username) {
        this.bill = bill;
        this.username = username;
    }

    /**
     * Gets bill.
     *
     * @return the bill
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * Sets bill.
     *
     * @param bill the bill
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
