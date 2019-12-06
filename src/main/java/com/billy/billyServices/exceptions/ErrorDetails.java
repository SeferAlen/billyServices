package com.billy.billyServices.exceptions;

import java.util.Date;

/**
 * Class for containing error's in customized exceptions
 */
public class ErrorDetails {

    private String dateTime;
    private String message;
    private String details;

    public ErrorDetails(String dateTime, String message, String details) {
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
