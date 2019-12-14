package com.billy.billyServices.utility;

import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillResponse;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.BillyUserResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for converting between types
 */
public class ConverterUtil {
    private static final String DATE_FORMAT = "MMM, YYYY";

    /**
     * Method for converting {@link List<Bill>} to {@link List<BillResponse>}
     *
     * @param bills {@link List<Bill>} the bills
     * @return {@link List<BillResponse>} the bills converted to {@link BillResponse}
     */
    public static List<BillResponse> fromBillToBillResponse(final List<Bill> bills) {

        final List<BillResponse> billResponse = new ArrayList<>();
        for (final Bill bill: bills) {
            billResponse.add(new BillResponse(bill.getBillID(), bill.getTotal(), new SimpleDateFormat(DATE_FORMAT).format(bill.getDate())));
        }

        return billResponse;
    }

    /**
     * Method for converting {@link List<BillyUser>} to {@link List<BillyUserResponse>}
     *
     * @param users {@link List<BillyUser>} the users
     * @return {@link List<BillyUser>} the users converted to {@link BillResponse}
     */
    public static List<BillyUserResponse> fromBillyUserToBillyUserResponse(final List<BillyUser> users) {

        final List<BillyUserResponse> billyUserResponses = new ArrayList<>();
        for (final BillyUser user: users) {
            billyUserResponses.add(new BillyUserResponse(
                    user.getBilly_userID(),
                    user.getFirst_name(),
                    user.getLast_name(),
                    user.getAddress(),
                    user.getPhone())
            );
        }

        return billyUserResponses;
    }
}
