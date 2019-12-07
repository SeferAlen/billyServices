package com.billy.billyServices.service;

import com.billy.billyServices.api.loginController;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Service for users and user related actions
 */
@Service
public class UserServiceImpl implements UserService {
    private static final UserCreateStatus STATUS_CREATED = UserCreateStatus.CREATED;
    private static final UserCreateStatus STATUS_ALREADY_EXIST = UserCreateStatus.ALREADY_EXIST;
    private static final UserCreateStatus STATUS_FAILED = UserCreateStatus.FAILED;
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";
    private static final String USER_NULL = "User must not be null";
    private static final String LOGIN_NULL = "Login must not be null";
    private static final String USER_CREATED = "User created";

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(loginController.class);

    /**
     * Method for creating new user
     *
     * @param billyUser {@link BillyUser} the billyUser to be created
     * @param login     {@link Login}     the login
     * @return {@link UserCreateStatus} the status representing user creation action result
     */
    public UserCreateStatus createUser(final BillyUser billyUser, final Login login) {
        Objects.requireNonNull(billyUser, USER_NULL);
        Objects.requireNonNull(login, LOGIN_NULL);

        try {
            if (loginRepository.findAll()
                    .stream()
                    .anyMatch(dbLogin -> dbLogin.getUsername().equals(login.getUsername()))) {
                return STATUS_ALREADY_EXIST;
            } else {
                userRepository.save(billyUser);

                login.setPassword(passwordEncoder.encode(login.getPassword()));
                login.setBillyUser(billyUser);
                final Set<Role> loginRoles = new HashSet<>();
                loginRoles.add(roleRepository.findByName(ROLE_USER));
                login.setRoles(loginRoles);

                loginRepository.save(login);

                logger.info(USER_CREATED);

                return STATUS_CREATED;
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return STATUS_FAILED;
        }
    }
}
