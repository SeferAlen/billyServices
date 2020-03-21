package com.billy.billyServices.utility;

import com.billy.billyServices.config.AdminUUID;
import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillResponse;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.BillyUserResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Utility methods for converting between types
 */
public class ConverterUtil {
    private static final String DATE_FORMAT = "MMM, YYYY";

    /**
     * Prevent instance creation
     */
    private ConverterUtil() {
    }

    /**
     * Method for converting {@link List<BillyUser>} to {@link List<BillyUserResponse>}
     *
     * @param billyUserList {@link List<BillyUser>} the users
     * @return {@link List<BillyUserResponse>} the users converted to {@link BillResponse}
     */
    public static List<BillyUserResponse> billyUserResponse(final List<BillyUser> billyUserList) {

        return listConverter(ConverterUtil::fromBillyUserToBillyUserResponse, billyUserList);
    }

    /**
     * Method for converting {@link List<Bill>} to {@link List<BillResponse>}
     *
     * @param billsList {@link List<Bill>} the bills
     * @return {@link List<BillResponse>} the bills converted to {@link BillResponse}
     */
    public static List<BillResponse> billUserResponse(final List<Bill> billsList) {

        return listConverter(ConverterUtil::fromBillToBillResponse, billsList);
    }

    /**
     * Generic method for list conversion
     *
     * @param converterFunction {@link Function<List, T>} functional interface implementation function for conversion
     * @param from {@link List} the list to be converted
     * @return {@link List<T>} the converted List containing items of type T
     */
    private static <T> List<T> listConverter(final Function<List, List<T>> converterFunction, final List from) {

        return converterFunction.apply(from);
    }

    /**
     * Method for converting {@link List<Bill>} to {@link List<BillResponse>}
     *
     * @param bills {@link List<Bill>} the bills
     * @return {@link List<BillResponse>} the bills converted to {@link BillResponse}
     */
    private static List<BillResponse> fromBillToBillResponse(final List<Bill> bills) {

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
     * @return {@link List<BillyUserResponse>} the users converted to {@link BillResponse}
     */
    private static List<BillyUserResponse> fromBillyUserToBillyUserResponse(final List<BillyUser> users) {

        final List<BillyUserResponse> billyUserResponses = new ArrayList<>();
        final AdminUUID admin = AdminUUID.getInstance();
        for (final BillyUser user: users) {
            if (!user.getBilly_userID().equals(admin.getAdminUUID())) {
                billyUserResponses.add(new BillyUserResponse(
                        user.getBilly_userID(),
                        user.getFirst_name(),
                        user.getLast_name(),
                        user.getAddress(),
                        user.getPhone())
                );
            } else {
                // skip Admin
            }
        }

        return billyUserResponses;
    }
}
