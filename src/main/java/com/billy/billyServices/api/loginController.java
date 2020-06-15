package com.billy.billyServices.api;

import com.billy.billyServices.model.AuthenticationResult;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Token;
import com.billy.billyServices.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for user login
 */
@RestController
@RequestMapping("/login")
public class loginController extends basicController {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String WRONG_PASSWORD = "Wrong password";

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Login endpoint for user login and token generation
     *
     * @param login {@link Login} the login data
     * @return {@link ResponseEntity} the response entity with body containing token (if request is valid) and Http status
     */
    @CrossOrigin()
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody @Valid final Login login) {

        final AuthenticationResult authenticationResult = authenticationService.generateJwt(login);

        switch (authenticationResult.getStatus()) {
            case USER_NOT_FOUND: return new ResponseEntity<>(USER_NOT_FOUND, HTTP_BAD_REQUEST);
            case WRONG_PASSWORD: return new ResponseEntity<>(WRONG_PASSWORD, HTTP_UNAUTHORIZED);
            case OK: return new ResponseEntity<>(new Token(authenticationResult.getToken()), HTTP_OK);
            default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
        }
    }
}
