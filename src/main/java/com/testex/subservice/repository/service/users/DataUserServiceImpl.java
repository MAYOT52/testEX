package com.testex.subservice.repository.service.users;

import com.testex.subservice.infrasrtucture.repository.UserRepository;
import com.testex.subservice.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DataUserServiceImpl implements DataUserService {
    private final UserRepository userRepository;

    @Override
    public boolean isExistsByID(UUID id) {
        return userRepository.existsByUserID(id);
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUserID(UUID id){
        return userRepository.findByUserID(id);
    }

    @Override
    public void deleteByUserID(UUID id){
        userRepository.deleteByUserID(id);
    }
}

