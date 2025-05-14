package com.testex.subservice.web.controller;

import com.testex.subservice.servicelayer.subs.event.SubEventService;
import com.testex.subservice.servicelayer.subs.subscription.SubService;
import com.testex.subservice.web.model.sub.CreateSubRequest;
import com.testex.subservice.web.model.sub.SubscriptionWeb;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubService subService;
    private final SubEventService subEventService;


    @Operation(
            summary = "Get top 3 most popular subscriptions",
            description = "Returns the top 3 (or fewer if less than 3 subscriptions are used) most popular subscription services."
    )
    @GetMapping("/top")
    public List<SubscriptionWeb> getTop3Subscriptions() {
        return subEventService.getTop3Popular();
    }

    @Operation(
            summary = "Create a new subscription",
            description = """
                    Creates a new subscription service (e.g., Yandex Plus, Netflix, YouTube Premium)
                    that can later be assigned to users.
                    
                    Note: On application startup, the following subscriptions are already available by default:
                    - Yandex Plus
                    - YouTube Premium
                    - Netflix
                    - VK Music
                    """
    )
    @PostMapping("")
    public ResponseEntity<SubscriptionWeb> createSubscription(@RequestBody CreateSubRequest createSubRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subService.createSubscription(createSubRequest));
    }


    @Operation(
            summary = "Get all available subscriptions",
            description = "Returns all subscription services in the system."
    )
    @GetMapping("")
    public List<SubscriptionWeb> getSubscriptions() {
        return subService.getAllSubscriptions();
    }

    @Operation(
            summary = "Delete a subscription service",
            description = "Deletes a subscription service by its ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable UUID id) {
        subService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }


}
