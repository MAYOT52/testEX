package com.testex.subservice.web.exception;

import com.testex.subservice.exception.SubscriptionEventNotFoundException;
import com.testex.subservice.exception.SubscriptionNotFoundException;
import com.testex.subservice.exception.UserNotFoundException;
import com.testex.subservice.shared.Constraint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation failed: {}", ex.getMessage());
        return ResponseEntity.badRequest().body("Email is not valid");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        log.error("Data integrity violation: {}", ex.getMessage(), ex);

        if (rootCause != null && rootCause.getMessage() != null) {
            String message = rootCause.getMessage();
            if (message.contains(Constraint.USER_EMAIL_CONSTRAINT)) {
                log.warn("User email constraint violation: {}", message);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
            } else if (message.contains(Constraint.SUBSCRIPTION_NAME_CONSTRAINT)) {
                log.warn("Subscription name constraint violation: {}", message);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Subscription with this name already exists");
            } else if (message.contains(Constraint.SUBSCRIPTION_EVENT_CONSTRAINT)){
                log.warn("Subscription event constraint violation: {}", message);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already subscribed on this subscription");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error unique rule");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundExceptions(UserNotFoundException ex) {
        log.warn("User not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionNotFoundExceptions(SubscriptionNotFoundException ex) {
        log.warn("Subscription not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SubscriptionEventNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionEventNotFoundExceptions(SubscriptionEventNotFoundException ex) {
        log.warn("Subscription event not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
