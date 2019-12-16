package com.billy.billyServices.model;

import java.util.UUID;

/**
 * Class for response of get user(s) action
 */
public class BillyUserResponse {

    private UUID billy_userID;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;

    /**
     * Instantiates a new Billy user response.
     *
     * @param billy_userID the billy user id
     * @param first_name   the first name
     * @param last_name    the last name
     * @param address      the address
     * @param phone        the phone
     */
    public BillyUserResponse(UUID billy_userID, String first_name, String last_name, String address, String phone) {
        this.billy_userID = billy_userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Gets billy user id.
     *
     * @return the billy user id
     */
    public UUID getBilly_userID() {
        return billy_userID;
    }

    /**
     * Sets billy user id.
     *
     * @param billy_userID the billy user id
     */
    public void setBilly_userID(UUID billy_userID) {
        this.billy_userID = billy_userID;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
