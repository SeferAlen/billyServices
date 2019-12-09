package com.billy.billyServices.model;

import com.billy.billyServices.enums.AuthorizationStatus;
import org.springframework.http.ResponseEntity;

public class AuthorizationResult {

    private ResponseEntity<?> responseEntity;
    private AuthorizationStatus authorizationStatus;

    public AuthorizationResult(ResponseEntity<?> responseEntity, AuthorizationStatus authorizationStatus) {
        this.responseEntity = responseEntity;
        this.authorizationStatus = authorizationStatus;
    }

    public ResponseEntity<?> getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(ResponseEntity<?> responseEntity) {
        this.responseEntity = responseEntity;
    }

    public AuthorizationStatus getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(AuthorizationStatus authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }
}
