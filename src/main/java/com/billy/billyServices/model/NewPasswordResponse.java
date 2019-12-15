package com.billy.billyServices.model;

public class NewPasswordResponse {

    private String message;

    public NewPasswordResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
