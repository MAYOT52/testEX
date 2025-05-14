package com.testex.subservice.repository.service.users;

import com.testex.subservice.repository.model.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface DataUserService {
    boolean isExistsByID(UUID id);
    UserEntity save(UserEntity user);
    Optional<UserEntity> findByUserID(UUID id);
    void deleteByUserID(UUID id);
}
