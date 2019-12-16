package com.billy.billyServices.model;

import javax.validation.constraints.NotNull;

/**
 * Class for send token
 */
public class Token {

    @NotNull
    private String token;

    /**
     * Instantiates a new Token.
     *
     * @param token the token
     */
    public Token(@NotNull String token) {
        this.token = token;
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
