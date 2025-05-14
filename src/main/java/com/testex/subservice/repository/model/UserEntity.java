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
        name = "user_entity",
        uniqueConstraints = @UniqueConstraint(name = Constraint.USER_EMAIL_CONSTRAINT, columnNames = "email")
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID userID;

    @Column(nullable = false)
    private String email;

    public UserEntity(){}

    @Default
    public UserEntity(UUID userID, String email) {
        this.userID = userID;
        this.email = email;
    }
}
