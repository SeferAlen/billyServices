package com.billy.billyServices.model;

import javax.validation.Valid;

public class Register {

    @Valid
    private BillyUser billyUser;
    @Valid
    private Login login;

    public Register(@Valid BillyUser billyUser, @Valid Login login) {
        this.billyUser = billyUser;
        this.login = login;
    }

    public BillyUser getBillyUser() {
        return billyUser;
    }

    public void setBillyUser(BillyUser billyUser) {
        this.billyUser = billyUser;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
