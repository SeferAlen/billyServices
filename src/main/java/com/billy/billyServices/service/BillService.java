package com.billy.billyServices.service;

import com.billy.billyServices.enums.BillCreateStatus;
import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillResponse;
import com.billy.billyServices.model.GetBillsResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BillService {

    BillCreateStatus create(final Bill bill, final String username);
    GetBillsResult getBill(final UUID uuid);
    GetBillsResult getBills(final String username);
    GetBillsResult getBills(final UUID uuid);
}
