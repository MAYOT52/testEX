package com.testex.subservice.repository.model;

import com.testex.subservice.shared.Constraint;
import com.testex.subservice.shared.Default;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@Table(
        name = "sub_event_entity",
        uniqueConstraints = @UniqueConstraint(
                name = Constraint.SUBSCRIPTION_EVENT_CONSTRAINT,
                columnNames = {"user_id", "sub_id"}
        )
)
public class SubEventEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID eventID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private SubscriptionEntity subscription;


    public SubEventEntity() {}

    @Default
    public SubEventEntity(UserEntity user, SubscriptionEntity subscription) {
        this.user = user;
        this.subscription = subscription;
    }

}
