package com.billy.billyServices.model;

/**
 * Class for response after post new password action
 */
public class NewPasswordResponse {

    private String message;

    /**
     * Instantiates a new New password response.
     *
     * @param message the message
     */
    public NewPasswordResponse(String message) {
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
