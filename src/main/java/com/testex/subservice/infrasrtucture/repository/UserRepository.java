package com.testex.subservice.infrasrtucture.repository;

import com.testex.subservice.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUserID(UUID userID);
    Optional<UserEntity> findByUserID(UUID userID);
    void deleteByUserID(UUID userID);
}
