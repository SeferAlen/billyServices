package com.billy.billyServices.service;

import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;

public interface UserService {
    UserCreateStatus createUser(final BillyUser billyUser, final Login login);
}
