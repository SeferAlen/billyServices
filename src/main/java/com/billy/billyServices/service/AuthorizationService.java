package com.billy.billyServices.service;

import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.AuthorizationResult;

import java.util.List;

public interface AuthorizationService {

    TokenStatus checkToken(final String auth);
    AuthorizationResult authorize(final String token, final List<String> requiredRoleNames);
}
