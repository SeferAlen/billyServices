package com.billy.billyServices.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class for customized exceptions
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final HttpStatus HTTP_UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    private static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus HTTP_INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private static final String EXCEPTION_NULL = "Exception must not be null";
    private static final String EXCEPTION_UNEXPECTED = "Unexpected exception or error";
    private static final String VALIDATION_FAILED = "Validation failed";
    private static final String TOKEN_EXPIRED = "Token expired";
    private static final String TOKEN_UNSUPPORTED = "Unsupported token";
    private static final String TOKEN_MALFORMED = "Malformed token";
    private static final String TOKEN_SIGNATURE_INVALID = "Token signature not valid";
    private static final String TOKEN_ILLEGAL = "Illegal token";
    private static final String WRONG_PASSWORD = "Wrong password";
    private static final String REQUEST_PARAM_NULL = "Request parameter is null";
    private static final String SERVICE_ERROR_MESSAGE = "Service error";
    private static final String SERVICE_ERROR_DETAILS = "Please contact us with about this";

    /**
     * Method for handling validation exception
     *
     * @param ex {@link MethodArgumentNotValidException} the thrown exception
     * @param headers {@link HttpHeaders}                the http headers
     * @param status {@link HttpStatus}                  the http status
     * @param request {@link WebRequest}                 the web request
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        final Date now = new Date();
        final String nowFormatted = new SimpleDateFormat(DATE_FORMAT).format(now);

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        final String listString = String.join(", ", errors);

        return new ResponseEntity<>(new ErrorDetails(nowFormatted, VALIDATION_FAILED, listString), HTTP_BAD_REQUEST);
    }

    /**
     * Method for default exception handling
     *
     * @param ex       {@link MethodArgumentNotValidException} the thrown exception
     * @param request  {@link HttpHeaders}                     the http headers
     * @param response {@link HttpStatus}                      the http status
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultExceptionHandle(final Exception ex,
                                                         final HttpServletRequest request,
                                                         final HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            new ResponseEntity(REQUEST_PARAM_NULL, HTTP_INTERNAL_ERROR);
        }
        return new ResponseEntity(SERVICE_ERROR_DETAILS, HTTP_INTERNAL_ERROR);
    }
}
