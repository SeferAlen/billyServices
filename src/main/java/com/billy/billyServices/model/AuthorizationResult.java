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
     * Prevents instantiating a new Authorization result.
     *
     */
    private AuthorizationResult() {
    }

    /**
     * Builder class for {@link AuthorizationResult}
     */
    public static class Builder {

        private ResponseEntity<?> responseEntity;
        private AuthorizationStatus authorizationStatus;


        /**
         * Instantiates a new Builder.
         *
         * @param authorizationStatus the authorization status
         */
        public Builder(AuthorizationStatus authorizationStatus) {
            this.authorizationStatus = authorizationStatus;
            this.responseEntity = null;
        }

        /**
         * Add {@link ResponseEntity} to {@link AuthorizationResult}
         *
         */
        public Builder withResponseEntity(final ResponseEntity<?> responseEntity) {
            this.responseEntity = responseEntity;

            return this;
        }

        /**
         * Build instance of {@link AuthorizationResult}
         *
         */
        public AuthorizationResult build() {
            AuthorizationResult authorizationResult = new AuthorizationResult();
            authorizationResult.authorizationStatus = this.authorizationStatus;
            authorizationResult.responseEntity = this.responseEntity;

            return authorizationResult;
        }
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
