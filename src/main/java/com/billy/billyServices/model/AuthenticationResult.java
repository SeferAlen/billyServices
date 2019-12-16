package com.billy.billyServices.model;

import com.billy.billyServices.enums.AuthenticationStatus;

/**
 * Class for result of authentication action
 */
public class AuthenticationResult {

    private AuthenticationStatus status;
    private String token;

    /**
     * Instantiates a new Authentication result.
     *
     * @param status the status
     * @param token  the token
     */
    public AuthenticationResult(AuthenticationStatus status, String token) {
        this.status = status;
        this.token = token;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public AuthenticationStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
