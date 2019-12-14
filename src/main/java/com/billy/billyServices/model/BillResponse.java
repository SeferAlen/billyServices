package com.billy.billyServices.model;

import java.util.UUID;

public class BillResponse {

    private UUID billId;
    private float total;
    private String date;

    public BillResponse(UUID billId, float total, String date) {
        this.billId = billId;
        this.total = total;
        this.date = date;
    }

    public UUID getBillId() {
        return billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
