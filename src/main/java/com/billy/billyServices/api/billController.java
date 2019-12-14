package com.billy.billyServices.api;

import com.billy.billyServices.enums.BillCreateStatus;
import com.billy.billyServices.model.*;
import com.billy.billyServices.service.AuthorizationService;
import com.billy.billyServices.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for bills
 */
@RestController
@RequestMapping("/bills")
public class billController extends basicController {
    private static final String BILL_CREATE_FAILED_RESPONSE = "Bill creation failed";
    private static final String CREATED_RESPONSE = "Bill created";
    private static final String USERNAME_NO_EXIST = "Username not found";
    private static final String UNAUTHORIZED = "Unauthorized to get %s bills";
    private static final String BILL_NOT_FOUND = "Bill with id %s not found";
    private static final int ZERO = 0;

    @Autowired
    private BillService billService;
    @Autowired
    private AuthorizationService authorizationService;

    /**
     * Post new bill endpoint
     *
     * @param newBill {@link NewBill} the bill
     * @return {@link ResponseEntity} the response entity with body containing token (if request is valid) and Http status
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createBill(@RequestHeader("Authorization") final String auth,
                                        @RequestBody final NewBill newBill) {

        final AuthorizationResult authorizationResult = authorize(auth, ONLY_ADMIN_ROLE);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {
            final BillCreateStatus billCreateStatus = billService.create(newBill.getBill(), newBill.getUsername());

            switch (billCreateStatus) {
                case CREATED: return new ResponseEntity<>(CREATED_RESPONSE, HTTP_CREATED);
                case FAILED: return new ResponseEntity<>(BILL_CREATE_FAILED_RESPONSE, HTTP_BAD_REQUEST);
                default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }

    /**
     * Get single user bill endpoint
     *
     * @param username {@link String} the username
     * @param uuid     {@link UUID}   the bill id
     * @return {@link ResponseEntity} the response entity with body containing {@link Bill} (if request is valid) and Http status
     */
    @GetMapping(value = "/{username}/{id}", produces = "application/json")
    public ResponseEntity<?> getBill(@RequestHeader("Authorization") final String auth,
                                     @PathVariable("username") final String username,
                                     @PathVariable("id") final UUID uuid) {

        final AuthorizationResult authorizationResult = authorize(auth, ALL_ROLES);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {

            if (tokenUsernameValid(auth, username)) {
                final GetBillsResult result = billService.getBill(uuid);

                switch (result.getStatus()) {
                    case BILL_NO_EXIST: {
                        return new ResponseEntity<>(String.format(BILL_NOT_FOUND, uuid.toString()), HTTP_BAD_REQUEST);
                    }
                    case OK: {
                        if (result.getBills().isEmpty()) return new ResponseEntity<>(EMPTY_STRING, HTTP_NO_CONTENT);
                        else return new ResponseEntity<>(result.getBills().get(ZERO), HTTP_OK);
                    }
                    case FAILED: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
                    default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
                }
            } else {
                return new ResponseEntity<>(String.format(UNAUTHORIZED, username), HTTP_UNAUTHORIZED);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }

    /**
     * Get all user bills endpoint
     *
     * @param username {@link String} the username
     * @return {@link ResponseEntity} the response entity with body containing {@link ArrayList<Bill>} (if request is valid) and Http status
     */
    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<?> getBills(@RequestHeader("Authorization") final String auth,
                                      @PathVariable("username") final String username) {

        final AuthorizationResult authorizationResult = authorize(auth, ALL_ROLES);

        if (authorizationResult.getAuthorizationStatus() == STATUS_AUTHORIZED) {

            if (tokenUsernameValid(auth, username)) {
                final GetBillsResult result = billService.getBills(username);

                switch (result.getStatus()) {
                    case USERNAME_NO_EXIST: return new ResponseEntity<>(USERNAME_NO_EXIST, HTTP_BAD_REQUEST);
                    case OK: {
                        if (result.getBills().isEmpty()) return new ResponseEntity<>(EMPTY_STRING, HTTP_NO_CONTENT);
                        else return new ResponseEntity<>(result.getBills(), HTTP_OK);
                    }
                    case FAILED: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
                    default: return new ResponseEntity<>(SERVER_ERROR_RESPONSE, HTTP_INTERNAL_ERROR);
                }
            } else {
                return new ResponseEntity<>(String.format(UNAUTHORIZED, username), HTTP_UNAUTHORIZED);
            }
        } else {
            return authorizationResult.getResponseEntity();
        }
    }

    /**
     * Check if user (not Admin role) trying to get someone else (different username) bills
     *
     * @param auth     {@link String} the auth
     * @param username {@link String} the username
     * @return {@link boolean} the boolean representing validity of {@link Role} or username in token and in var username
     */
    private boolean tokenUsernameValid(final String auth, final String username) {

        if (authorizationService.getRole(auth).getName().equals(ROLE_ADMIN)) return true;
        else if (!authorizationService.getUsername(auth).equals(username)) return false;
        else return true;
    }
}
