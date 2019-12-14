package com.billy.billyServices.service;

import com.billy.billyServices.enums.PasswordChangeStatus;
import com.billy.billyServices.enums.UserCreateStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.GetUsersResult;
import com.billy.billyServices.model.Login;

import java.util.List;

public interface UserService {

    UserCreateStatus createUser(final BillyUser billyUser, final Login login);
    UserCreateStatus createAdmin(final BillyUser billyUser, final Login login);
    PasswordChangeStatus changePassword(final String username, final String newPassword);
    GetUsersResult getUsers();
}
