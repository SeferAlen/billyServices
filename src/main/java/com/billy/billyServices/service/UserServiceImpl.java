package com.billy.billyServices.service;

import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Role;

import java.util.Objects;

/**
 * Service for users and user related actions
 */
public class UserServiceImpl implements UserService {
    private static final UserCreateStatus CREATED = UserCreateStatus.CREATED;
    private static final UserCreateStatus ALREADY_EXIST = UserCreateStatus.ALREADY_EXIST;
    private static final UserCreateStatus FAILED = UserCreateStatus.FAILED;
    private static final String USER_NULL = "User must not be null";
    private static final String PASSWORD_NULL = "Password must not be null";
    private static final String ROLE_NULL = "Role must not be null";

    /**
     * Method for creating new user
     *
     * @param billyUser {@link BillyUser} the billyUser to be created
     * @param password  {@link String}    the password
     * @param role      {@link Role}      the role
     * @return {@link UserCreateStatus} the status representing user creation action result
     */
    public UserCreateStatus createUser(final BillyUser billyUser, final String password, final Role role) {
        Objects.requireNonNull(billyUser, USER_NULL);
        Objects.requireNonNull(password, PASSWORD_NULL);
        Objects.requireNonNull(role, ROLE_NULL);

        return FAILED;
    }
}
