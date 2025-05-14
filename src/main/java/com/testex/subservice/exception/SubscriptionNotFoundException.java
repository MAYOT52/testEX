package com.testex.subservice.exception;

import java.util.UUID;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(UUID userID) {
        super("Subscription with ID: " + userID.toString() + " not found");
    }


}
