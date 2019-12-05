package com.billy.billyServices.api;

import com.billy.billyServices.model.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * REST controller for user login
 */
@RestController
@RequestMapping("/login")
public class loginController extends basicController {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGGING = "User logging: ";
    private final Logger logger = LoggerFactory.getLogger(loginController.class);

    /**
     * Login endpoint for user login and token generation
     *
     * @param login {@link Login} the login data
     * @return {@link ResponseEntity} the response entity with body containing token and Http status if request is valid
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody@Valid final Login login) {
        logger.info(LOGGING + login.getUsername());

        if (login.getUsername() == null) {
            return nullParameter(USERNAME);
        } else if (login.getPassword() == null) {
            return nullParameter(PASSWORD);
        } else if (login.getPassword().length() < EIGHT) {
            return minLength(PASSWORD);
        }
        return new ResponseEntity<>(login.getPassword() + login.getUsername(), HTTP_OK);
    }
}
