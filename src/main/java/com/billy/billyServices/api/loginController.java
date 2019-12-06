package com.billy.billyServices.api;

import com.billy.billyServices.model.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for user login
 */
@RestController
@RequestMapping("/login")
public class loginController extends basicController {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGGING = "User logging: ";

    /**
     * Login endpoint for user login and token generation
     *
     * @param login {@link Login} the login data
     * @return {@link ResponseEntity} the response entity with body containing token (if request is valid) and Http status
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody@Valid final Login login) {

        return new ResponseEntity<>("", HTTP_OK);
    }
}
