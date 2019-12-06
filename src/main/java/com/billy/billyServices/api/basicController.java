package com.billy.billyServices.api;

import org.springframework.http.HttpStatus;

/**
 * Abstract basic REST controller
 */
public abstract class basicController {
    protected static final HttpStatus HTTP_OK = HttpStatus.OK;
    protected static final HttpStatus HTTP_CREATED = HttpStatus.CREATED;
    protected static final HttpStatus HTTP_ACCEPTED = HttpStatus.ACCEPTED;
    protected static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    protected static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    protected static final HttpStatus HTTP_INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";
    protected static final String NOT_NULL = " must not be null";
    protected static final String MIN_SIZE = " min length is required to be ";
    protected static final String EMPTY_SPACE = " ";
    protected static final int EIGHT = 8;

}
