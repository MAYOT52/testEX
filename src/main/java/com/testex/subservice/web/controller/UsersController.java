package com.testex.subservice.web.controller;


import com.testex.subservice.servicelayer.users.UserService;
import com.testex.subservice.web.model.user.SignUpRequest;
import com.testex.subservice.web.model.user.UpdateRequest;
import com.testex.subservice.web.model.user.UserWeb;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UsersController {
    private final UserService userService;

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user with the provided sign-up details. " +
                    "Returns the unique ID of the newly created user."
    )
    @PostMapping("")
    public ResponseEntity<UUID> createUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(signUpRequest).userID());
    }

    @Operation(
            summary = "Get user by ID",
            description = "Returns the details of a user based on their unique ID."
    )
    @GetMapping("/{id}")
    public UserWeb getUser(@PathVariable UUID id) {
        return userService.getUserByID(id);
    }

    @Operation(
            summary = "Update user details",
            description = "Updates the user information for the specified user ID."
    )
    @PutMapping("/update/{id}")
    public UserWeb updateUser(@PathVariable UUID id, @RequestBody @Valid UpdateRequest updateRequest) {
        return userService.updateUser(id, updateRequest);
    }

    @Operation(
            summary = "Delete a user",
            description = "Deletes a user based on their unique ID. " +
                    "The user and their associated data will be permanently removed."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
