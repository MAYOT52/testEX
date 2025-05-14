package com.testex.subservice.servicelayer.subs.event;

import com.testex.subservice.web.model.sub.SubEventWeb;
import com.testex.subservice.web.model.sub.SubscriptionWeb;

import java.util.List;
import java.util.UUID;

public interface SubEventService {
    SubEventWeb addSubToUser(UUID userID, UUID subscriptionID);

    List<SubscriptionWeb> getTop3Popular();

    List<SubscriptionWeb> getSubscriptions(UUID userID);


    void deleteSubFromUser(UUID userID, UUID subscriptionID);
}
