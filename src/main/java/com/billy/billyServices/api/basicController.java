package com.billy.billyServices.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    protected static final String NOT_NULL = " must not be null";
    protected static final String MIN_SIZE = " min length is required to be ";
    protected static final String EMPTY_SPACE = " ";
    protected static final int EIGHT = 8;

    /**
     * Method for returning response when one required parameter is null
     *
     * @param param {@link String} the param required not to be null
     * @return {@link ResponseEntity} the response entity with body containing message and Http status
     */
    protected ResponseEntity<?> nullParameter(final String param) {

        return new ResponseEntity<>(param + NOT_NULL, HTTP_BAD_REQUEST);
    }

    /**
     * Method for returning response when one required parameter length is below minimum required
     *
     * @param param {@link String} the param required to be specific length
     * @return {@link ResponseEntity} the response entity with body containing message and Http status
     */
    protected ResponseEntity<?> minLength(final String param) {

        return new ResponseEntity<>(param + MIN_SIZE + EIGHT, HTTP_BAD_REQUEST);
    }
}
