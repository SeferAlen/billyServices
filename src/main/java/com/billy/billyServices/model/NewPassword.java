package com.billy.billyServices.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class for post new password
 */
public class NewPassword {

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * Instantiates a new New password.
     */
    public NewPassword() {
        // Need for Spring to map Json to POJO
    }

    /**
     * Instantiates a new New password.
     *
     * @param password the password
     */
    public NewPassword(@NotNull String password) {
        this.password = password;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
