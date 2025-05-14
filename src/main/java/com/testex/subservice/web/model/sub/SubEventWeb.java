package com.testex.subservice.web.model.sub;


import com.testex.subservice.repository.model.SubscriptionEntity;
import com.testex.subservice.repository.model.UserEntity;
import com.testex.subservice.shared.Default;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubEventWeb {
    private UUID eventID;
    private UserEntity user;
    private SubscriptionEntity subscription;

    public SubEventWeb() {}

    @Default
    public SubEventWeb(UserEntity user, SubscriptionEntity subscription) {
        this.user = user;
        this.subscription = subscription;
    }
}
