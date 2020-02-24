package com.billy.billyServices.service;

import com.billy.billyServices.dao.RoleDbImpl;
import com.billy.billyServices.enums.AuthorizationStatus;
import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.utility.JwtUtil;
import com.billy.billyServices.utility.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service for users authorization and authorization related actions
 */
@Service
public class AuthorizationServiceImp implements AuthorizationService {
    private static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    private static final TokenStatus INVALID_FORMAT_STATUS = TokenStatus.INVALID_FORMAT;
    private static final AuthorizationStatus STATUS_AUTHORIZED = AuthorizationStatus.AUTHORIZED;
    private static final AuthorizationStatus STATUS_UNAUTHORIZED = AuthorizationStatus.UNAUTHORIZED;
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String AUTH_NULL = "auth must not be null";
    private static final String TOKEN_NULL = "token must not be null";
    private static final String ROLE_LIST_NULL = "requiredRoleName must not be null";
    private static final String EMPTY_SPACE = " ";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleDbImpl roleDb;

    private final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    /**
     * Method for check Jwt validity
     *
     * @param auth {@link String} the Authorization header
     * @return {@link TokenStatus} the result of check token validity action
     */
    public TokenStatus checkToken(final String auth) {
        Objects.requireNonNull(auth, AUTH_NULL);

        if (!PatternUtil.authorizationHeaderValid(auth)) return INVALID_FORMAT_STATUS;

        final String token = auth.substring(auth.indexOf(EMPTY_SPACE));
        return JwtUtil.validateToken(token);
    }

    /**
     * Method for user authorization from token and required {@link Role} names
     *
     * @param token             {@link String}       the token
     * @param requiredRoleNames {@link List<String>} the requiredRoleNames containing all {@link Role} names valid for related action
     * @return {@link AuthorizationResult} the response containing {@link ResponseEntity} with body (if and only if Authorization status is UNAUTHORIZED)
     * and Authorization status
     */
    public AuthorizationResult authorize(final String token, final List<String> requiredRoleNames) {
        Objects.requireNonNull(token, TOKEN_NULL);
        Objects.requireNonNull(requiredRoleNames, ROLE_LIST_NULL);

        try {
            final String userRoleName = JwtUtil.getRoleFromToken(token);
            final Role dbRoleJpaRepository = roleRepository.findByName(userRoleName);
            final Role dbRoleSQL = roleDb.findByName(userRoleName);

            final List<String> allowedRoles = new ArrayList<>();
            for (final String roleName : requiredRoleNames) {
                final Role role = roleDb.findByName(roleName);

                allowedRoles.add(role.getName());
            }

            if (allowedRoles.contains(dbRoleJpaRepository.getName())) return new AuthorizationResult.Builder(STATUS_AUTHORIZED).build();
            else return new AuthorizationResult.Builder(STATUS_UNAUTHORIZED).withResponseEntity(new ResponseEntity<>(UNAUTHORIZED, HTTP_UNAUTHORIZED))
                        .build();
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new AuthorizationResult.Builder(STATUS_UNAUTHORIZED)
                    .withResponseEntity(new ResponseEntity<>(UNAUTHORIZED, HTTP_UNAUTHORIZED))
                    .build();
        }
    }

    /**
     * Method to get {@link Role} from Authorization header
     *
     * @param auth {@link String} the Authorization header
     * @return {@link Role} the role
     */
    public Role getRole(final String auth) {
        Objects.requireNonNull(auth, AUTH_NULL);

        try {
            final String token = auth.substring(auth.indexOf(EMPTY_SPACE));
            final String roleName = JwtUtil.getRoleFromToken(token);
            return roleRepository.findByName(roleName);
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            throw e;
        }
    }

    /**
     * Method to get username from Authorization header
     *
     * @param auth {@link String} the Authorization header
     * @return {@link String} the username
     */
    public String getUsername(final String auth) {
        Objects.requireNonNull(auth, AUTH_NULL);

        final String token = auth.substring(auth.indexOf(EMPTY_SPACE));
        return JwtUtil.getUsernameFromToken(token);
    }
}
