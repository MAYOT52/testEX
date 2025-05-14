package com.testex.subservice.servicelayer;

import com.testex.subservice.domain.mapper.MapperUserDomain;
import com.testex.subservice.domain.model.User;
import com.testex.subservice.domain.service.DomainUserService;
import com.testex.subservice.exception.UserNotFoundException;
import com.testex.subservice.repository.mapper.MapperUserData;
import com.testex.subservice.repository.model.UserEntity;
import com.testex.subservice.repository.service.users.DataUserService;
import com.testex.subservice.servicelayer.users.UserServiceImpl;
import com.testex.subservice.web.mapper.MapperUserWeb;
import com.testex.subservice.web.model.user.SignUpRequest;
import com.testex.subservice.web.model.user.UpdateRequest;
import com.testex.subservice.web.model.user.UserWeb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private DataUserService dataUserService;
    @Mock
    private DomainUserService domainUserService;

    private final MapperUserDomain mapperUserDomain = Mappers.getMapper(MapperUserDomain.class);
    private final MapperUserData mapperUserData = Mappers.getMapper(MapperUserData.class);
    private final MapperUserWeb mapperUserWeb = Mappers.getMapper(MapperUserWeb.class);

    private UserServiceImpl userService;

    private UserEntity userEntity;

    private static final String emailAddress="email@email.com";

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(
                dataUserService,
                domainUserService,
                mapperUserDomain,
                mapperUserData,
                mapperUserWeb
        );



        userEntity = new UserEntity();
        userEntity.setUserID(UUID.randomUUID());
        userEntity.setEmail(emailAddress);


    }


    @Test
    void createUserTest() {
        UserEntity userEntityNoID = new UserEntity();
        userEntityNoID.setUserID(null);
        userEntityNoID.setEmail(emailAddress);
        
        User userCreatedInDomain = new User(); // User for mock domain
        userCreatedInDomain.setEmail(userEntityNoID.getEmail());
        when(domainUserService.createUser(userEntityNoID.getEmail())).thenReturn(userCreatedInDomain);
        
        when(dataUserService.save(any(UserEntity.class))).thenReturn(userEntity);

        SignUpRequest signUpRequest = new SignUpRequest(emailAddress);

        UserWeb savedUser = userService.createUser(signUpRequest);

        assertNotNull(savedUser.userID(), "User ID should not be null");
        assertEquals(emailAddress, savedUser.email(), "Email should match the expected value");
    }

    @Test
    void updateUserIfExistsTest() {
        UpdateRequest updateRequest = new UpdateRequest("newemail@email.com");
        when(dataUserService.findByUserID(userEntity.getUserID())).thenReturn(Optional.of(userEntity));

        UserWeb updatedUser = userService.updateUser(userEntity.getUserID(), updateRequest);

        assertNotNull(updatedUser.userID(), "User ID should not be null");
        assertEquals("newemail@email.com", updatedUser.email(), "Updated email should match the expected value");
        verify(dataUserService).findByUserID(userEntity.getUserID());
    }

    @Test
    void updateUserIfDoesntExistTest() {
        UpdateRequest updateRequest = new UpdateRequest("newemail@email.com");
        when(dataUserService.findByUserID(userEntity.getUserID())).thenReturn(Optional.empty());


        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userEntity.getUserID(), updateRequest);
        });
    }


    @Test
    void getUserIfExistsTest() {
        when(dataUserService.findByUserID(userEntity.getUserID())).thenReturn(Optional.of(userEntity));

        UserWeb foundUser = userService.getUserByID(userEntity.getUserID());

        assertEquals(userEntity.getUserID(), foundUser.userID(), "User ID should match the expected value");
        assertEquals(userEntity.getEmail(), foundUser.email(), "Email should match the expected value");
    }

    @Test
    void getUserIfDoesntExistsTest() {
        when(dataUserService.findByUserID(userEntity.getUserID())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByID(userEntity.getUserID());
        });
    }

    @Test
    void deleteUserIfExistsTest() {
        when(dataUserService.isExistsByID(userEntity.getUserID())).thenReturn(true);
        doNothing().when(dataUserService).deleteByUserID(userEntity.getUserID());

        userService.deleteUser(userEntity.getUserID());

        verify(dataUserService, times(1)).deleteByUserID(userEntity.getUserID());
    }

    @Test
    void deleteUserIfDoesntExistsTest() {
        when(dataUserService.isExistsByID(userEntity.getUserID())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(userEntity.getUserID());
        });
    }
}
