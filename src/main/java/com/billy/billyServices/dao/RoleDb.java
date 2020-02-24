package com.billy.billyServices.dao;

import com.billy.billyServices.model.Role;

public interface RoleDb {

    Role findByName(final String name);
}
