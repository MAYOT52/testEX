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
        name = "subscription_entity",
        uniqueConstraints = @UniqueConstraint(name = Constraint.SUBSCRIPTION_NAME_CONSTRAINT, columnNames = "sub_name")
)
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID subID;
    @Column(nullable = false)
    private String subName;

    public SubscriptionEntity() {}

    @Default
    public SubscriptionEntity(UUID subID, String subName) {
        this.subID = subID;
        this.subName = subName;
    }
}
