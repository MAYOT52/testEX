package com.testex.subservice.infrasrtucture.repository;

import com.testex.subservice.repository.model.SubEventEntity;
import com.testex.subservice.repository.model.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SubEventRepository extends JpaRepository<SubEventEntity, UUID> {
    @Query("""
                SELECT s.subscription
                FROM SubEventEntity s
                WHERE s.user.userID = :userID
            """)
    List<SubscriptionEntity> findAllSubscriptionsByUserID(UUID userID);

    boolean existsByUser_UserIDAndSubscription_SubID(UUID userID, UUID subscriptionID);

    void deleteByUser_UserIDAndSubscription_SubID(UUID userID, UUID eventID);

    @Query("""
                SELECT s.subscription FROM SubEventEntity s
                GROUP BY s.subscription
                ORDER BY COUNT(s) DESC
            """)
    Page<SubscriptionEntity> topSubscriptions(Pageable pageable);

}