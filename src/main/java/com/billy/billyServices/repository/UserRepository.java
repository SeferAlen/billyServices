package com.billy.billyServices.repository;

import com.billy.billyServices.model.Bill;
import com.billy.billyServices.model.BillyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<BillyUser, UUID> {

    Optional<BillyUser> findById(final UUID uuid);
}
