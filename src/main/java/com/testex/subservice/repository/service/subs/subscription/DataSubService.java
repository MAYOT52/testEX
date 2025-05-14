package com.testex.subservice.repository.service.subs.subscription;

import com.testex.subservice.repository.model.SubscriptionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataSubService {
    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);
    Optional<SubscriptionEntity> findByID(UUID subID);
    boolean isExistsBySubID(UUID subID);
    boolean isExistsByName(String subName);
    List<SubscriptionEntity> findAllSub();
    public void deleteBySubID(UUID subID);
}
