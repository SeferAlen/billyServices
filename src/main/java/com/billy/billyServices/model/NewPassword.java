package com.billy.billyServices.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewPassword {

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public NewPassword() {
        // Need for Spring to map Json to POJO
    }

    public NewPassword(@NotNull String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
