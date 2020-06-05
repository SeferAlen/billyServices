package com.billy.billyServices.service;

import com.billy.billyServices.BillyServicesApplication;
import com.billy.billyServices.enums.BillCreateStatus;
import com.billy.billyServices.enums.GetBillsStatus;
import com.billy.billyServices.listeners.DBListener;
import com.billy.billyServices.model.Address;
import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillResponse;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.City;
import com.billy.billyServices.model.GetBillsResult;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.repository.BillRepository;
import com.billy.billyServices.repository.LoginRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;


/**
 * Unit tests for BillService
 */
@SpringBootTest(classes = BillyServicesApplication.class)
@RunWith(SpringRunner.class)
public class BillServiceImplTest {
    private static final String USERNAME = "username";
    private static final String firstName1 = "Alen";
    private static final String firstName2 = "Pero";
    private static final String firstName3 = "Pajo";
    private static final String lastName1 = "Sefer";
    private static final String lastName2 = "Perić";
    private static final String lastName3 = "Patak";
    private static final String city1 = "Bugojno";
    private static final String city2 = "Zagreb";
    private static final String city3 = "New York";
    private static final String street1 = "Kopčić b.b.";
    private static final String street2 = "Zagreb Savska ceska 121";
    private static final String street3 = "New York Brooklyn 32";
    private static final String zipcode1 = "70230";
    private static final String zipcode2 = "16783";
    private static final String zipcode3 = "43234";
    private static final String country1 = "Bosnia and Herzegovina";
    private static final String country2 = "Croatia";
    private static final String country3 = "USA";
    private static final String phone1 = "alensefer1990@gmail.com";
    private static final String phone2 = "peroperic@gmail.com";
    private static final String phone3 = "pajoPatak@gmail.com";
    private static final String PASSWORD = "Password";
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";
    private static final String EMPTY_STRING = "";
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final float TOTAL = 2.42F;
    private static final Date NOW = new Date();
    private static final UUID UUID = java.util.UUID.randomUUID();
    private static final BillCreateStatus STATUS_CREATED = BillCreateStatus.CREATED;
    private static final BillCreateStatus STATUS_FAILED = BillCreateStatus.FAILED;
    private static final GetBillsStatus STATUS_NO_USERNAME = GetBillsStatus.USERNAME_NO_EXIST;
    private static final GetBillsStatus STATUS_NO_BILL = GetBillsStatus.BILL_NO_EXIST;
    private static final GetBillsStatus STATUS_BILLS_OK = GetBillsStatus.OK;
    private static final GetBillsStatus STATUS_BILLS_FAILED = GetBillsStatus.FAILED;
    private static final Address ADDRESS_1 = new Address(firstName1 + lastName1, new City(city1, street1, zipcode1, country1));
    private static final Address ADDRESS_2 = new Address(firstName2 + lastName2, new City(city2, street2, zipcode2, country2));
    private static final Address ADDRESS_3 = new Address(firstName3 + lastName3, new City(city3, street3, zipcode3, country3));


    private final BillyUser user1 = new BillyUser(firstName1, lastName1, ADDRESS_1, phone1);
    private final BillyUser user2 = new BillyUser(firstName2, lastName2, ADDRESS_2, phone2);
    private final BillyUser user3 = new BillyUser(firstName3, lastName3, ADDRESS_3, phone3);

    final Bill bill = new Bill(TOTAL, NOW, user1);

    @Autowired
    private BillService billService;
    @MockBean
    private LoginRepository loginRepository;
    @MockBean
    private BillRepository billRepository;
    @MockBean
    private DBListener dbListener;

    @Test
    public void testCreate() {
        final Login login = new Login(PASSWORD, USERNAME, user1);

        Assert.assertEquals(billService.create(bill, USERNAME), STATUS_FAILED);
        Mockito.when(loginRepository.findByUsername(USERNAME)).thenReturn(login);
        Assert.assertEquals(billService.create(bill, USERNAME), STATUS_CREATED);

        Mockito.verify(billRepository, Mockito.times(ONE)).save(bill);
    }

    @Test
    public void getBill() {

        Assert.assertNull(billService.getBill(UUID).getBills());
        Mockito.when(billRepository.findById(UUID)).thenReturn(java.util.Optional.of(bill));
    }
}
