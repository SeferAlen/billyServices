package com.billy.billyServices.model;

import java.util.UUID;

/**
 * Class for response of get user(s) action
 */
public class BillyUserResponse {

    private UUID billy_userID;
    private String first_name;
    private String last_name;
    private Address address;
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
    public BillyUserResponse(UUID billy_userID, String first_name, String last_name, Address address, String phone) {
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
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
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

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (!(obj instanceof BillyUserResponse)) return false;

        final BillyUserResponse comparable = (BillyUserResponse) obj;

        return this.billy_userID.equals(comparable.billy_userID) &&
               this.first_name.equals(comparable.first_name) &&
               this.last_name.equals(comparable.last_name) &&
               this.address.equals(comparable.address) &&
               this.phone.equals(comparable.phone);
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + billy_userID.hashCode();
        result = 31 * result + first_name.hashCode();
        result = 31 * result + last_name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
