package com.testex.subservice.infrasrtucture.repository;

import com.testex.subservice.repository.model.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
    boolean existsBySubID(UUID subID);

    boolean existsBySubName(String subName);

    void deleteBySubID(UUID subID);
}
