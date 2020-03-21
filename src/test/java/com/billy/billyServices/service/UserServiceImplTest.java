package com.billy.billyServices.service;

import com.billy.billyServices.BillyServicesApplication;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.listeners.DBListener;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for UserService
 */
@SpringBootTest(classes = BillyServicesApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    private static final UserCreateStatus STATUS_CREATED = UserCreateStatus.CREATED;
    private static final UserCreateStatus STATUS_ALREADY_EXIST = UserCreateStatus.ALREADY_EXIST;
    private static final UserCreateStatus STATUS_FAILED = UserCreateStatus.FAILED;
    private static final String firstName1 = "Alen";
    private static final String firstName2 = "Pero";
    private static final String firstName3 = "Pajo";
    private static final String lastName1 = "Sefer";
    private static final String lastName2 = "Perić";
    private static final String lastName3 = "Patak";
    private static final String address1 = "alensefer1990@gmail.com";
    private static final String address2 = "peroperic@gmail.com";
    private static final String address3 = "pajoPatak@gmail.com";
    private static final String phone1 = "alensefer1990@gmail.com";
    private static final String phone2 = "peroperic@gmail.com";
    private static final String phone3 = "pajoPatak@gmail.com";
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";

    final BillyUser user1 = new BillyUser(firstName1, lastName1, address1, phone1);
    final BillyUser user2 = new BillyUser(firstName2, lastName2, address2, phone2);
    final BillyUser user3 = new BillyUser(firstName3, lastName3, address3, phone3);

    final Role roleUser = new Role(ROLE_USER);

    final Login login1 = new Login(firstName1, firstName1, user1);
    final Login login2 = new Login(firstName2, firstName2, user2);
    final List<BillyUser> users = new ArrayList<>();
    final List<Login> logins = new ArrayList<>();

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private LoginRepository loginRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private DBListener dbListener;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        user1.setBilly_userID(UUID.randomUUID());
        user2.setBilly_userID(UUID.randomUUID());
        user3.setBilly_userID(UUID.randomUUID());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        logins.add(login1);
    }

    /**
     * Test create user.
     */
    @Test
    public void testCreateUser() {
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(loginRepository.findByUsername(firstName1)).thenReturn(login1);
        Mockito.when(loginRepository.findByUsername(firstName2)).thenReturn(login2);
        Mockito.when(loginRepository.findByUsername(ROLE_ADMIN)).thenReturn(login1);
        Mockito.when(loginRepository.findAll()).thenReturn(logins);

        Assert.assertEquals(STATUS_ALREADY_EXIST, userService.createUser(user1, login1));
        Assert.assertEquals(STATUS_FAILED, userService.createUser(user2, login2));

        Mockito.when(roleRepository.findByName(ROLE_USER)).thenReturn(roleUser);

        Assert.assertEquals(STATUS_CREATED, userService.createUser(user2, login2));

        verify(roleRepository, times(3)).findByName(ROLE_USER);
        verify(loginRepository, times(3)).findAll();
    }

    /**
     * Test get users.
     */
    @Test
    public void testGetUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(loginRepository.findByUsername(firstName1)).thenReturn(login1);
        Mockito.when(loginRepository.findByUsername(firstName2)).thenReturn(login2);
        Mockito.when(loginRepository.findByUsername(ROLE_ADMIN)).thenReturn(login1);
        Mockito.when(loginRepository.findAll()).thenReturn(logins);

        Assert.assertEquals(3, userService.getUsers().getUsers().size());

        verify(userRepository, times(1)).findAll();
        verify(loginRepository, times(1)).findByUsername(ROLE_ADMIN);
    }
}
