package com.testex.subservice.DTO;

import com.testex.subservice.shared.Default;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubscriptionDTO {
    private UUID subID;
    private String subName;

    public SubscriptionDTO() {}

    @Default
    public SubscriptionDTO(UUID subID, String subName) {
        this.subID = subID;
        this.subName = subName;
    }
}
