package com.testex.subservice.servicelayer.users;

import com.testex.subservice.web.model.user.SignUpRequest;
import com.testex.subservice.web.model.user.UpdateRequest;
import com.testex.subservice.web.model.user.UserWeb;

import java.util.UUID;


public interface UserService {
    UserWeb createUser(SignUpRequest signUpRequest);
    UserWeb getUserByID(UUID userID);
    UserWeb updateUser(UUID userID, UpdateRequest updateUserRequest);
    void deleteUser(UUID userID);
}
