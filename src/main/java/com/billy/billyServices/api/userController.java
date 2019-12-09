package com.billy.billyServices.api;

import com.billy.billyServices.enums.PasswordChangeStatus;
import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.model.NewPassword;
import com.billy.billyServices.model.Register;
import com.billy.billyServices.model.Token;
import com.billy.billyServices.service.UserService;
import com.billy.billyServices.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * REST controller for user CRUD actions
 */
@RestController
@RequestMapping("/user")
public class userController extends basicController {
    private static final String ALREADY_EXIST_RESPONSE = "Username already exist";
    private static final String FAILED_RESPONSE = "User creation failed";
    private static final String CREATED_RESPONSE = "User created";
    private static final String PASSWORD_CHANGED = "Password changed";
    private static final PasswordChangeStatus PASSWORD_CHANGE_FAILED_STATUS = PasswordChangeStatus.FAILED;
    private static final PasswordChangeStatus PASSWORD_CHANGED_STATUS = PasswordChangeStatus.CHANGED;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(loginController.class);

    /**
     * Get all users from database endpoint
     *
     * @param token {@link Token} the token
     * @return {@link ResponseEntity} the response entity with body containing users and Http status
     */
    @GetMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getUsers(@RequestBody final Token token) {
        return new ResponseEntity<>("OK", HTTP_OK);
    }

    /**
     * Register new user endpoint
     *
     * @param register {@link Register} the register containing user info and password
     * @return {@link ResponseEntity} the response entity with body containing status of user creation action and Http status
     */
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody final Register register) {

        final UserCreateStatus createStatus = userService.createUser(register.getBillyUser(), register.getLogin());

        switch (createStatus) {
            case ALREADY_EXIST:
                return new ResponseEntity<>(ALREADY_EXIST_RESPONSE, HTTP_BAD_REQUEST);
            case FAILED:
                return new ResponseEntity<>(FAILED_RESPONSE, HTTP_BAD_REQUEST);
            case CREATED:
                return new ResponseEntity<>(CREATED_RESPONSE, HTTP_BAD_REQUEST);
            default:
                return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
        }
    }

    /**
     * Change password endpoint
     *
     * @param newPassword {@link NewPassword} the newPassword
     * @return {@link ResponseEntity} the response entity with body with message status and Http status
     */
    @PostMapping(value = "/password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") final String auth,
                                            @Valid@RequestBody final NewPassword newPassword) {

        final String token = auth.substring(auth.indexOf(EMPTY_SPACE));

        final AuthorizationResult authorizationResult = authorize(token, ALL_ROLES);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {
            final PasswordChangeStatus passwordChangeStatus = userService.changePassword(JwtUtil.getUsernameFromToken(token),
                    newPassword.getPassword());

            if (passwordChangeStatus == PASSWORD_CHANGED_STATUS) {
                return new ResponseEntity<>(PASSWORD_CHANGED, HTTP_OK);
            } else {
                return new ResponseEntity<>(UNAUTHORIZED, HTTP_UNAUTHORIZED);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }
}
