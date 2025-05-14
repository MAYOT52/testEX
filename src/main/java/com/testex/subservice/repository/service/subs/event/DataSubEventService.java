package com.testex.subservice.repository.service.subs.event;

import com.testex.subservice.repository.model.SubEventEntity;
import com.testex.subservice.repository.model.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DataSubEventService {


    SubEventEntity save(SubEventEntity subEventEntity);

    Page<SubscriptionEntity> topSubscriptions(Pageable pageable);

    List<SubscriptionEntity> findAllSubscriptionsByUserID(UUID userID);

    void deleteByUserIDAndSubID(UUID userID, UUID subID);
}
