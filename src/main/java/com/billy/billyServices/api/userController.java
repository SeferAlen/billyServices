package com.billy.billyServices.api;

import com.billy.billyServices.model.AuthorizationResult;
import com.billy.billyServices.model.GetUsersResult;
import com.billy.billyServices.model.Register;
import com.billy.billyServices.model.NewPassword;
import com.billy.billyServices.model.NewPasswordResponse;
import com.billy.billyServices.enums.PasswordChangeStatus;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.service.UserService;
import com.billy.billyServices.utility.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

/**
 * REST controller for user CRUD actions
 */
@RestController
@RequestMapping("/users")
public class userController extends basicController {
    private static final String ALREADY_EXIST_RESPONSE = "Username already exist";
    private static final String USER_CREATE_FAILED_RESPONSE = "User creation failed";
    private static final String CREATED_RESPONSE = "User created";
    private static final String PASSWORD_CHANGED = "Password changed";
    private static final String PASSWORD_SAME = "Password is same as old password";

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(userController.class);

    /**
     * Get all users from database endpoint
     *
     * @param auth {@link String} the Authorization header
     * @return {@link ResponseEntity} the response entity with body containing users and Http status
     */
    @CrossOrigin()
    @GetMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getUsers(@RequestHeader("Authorization") final String auth) {

        final AuthorizationResult authorizationResult = authorize(auth, ONLY_ADMIN_ROLE);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {
            final GetUsersResult getUsersResult = userService.getUsers();

            switch (getUsersResult.getStatus()) {
                case NO_USERS: return new ResponseEntity<>(HTTP_NO_CONTENT);
                case OK: return new ResponseEntity<>(getUsersResult.getUsers(), HTTP_OK);
                case FAILED: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_BAD_REQUEST);
                default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }

    /**
     * Register new user endpoint
     *
     * @param register {@link Register} the register containing user info and password
     * @return {@link ResponseEntity} the response entity with body containing status of user creation action and Http status
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody @Valid final Register register) {

        final UserCreateStatus createStatus = userService.createUser(register.getBillyUser(), register.getLogin());

        switch (createStatus) {
            case ALREADY_EXIST: return new ResponseEntity<>(ALREADY_EXIST_RESPONSE, HTTP_BAD_REQUEST);
            case CREATED: return new ResponseEntity<>(CREATED_RESPONSE, HTTP_CREATED);
            case FAILED: return new ResponseEntity<>(USER_CREATE_FAILED_RESPONSE, HTTP_BAD_REQUEST);
            default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
        }
    }

    /**
     * Change password endpoint
     *
     * @param newPassword {@link NewPassword} the newPassword
     * @return {@link ResponseEntity} the response entity with body containing message and Http status
     */
    @CrossOrigin()
    @PostMapping(value = "/password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") final String auth,
                                            @RequestBody @Valid final NewPassword newPassword) {

        final AuthorizationResult authorizationResult = authorize(auth, ALL_ROLES);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {

            final String token = auth.substring(auth.indexOf(EMPTY_SPACE));
            final PasswordChangeStatus passwordChangeStatus = userService.changePassword(JwtUtil.getUsernameFromToken(token),
                    newPassword.getPassword());

            switch (passwordChangeStatus) {
                case SAME_PASSWORD: return new ResponseEntity<>(new NewPasswordResponse(PASSWORD_SAME), HTTP_ACCEPTED);
                case CHANGED: return new ResponseEntity<>(new NewPasswordResponse(PASSWORD_CHANGED), HTTP_OK);
                case FAILED: return new ResponseEntity<>(new NewPasswordResponse(SERVER_ERROR_RESPONSE), HTTP_BAD_REQUEST);
                default: return new ResponseEntity<>(new NewPasswordResponse(SERVER_ERROR_RESPONSE), HTTP_INTERNAL_ERROR);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }
}
