package com.billy.billyServices.service;

import com.billy.billyServices.model.AuthenticationResult;
import com.billy.billyServices.model.Login;

public interface AuthenticationService {
    AuthenticationResult generateJwt(final Login login);
}
