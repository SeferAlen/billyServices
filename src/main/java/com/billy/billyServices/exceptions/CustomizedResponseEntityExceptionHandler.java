package com.billy.billyServices.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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
    private static final String EXCEPTION_NULL = "exception must not be null";
    private static final String VALIDATION_FAILED = "Validation failed";
    private static final String TOKEN_EXPIRED = "Token expired";
    private static final String TOKEN_UNSUPPORTED = "Unsupported token";
    private static final String TOKEN_MALFORMED = "Malformed token";
    private static final String TOKEN_SIGNATURE_INVALID = "Token signature not valid";
    private static final String TOKEN_ILLEGAL = "Illegal token";
    private static final String REQUEST_PARAM_NULL = "Parameter is null";
    private static final String SERVICE_ERROR_DETAILS = "Please contact us with about this";
    private static final String DOUBLE_DOT = ": ";
    private static final String COMA_SEPARATED = ", ";

    private final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    /**
     * Method for handling validation exception (can't have @ExceptionHandler(MethodArgumentNotValidException.class) because
     * of ambiguity with Spring ResponseEntityExceptionHandler)
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

        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + DOUBLE_DOT + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + DOUBLE_DOT + error.getDefaultMessage());
        }

        final String listString = String.join(COMA_SEPARATED, errors);

        return new ResponseEntity<>(new ErrorDetails(nowFormatted, VALIDATION_FAILED, listString), HTTP_BAD_REQUEST);
    }

    /**
     * Method for handling expired JWT exception
     *
     * @param ex {@link ExpiredJwtException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredJwtException(final ExpiredJwtException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_EXPIRED, ex.getLocalizedMessage()),
                HTTP_UNAUTHORIZED
        );
    }

    /**
     * Method for handling unsupported JWT exception
     *
     * @param ex {@link UnsupportedJwtException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(UnsupportedJwtException.class)
    protected ResponseEntity<Object> handleUnsupportedJwtException(final UnsupportedJwtException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_UNSUPPORTED, ex.getLocalizedMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    /**
     * Method for handling malformed JWT exception
     *
     * @param ex {@link MalformedJwtException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(MalformedJwtException.class)
    protected ResponseEntity<Object> handleMalformedJwtException(final MalformedJwtException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_MALFORMED, ex.getLocalizedMessage()),
                HTTP_UNAUTHORIZED
        );
    }

    /**
     * Method for handling signature JWT exception
     *
     * @param ex {@link SignatureException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<Object> handleSignatureException(final SignatureException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_SIGNATURE_INVALID, ex.getLocalizedMessage()),
                HTTP_UNAUTHORIZED
        );
    }

    /**
     * Method for handling invalid claim JWT exception
     *
     * @param ex {@link InvalidClaimException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(InvalidClaimException.class)
    protected ResponseEntity<Object> handleInvalidClaimException(final InvalidClaimException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_ILLEGAL, ex.getLocalizedMessage()),
                HTTP_UNAUTHORIZED
        );
    }

    /**
     * Method for handling illegal argument JWT exception
     *
     * @param ex {@link IllegalArgumentException} the trowed exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        return new ResponseEntity<>(new ErrorDetails(new Date().toString(), TOKEN_ILLEGAL, ex.getLocalizedMessage()),
                HTTP_UNAUTHORIZED
        );
    }

    /**
     * Method for default exception handling
     *
     * @param ex       {@link MethodArgumentNotValidException} the thrown exception
     * @return {@link ResponseEntity} with {@link ErrorDetails} as body and {@link HttpStatus} http status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultExceptionHandle(final Exception ex) {
        Objects.requireNonNull(ex, EXCEPTION_NULL);

        logger.error(ex.getLocalizedMessage());

        if (ex instanceof NullPointerException) {
            return new ResponseEntity(REQUEST_PARAM_NULL, HTTP_INTERNAL_ERROR);
        }
        return new ResponseEntity(SERVICE_ERROR_DETAILS, HTTP_INTERNAL_ERROR);
    }
}
