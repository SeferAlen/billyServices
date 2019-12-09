package com.billy.billyServices.service;

import com.billy.billyServices.enums.AuthorizationStatus;
import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.utility.JwtUtil;
import com.billy.billyServices.utility.PatternUtil;
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
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String AUTH_NULL = "auth must not be null";
    private static final String TOKEN_NULL = "token must not be null";
    private static final String ROLE_LIST_NULL = "requiredRoleName must not be null";
    protected static final String EMPTY_SPACE = " ";
    protected static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    protected static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final TokenStatus INVALID_FORMAT_STATUS = TokenStatus.INVALID_FORMAT;
    protected static final AuthorizationStatus STATUS_AUTHORIZED = AuthorizationStatus.AUTHORIZED;
    protected static final AuthorizationStatus STATUS_UNAUTHORIZED = AuthorizationStatus.UNAUTHORIZED;

    @Autowired
    private RoleRepository roleRepository;

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
}
