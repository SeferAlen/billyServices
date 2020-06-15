package com.billy.billyServices.service;

import com.billy.billyServices.enums.BillCreateStatus;
import com.billy.billyServices.enums.GetBillsStatus;
import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.BillResponse;
import com.billy.billyServices.model.GetBillsResult;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.repository.BillRepository;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.repository.UserRepository;
import com.billy.billyServices.utility.ConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for bills and bills related actions
 */
@Service
public class BillServiceImpl implements BillService {
    private static final BillCreateStatus STATUS_CREATED = BillCreateStatus.CREATED;
    private static final BillCreateStatus STATUS_FAILED = BillCreateStatus.FAILED;
    private static final GetBillsStatus STATUS_NO_USERNAME = GetBillsStatus.USERNAME_NO_EXIST;
    private static final GetBillsStatus STATUS_NO_BILL = GetBillsStatus.BILL_NO_EXIST;
    private static final GetBillsStatus STATUS_BILLS_OK = GetBillsStatus.OK;
    private static final GetBillsStatus STATUS_BILLS_FAILED = GetBillsStatus.FAILED;
    private static final String BILL_NULL = "bill must not be null";
    private static final String UUID_NULL = "uuid must not be null";
    private static final String USERNAME_NULL = "username must not be null";

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(BillService.class);

    /**
     * Method for creating new bill
     *
     * @param bill     {@link Bill}   the bill
     * @param username {@link String} the username
     * @return {@link BillCreateStatus} the status representing bill creation action result
     */
    public BillCreateStatus create(final Bill bill, final String username) {
        Objects.requireNonNull(bill, BILL_NULL);
        Objects.requireNonNull(username, USERNAME_NULL);

        try {
            final Login dbLogin = loginRepository.findByUsername(username);
            final BillyUser billyUser = dbLogin.getBillyUser();

            bill.setOwner(billyUser);
            billRepository.save(bill);

            return STATUS_CREATED;
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return STATUS_FAILED;
        }
    }

    /**
     * Method for getting bill
     *
     * @param uuid {@link UUID} the uuid of bill
     * @return {@link GetBillsResult} the object containing {@link ArrayList<BillResponse>} if request valid and {@link GetBillsStatus}
     */
    public GetBillsResult getBill(final UUID uuid) {
        Objects.requireNonNull(uuid, UUID_NULL);

        try {
            final Optional<Bill> bill = billRepository.findById(uuid);

            if (!bill.isPresent()) return new GetBillsResult(STATUS_NO_BILL);
            else {
                final List<Bill> oneBillList = new ArrayList<>();
                oneBillList.add(bill.get());
                return new GetBillsResult(new ArrayList<>(ConverterUtil.billUserResponse(oneBillList)), STATUS_BILLS_OK);
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new GetBillsResult(STATUS_BILLS_FAILED);
        }
    }

    /**
     * Method for getting all bills from user
     *
     * @param username {@link String} the username
     * @return {@link GetBillsResult} the object containing {@link ArrayList<BillResponse>} if request valid and {@link GetBillsStatus}
     */
    public GetBillsResult getBills(final String username) {
        Objects.requireNonNull(username, USERNAME_NULL);

        try {
            final Login login = loginRepository.findByUsername(username);
            if (login == null) return new GetBillsResult(STATUS_NO_USERNAME);
            final UUID userID = login.getBillyUser().getBilly_userID();

            final List<Bill> bills = billRepository.findAll().stream()
                                                             .filter(
                                                                bill -> bill.getOwner().getBilly_userID().equals(userID)
                                                             ).collect(Collectors.toList()
                    );

            return new GetBillsResult(new ArrayList<>(ConverterUtil.billUserResponse(bills)), STATUS_BILLS_OK);
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new GetBillsResult(STATUS_BILLS_FAILED);
        }
    }

    /**
     * Method for getting all bills from user
     *
     * @param uuid {@link UUID} the user id
     * @return {@link GetBillsResult} the object containing {@link ArrayList<BillResponse>} if request valid and {@link GetBillsStatus}
     */
    public GetBillsResult getBills(final UUID uuid) {
        Objects.requireNonNull(uuid, UUID_NULL);

        try {
            final Optional<BillyUser> billyUser = userRepository.findById(uuid);

            if (billyUser.isPresent()) {

                final Login login = loginRepository.findByBillyUser(billyUser.get());
                if (login == null) return new GetBillsResult(STATUS_NO_USERNAME);
                final UUID userID = login.getBillyUser().getBilly_userID();

                final List<Bill> bills = billRepository.findAll().stream()
                        .filter(
                                bill -> bill.getOwner().getBilly_userID().equals(userID)
                        ).collect(Collectors.toList());

                return new GetBillsResult(new ArrayList<>(ConverterUtil.billUserResponse(bills)), STATUS_BILLS_OK);
            } else {
                return new GetBillsResult(STATUS_BILLS_FAILED);
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new GetBillsResult(STATUS_BILLS_FAILED);
        }
    }
}
