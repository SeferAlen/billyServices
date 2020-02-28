package com.billy.billyServices.dao;

import com.billy.billyServices.model.Login;

public interface LoginDb {

    Login findByUsername(final String username);
}
