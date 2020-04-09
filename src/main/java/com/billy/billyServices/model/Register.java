package com.billy.billyServices.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for post new user register
 */
public class Register {

    @Valid
    @NotNull
    private BillyUser billyUser;
    @Valid
    @NotNull
    private Login login;

    /**
     * Instantiates a new Register.
     *
     * @param billyUser the billy user
     * @param login     the login
     */
    public Register(@Valid BillyUser billyUser, @Valid Login login) {
        this.billyUser = billyUser;
        this.login = login;
    }

    /**
     * Gets billy user.
     *
     * @return the billy user
     */
    public BillyUser getBillyUser() {
        return billyUser;
    }

    /**
     * Sets billy user.
     *
     * @param billyUser the billy user
     */
    public void setBillyUser(BillyUser billyUser) {
        this.billyUser = billyUser;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(Login login) {
        this.login = login;
    }
}
