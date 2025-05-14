package com.testex.subservice.repository.service.subs.event;


import com.testex.subservice.infrasrtucture.repository.SubEventRepository;
import com.testex.subservice.repository.model.SubEventEntity;
import com.testex.subservice.repository.model.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DataSubEventServiceImpl implements DataSubEventService {
    private final SubEventRepository subEventRepository;

    @Override
    public SubEventEntity save(SubEventEntity subEventEntity) {
        return subEventRepository.save(subEventEntity);
    }

    @Override
    public Page<SubscriptionEntity> topSubscriptions(Pageable pageable) {
        return subEventRepository.topSubscriptions(pageable);
    }

    @Override
    public List<SubscriptionEntity> findAllSubscriptionsByUserID(UUID userID) {
        return subEventRepository.findAllSubscriptionsByUserID(userID);
    }

    @Override
    public void deleteByUserIDAndSubID(UUID userID, UUID subID) {
        subEventRepository.deleteByUser_UserIDAndSubscription_SubID(userID, subID);
    }



}
