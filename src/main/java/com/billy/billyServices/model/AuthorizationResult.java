package com.billy.billyServices.model;

import com.billy.billyServices.enums.AuthorizationStatus;
import org.springframework.http.ResponseEntity;

/**
 * Class for result of authorization action
 */
public class AuthorizationResult {

    private ResponseEntity<?> responseEntity;
    private AuthorizationStatus authorizationStatus;

    /**
     * Instantiates a new Authorization result.
     *
     * @param responseEntity      the response entity
     * @param authorizationStatus the authorization status
     */
    public AuthorizationResult(ResponseEntity<?> responseEntity, AuthorizationStatus authorizationStatus) {
        this.responseEntity = responseEntity;
        this.authorizationStatus = authorizationStatus;
    }

    /**
     * Gets response entity.
     *
     * @return the response entity
     */
    public ResponseEntity<?> getResponseEntity() {
        return responseEntity;
    }

    /**
     * Sets response entity.
     *
     * @param responseEntity the response entity
     */
    public void setResponseEntity(ResponseEntity<?> responseEntity) {
        this.responseEntity = responseEntity;
    }

    /**
     * Gets authorization status.
     *
     * @return the authorization status
     */
    public AuthorizationStatus getAuthorizationStatus() {
        return authorizationStatus;
    }

    /**
     * Sets authorization status.
     *
     * @param authorizationStatus the authorization status
     */
    public void setAuthorizationStatus(AuthorizationStatus authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }
}
