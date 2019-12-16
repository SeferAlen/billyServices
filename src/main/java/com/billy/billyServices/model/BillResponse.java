package com.billy.billyServices.model;

import java.util.UUID;

/**
 * Class for response of get bill(s) action
 */
public class BillResponse {

    private UUID billId;
    private float total;
    private String date;

    /**
     * Instantiates a new Bill response.
     *
     * @param billId the bill id
     * @param total  the total
     * @param date   the date
     */
    public BillResponse(UUID billId, float total, String date) {
        this.billId = billId;
        this.total = total;
        this.date = date;
    }

    /**
     * Gets bill id.
     *
     * @return the bill id
     */
    public UUID getBillId() {
        return billId;
    }

    /**
     * Sets bill id.
     *
     * @param billId the bill id
     */
    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
