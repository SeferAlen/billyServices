package com.billy.billyServices.exceptions;

/**
 * Class for containing error's in customized exceptions
 */
public class ErrorDetails {

    private String dateTime;
    private String message;
    private String details;

    /**
     * Instantiates a new Error details.
     *
     * @param dateTime the date time
     * @param message  the message
     * @param details  the details
     */
    public ErrorDetails(String dateTime, String message, String details) {
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets date time.
     *
     * @param dateTime the date time
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
