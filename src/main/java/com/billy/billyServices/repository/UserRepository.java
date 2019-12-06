package com.billy.billyServices.repository;

import com.billy.billyServices.model.BillyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<BillyUser, UUID> {
}
