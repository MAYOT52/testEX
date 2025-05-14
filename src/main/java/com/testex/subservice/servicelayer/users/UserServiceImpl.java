package com.testex.subservice.servicelayer.users;

import com.testex.subservice.DTO.UserDTO;
import com.testex.subservice.domain.mapper.MapperUserDomain;
import com.testex.subservice.domain.model.User;
import com.testex.subservice.domain.service.DomainUserService;
import com.testex.subservice.exception.UserNotFoundException;
import com.testex.subservice.repository.mapper.MapperUserData;
import com.testex.subservice.repository.model.UserEntity;
import com.testex.subservice.repository.service.users.DataUserService;
import com.testex.subservice.web.mapper.MapperUserWeb;
import com.testex.subservice.web.model.user.SignUpRequest;
import com.testex.subservice.web.model.user.UpdateRequest;
import com.testex.subservice.web.model.user.UserWeb;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final DataUserService dataUserService;
    private final DomainUserService domainUserService;
    private final MapperUserDomain mapperUserDomain;
    private final MapperUserData mapperUserData;
    private final MapperUserWeb mapperUserWeb;

    @Transactional
    @Override
    public UserWeb createUser(SignUpRequest signUpRequest) {
        log.info("Creating user with email: {}", signUpRequest.email());
        User userToCreateDomain = domainUserService.createUser(signUpRequest.email());
        UserDTO userToCreateDTO = mapperUserDomain.toDTO(userToCreateDomain);
        UserEntity userToCreateData = mapperUserData.toData(userToCreateDTO);
        UserEntity createdUser = dataUserService.save(userToCreateData);
        log.info("User saved to database: {}", createdUser.getUserID());
        UserDTO createdUserDTO = mapperUserData.toDTO(createdUser);
        UserWeb createdUserWeb = mapperUserWeb.toWeb(createdUserDTO);
        log.info("User creation successful, returning: {}", createdUserWeb.userID());
        return createdUserWeb;
    }

    @Override
    public UserWeb getUserByID(UUID userID) {
        log.info("Fetching user with ID: {}", userID);
        UserEntity userEntity = dataUserService.findByUserID(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        log.info("User found: {}", userEntity.getUserID());
        UserDTO userDTO = mapperUserData.toDTO(userEntity);
        UserWeb userWeb = mapperUserWeb.toWeb(userDTO);
        log.info("User successfully returned: {}", userWeb);
        return userWeb;
    }

    @Transactional
    @Override
    public UserWeb updateUser(UUID userID, UpdateRequest updateUserRequest) {
        log.info("Updating user with ID: {} and new email: {}", userID, updateUserRequest.email());
        UserEntity userToUpdateData = dataUserService.findByUserID(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        log.info("User to update found: {}", userToUpdateData.getUserID());
        userToUpdateData.setEmail(updateUserRequest.email());
        UserDTO updatedUserDTO = mapperUserData.toDTO(userToUpdateData);
        UserWeb updatedUserWeb = mapperUserWeb.toWeb(updatedUserDTO);
        log.info("User updated successfully: {}", updatedUserWeb.userID());
        return updatedUserWeb;
    }

    @Transactional
    @Override
    public void deleteUser(UUID userID) {
        log.info("Deleting user with ID: {}", userID);
        if (dataUserService.isExistsByID(userID)) {
            log.info("User found, deleting user with ID: {}", userID);
            dataUserService.deleteByUserID(userID);
            log.info("User with ID: {} deleted successfully", userID);
        } else {
            throw new UserNotFoundException(userID);
        }
    }
}
