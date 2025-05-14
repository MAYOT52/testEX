package com.testex.subservice.servicelayer.subs.subscription;


import com.testex.subservice.web.model.sub.CreateSubRequest;
import com.testex.subservice.web.model.sub.SubscriptionWeb;

import java.util.List;
import java.util.UUID;

public interface SubService {

    SubscriptionWeb createSubscription(CreateSubRequest createSubRequest);

    List<SubscriptionWeb> getAllSubscriptions();

    void deleteSubscription(UUID subID);
}
