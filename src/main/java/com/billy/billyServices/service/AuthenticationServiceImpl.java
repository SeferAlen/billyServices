package com.billy.billyServices.service;

import com.billy.billyServices.api.loginController;
import com.billy.billyServices.enums.AuthenticationStatus;
import com.billy.billyServices.model.AuthenticationResult;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service for users authentication and authentication related actions
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final AuthenticationStatus STATUS_NOT_FOUND = AuthenticationStatus.USER_NOT_FOUND;
    private static final AuthenticationStatus STATUS_WRONG_PASSWORD = AuthenticationStatus.WRONG_PASSWORD;
    private static final AuthenticationStatus STATUS_OK = AuthenticationStatus.OK;
    private static final AuthenticationStatus STATUS_FAILED = AuthenticationStatus.FAILED;
    private static final String NO_TOKEN = "";
    private static final String LOGIN_NULL = "Login must not be null";

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(loginController.class);

    /**
     * Method for generating Jwt
     *
     * @param login {@link Login} the login containing username and password
     * @return {@link AuthenticationResult} the result of generating Jwt containing token if login data is valid and
     * {@link AuthenticationStatus} representing status token generating action
     */
    public AuthenticationResult generateJwt(final Login login) {
        Objects.requireNonNull(login, LOGIN_NULL);

        try {
            final Login dbLogin = loginRepository.findByUsername(login.getUsername());

            if (dbLogin == null) return new AuthenticationResult(STATUS_NOT_FOUND, NO_TOKEN);
            final boolean passwordCorrect = passwordEncoder.matches(login.getPassword(), dbLogin.getPassword());
            if (!passwordCorrect) return new AuthenticationResult(STATUS_WRONG_PASSWORD, NO_TOKEN);

            return new AuthenticationResult(STATUS_OK, JwtUtil.generateToken(dbLogin));
        } catch (final Exception e) {
            logger.error(e.getLocalizedMessage());

            return new AuthenticationResult(STATUS_FAILED, NO_TOKEN);
        }
    }
}
