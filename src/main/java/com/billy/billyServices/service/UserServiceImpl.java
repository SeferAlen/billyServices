package com.billy.billyServices.service;

import com.billy.billyServices.config.AdminUUID;
import com.billy.billyServices.dao.LoginDb;
import com.billy.billyServices.enums.GetUsersStatus;
import com.billy.billyServices.enums.PasswordChangeStatus;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.GetUsersResult;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.repository.UserRepository;
import com.billy.billyServices.utility.ConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Service for users and user related actions
 */
@Service
public class UserServiceImpl implements UserService {
    private static final UserCreateStatus STATUS_CREATED = UserCreateStatus.CREATED;
    private static final UserCreateStatus STATUS_ALREADY_EXIST = UserCreateStatus.ALREADY_EXIST;
    private static final UserCreateStatus STATUS_FAILED = UserCreateStatus.FAILED;
    private static final PasswordChangeStatus PASSWORD_SAME_STATUS = PasswordChangeStatus.SAME_PASSWORD;
    private static final PasswordChangeStatus PASSWORD_CHANGED_STATUS = PasswordChangeStatus.CHANGED;
    private static final PasswordChangeStatus PASSWORD_CHANGE_FAILED_STATUS = PasswordChangeStatus.FAILED;
    private static final GetUsersStatus NO_USERS = GetUsersStatus.NO_USERS;
    private static final GetUsersStatus GET_USERS_STATUS_FAILED = GetUsersStatus.FAILED;
    private static final GetUsersStatus GET_USERS_STATUS_OK = GetUsersStatus.OK;
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";
    private static final String USER_NULL = "user must not be null";
    private static final String USERNAME_NULL = "username must not be null";
    private static final String LOGIN_NULL = "login must not be null";
    private static final String PASSWORD_NULL = "password must not be null";
    private static final String CREATED = " created";

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

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

        return create(billyUser, login, roleRepository.findByName(ROLE_USER));
    }

    /**
     * Method for creating new admin
     *
     * @param billyUser {@link BillyUser} the billyUser to be created
     * @param login     {@link Login}     the login
     * @return {@link UserCreateStatus} the status representing user creation action result
     */
    public UserCreateStatus createAdmin(final BillyUser billyUser, final Login login) {
        Objects.requireNonNull(billyUser, USER_NULL);
        Objects.requireNonNull(login, LOGIN_NULL);

        final UserCreateStatus adminCreateStatus = create(billyUser, login, roleRepository.findByName(ROLE_ADMIN));
        if (adminCreateStatus == STATUS_CREATED) saveAdminUUID();

        return adminCreateStatus;
    }

    /**
     * Method for changing user password
     *
     * @param username    {@link String}  the login
     * @param newPassword {@link String} the new password
     * @return {@link PasswordChangeStatus} the status representing password change action result
     */
    public PasswordChangeStatus changePassword(final String username, final String newPassword) {
        Objects.requireNonNull(username, USERNAME_NULL);
        Objects.requireNonNull(newPassword, PASSWORD_NULL);

        try {
            final Login userLogin = loginRepository.findByUsername(username);
            final boolean passwordSame = passwordEncoder.matches(newPassword, userLogin.getPassword());
            if (passwordSame) return PASSWORD_SAME_STATUS;

            final String newPasswordHash = passwordEncoder.encode(newPassword);

            userLogin.setPassword(newPasswordHash);
            loginRepository.save(userLogin);

            return PASSWORD_CHANGED_STATUS;
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return PASSWORD_CHANGE_FAILED_STATUS;
        }
    }

    /**
     * Method for getting all users
     *
     * @return {@link GetUsersResult} the object containing {@link ArrayList<BillyUser>} if request valid and {@link GetUsersStatus}
     */
    public GetUsersResult getUsers() {

        try {
            final List<BillyUser> users = userRepository.findAll();

            if (users.isEmpty()) return new GetUsersResult(NO_USERS);
            else return new GetUsersResult(ConverterUtil.billyUserResponse(users), GET_USERS_STATUS_OK);
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new GetUsersResult(GET_USERS_STATUS_FAILED);
        }
    }

    /**
     * Method for creating new user with role
     *
     * @param billyUser {@link BillyUser} the billyUser to be created
     * @param login     {@link Login}     the login
     * @param role      {@link Role}      the role
     * @return {@link UserCreateStatus} the status representing user creation action result
     */
    private UserCreateStatus create(final BillyUser billyUser, final Login login, final Role role) {
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
                loginRoles.add(role);
                login.setRoles(loginRoles);

                loginRepository.save(login);
                logger.info(role.getName() + CREATED);

                return STATUS_CREATED;
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return STATUS_FAILED;
        }
    }

    /**
     * Method to set admin UUID
     *
     */
    private void saveAdminUUID() {

        final AdminUUID adminUUID = AdminUUID.getInstance();
        final Login adminLogin = loginRepository.findByUsername(ROLE_ADMIN);
        adminUUID.setAdminUUID(adminLogin.getBillyUser().getBilly_userID());
    }
}
