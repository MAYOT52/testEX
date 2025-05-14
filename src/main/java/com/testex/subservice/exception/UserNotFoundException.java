package com.testex.subservice.exception;

import java.util.UUID;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(UUID userID) { super("User with ID: " + userID.toString() + " not found"); }
}
