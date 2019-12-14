package com.billy.billyServices.model;

import com.billy.billyServices.enums.GetBillsStatus;

import java.util.List;

public class GetBillsResult {

    private List<BillResponse> bills;
    private GetBillsStatus status;

    public GetBillsResult(GetBillsStatus status) {
        this.status = status;
    }

    public GetBillsResult(List<BillResponse> bills, GetBillsStatus status) {
        this.bills = bills;
        this.status = status;
    }

    public List<BillResponse> getBills() {
        return bills;
    }

    public void setBills(List<BillResponse> bills) {
        this.bills = bills;
    }

    public GetBillsStatus getStatus() {
        return status;
    }

    public void setStatus(GetBillsStatus status) {
        this.status = status;
    }
}
