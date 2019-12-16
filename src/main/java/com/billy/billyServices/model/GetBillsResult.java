package com.billy.billyServices.model;

import com.billy.billyServices.enums.GetBillsStatus;

import java.util.List;

/**
 * Class for result of get bill(s) action
 */
public class GetBillsResult {

    private List<BillResponse> bills;
    private GetBillsStatus status;

    /**
     * Instantiates a new Get bills result.
     *
     * @param status the status
     */
    public GetBillsResult(GetBillsStatus status) {
        this.status = status;
    }

    /**
     * Instantiates a new Get bills result.
     *
     * @param bills  the bills
     * @param status the status
     */
    public GetBillsResult(List<BillResponse> bills, GetBillsStatus status) {
        this.bills = bills;
        this.status = status;
    }

    /**
     * Gets bills.
     *
     * @return the bills
     */
    public List<BillResponse> getBills() {
        return bills;
    }

    /**
     * Sets bills.
     *
     * @param bills the bills
     */
    public void setBills(List<BillResponse> bills) {
        this.bills = bills;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public GetBillsStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(GetBillsStatus status) {
        this.status = status;
    }
}
