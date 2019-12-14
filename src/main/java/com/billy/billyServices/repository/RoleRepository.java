package com.billy.billyServices.repository;

import com.billy.billyServices.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(final String name);
}
