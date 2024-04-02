package com.javaspring.javajrinterview.client;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByEmail(String email);
    Optional<ClientEntity> findByPhone(String phone);
}
