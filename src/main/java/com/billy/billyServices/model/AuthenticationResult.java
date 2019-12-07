package com.billy.billyServices.model;

import com.billy.billyServices.enums.AuthenticationStatus;

public class AuthenticationResult {

    private AuthenticationStatus status;
    private String token;

    public AuthenticationResult(AuthenticationStatus status, String token) {
        this.status = status;
        this.token = token;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
