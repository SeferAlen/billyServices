package com.billy.billyServices.api;

import com.billy.billyServices.enums.AuthorizationStatus;
import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.InvalidClaimException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract basic REST controller
 */
public abstract class basicController {
    protected static final HttpStatus HTTP_OK = HttpStatus.OK;
    protected static final HttpStatus HTTP_CREATED = HttpStatus.CREATED;
    protected static final HttpStatus HTTP_NO_CONTENT = HttpStatus.NO_CONTENT;
    protected static final HttpStatus HTTP_ACCEPTED = HttpStatus.ACCEPTED;
    protected static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    protected static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    protected static final HttpStatus HTTP_INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    protected static final String EMPTY_SPACE = " ";
    protected static final String EMPTY_STRING = "";
    protected static final String SERVER_ERROR_RESPONSE = "Server error, please contacts us";
    protected static final String ROLE_ADMIN = "Admin";
    protected static final String ROLE_USER = "User";
    protected static final String TOKEN_EXPIRED = "Token expired";
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
    private AuthorizationService authorizationService;

    private final Logger logger = LoggerFactory.getLogger(basicController.class);

    /**
     * Method for authorization across controllers
     *
     * @param auth {@link String}                    the Authorization header
     * @param requiredRoleNames {@link List<String>} the list of required roles
     * @return {@link AuthorizationResult} the response containing {@link ResponseEntity} with body (if and only if Authorization status is UNAUTHORIZED)
     * and Authorization status
     */
    protected AuthorizationResult authorize(final String auth, final List<String> requiredRoleNames) {

        try{
            final TokenStatus tokenStatus = authorizationService.checkToken(auth);

            switch (tokenStatus) {
                case EXPIRED:
                    return new AuthorizationResult(new ResponseEntity<>(TOKEN_EXPIRED, HTTP_BAD_REQUEST),
                            STATUS_UNAUTHORIZED);

                case INVALID_FORMAT:
                    return new AuthorizationResult(new ResponseEntity<>(EMPTY_SPACE, HTTP_BAD_REQUEST),
                            STATUS_UNAUTHORIZED);

                case OK: {
                    final String token = auth.substring(auth.indexOf(EMPTY_SPACE));
                    return authorizationService.authorize(token, requiredRoleNames);
                }

                default: return new AuthorizationResult(new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR),
                        STATUS_UNAUTHORIZED);
            }
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            if (e instanceof ExpiredJwtException || e instanceof UnsupportedJwtException || e instanceof MalformedJwtException ||
                e instanceof SignatureException || e instanceof InvalidClaimException || e instanceof IllegalArgumentException) throw e;

            return new AuthorizationResult(new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR),
                    STATUS_UNAUTHORIZED);
        }
    }
}
