package com.testex.subservice.DTO;

import com.testex.subservice.repository.model.SubscriptionEntity;
import com.testex.subservice.repository.model.UserEntity;
import com.testex.subservice.shared.Default;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class SubEventDTO {
    private UUID eventID;

    private UserEntity user;

    private SubscriptionEntity subscription;

    public SubEventDTO() {
    }

    @Default
    public SubEventDTO(UserEntity user, SubscriptionEntity subscription) {
        this.user = user;
        this.subscription = subscription;
    }
}
