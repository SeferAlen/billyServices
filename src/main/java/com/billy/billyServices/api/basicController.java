package com.billy.billyServices.api;

import com.billy.billyServices.enums.AuthorizationStatus;
import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract basic REST controller
 */
public abstract class basicController {
    protected static final HttpStatus HTTP_OK = HttpStatus.OK;
    protected static final HttpStatus HTTP_CREATED = HttpStatus.CREATED;
    protected static final HttpStatus HTTP_ACCEPTED = HttpStatus.ACCEPTED;
    protected static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    protected static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    protected static final HttpStatus HTTP_INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";
    protected static final String NOT_NULL = " must not be null";
    protected static final String MIN_SIZE = " min length is required to be ";
    protected static final String EMPTY_SPACE = " ";
    protected static final String SERVER_ERROR_RESPONSE = "Server error, please contacts us";
    protected static final String ROLE_ADMIN = "Admin";
    protected static final String ROLE_USER = "User";
    protected static final int EIGHT = 8;
    private static final String TOKEN_EXPIRED = "Token expired";
    protected static final String UNAUTHORIZED = "Unauthorized";
    protected static final AuthorizationStatus STATUS_AUTHORIZED = AuthorizationStatus.AUTHORIZED;
    protected static final AuthorizationStatus STATUS_UNAUTHORIZED = AuthorizationStatus.UNAUTHORIZED;
    protected static final List<String> ONLY_USER_ROLE = new ArrayList<>();
    protected static final List<String> ONLY_ADMIN_ROLE = new ArrayList<>();
    protected static final List<String> ALL_ROLES = new ArrayList<>();

    public basicController() {
        ONLY_USER_ROLE.add(ROLE_USER);
        ONLY_ADMIN_ROLE.add(ROLE_ADMIN);
        ALL_ROLES.add(ROLE_USER);
        ALL_ROLES.add(ROLE_ADMIN);
    }

    @Autowired
    private RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger(loginController.class);

    protected AuthorizationResult authorize(final String token, final List<String> requiredRoleNames) {

        try{
            final TokenStatus tokenStatus = JwtUtil.validateToken(token);

            switch (tokenStatus) {
                case EXPIRED: return new AuthorizationResult(new ResponseEntity<>(TOKEN_EXPIRED, HTTP_BAD_REQUEST), STATUS_UNAUTHORIZED);

                case OK: {
                    final String userRoleName = JwtUtil.getRoleFromToken(token);
                    final Role userRole = roleRepository.findByName(userRoleName);

                    final List<Role> allowedRoles = new ArrayList<>();
                    for (final String roleName : requiredRoleNames) {
                        final Role role = roleRepository.findByName(roleName);

                        allowedRoles.add(role);
                    }

                    if (allowedRoles.contains(userRole)) return new AuthorizationResult(null, STATUS_AUTHORIZED);
                    else return new AuthorizationResult(new ResponseEntity<>(UNAUTHORIZED, HTTP_UNAUTHORIZED), STATUS_UNAUTHORIZED);
                }

                default: return new AuthorizationResult(new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR), STATUS_UNAUTHORIZED);
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new AuthorizationResult(new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR), STATUS_UNAUTHORIZED);
        }
    }
}
