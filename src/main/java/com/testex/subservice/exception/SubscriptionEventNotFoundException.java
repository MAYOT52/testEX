package com.testex.subservice.exception;

import java.util.UUID;

public class SubscriptionEventNotFoundException extends RuntimeException {
    public SubscriptionEventNotFoundException(UUID userID) {
        super("Subscription event with ID: " + userID.toString() + " not found");
    }


}
