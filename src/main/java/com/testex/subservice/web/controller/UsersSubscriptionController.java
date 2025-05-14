package com.testex.subservice.web.controller;


import com.testex.subservice.servicelayer.subs.event.SubEventService;
import com.testex.subservice.web.model.sub.SubEventWeb;
import com.testex.subservice.web.model.sub.SubscriptionWeb;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersSubscriptionController {
    private final SubEventService subEventService;


    @Operation(
            summary = "Add a subscription to a user",
            description = "Adds a subscription service (e.g., Netflix, Yandex Plus) " +
                    "to the specified user's list of subscriptions."
    )
    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<SubEventWeb> addSubscription(@PathVariable("id") UUID userID, @RequestBody UUID subscriptionID) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subEventService.addSubToUser(userID, subscriptionID));
    }

    @Operation(
            summary = "Get a user's subscriptions",
            description = "Retrieves all subscription services that the specified user is currently subscribed to."
    )
    @GetMapping("/{id}/subscriptions")
    public List<SubscriptionWeb> getSubscriptions(@PathVariable("id") UUID userID) {
        return subEventService.getSubscriptions(userID);
    }

    @Operation(
            summary = "Remove a subscription from a user",
            description = "Removes a specific subscription service from the specified user's list of subscriptions."
    )
    @DeleteMapping("/{user_id}/subscriptions/{subscription_id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("user_id") UUID userID,
                                                @PathVariable("subscription_id") UUID subscriptionID) {
        subEventService.deleteSubFromUser(userID, subscriptionID);
        return ResponseEntity.noContent().build();
    }

}