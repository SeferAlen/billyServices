package com.billy.billyServices.model;

import javax.validation.constraints.NotNull;

public class Token {

    @NotNull
    private String token;

    public Token(@NotNull String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
