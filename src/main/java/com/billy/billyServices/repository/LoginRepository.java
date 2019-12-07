package com.billy.billyServices.repository;

import com.billy.billyServices.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {

    Login findByUsername(final String username);
}
