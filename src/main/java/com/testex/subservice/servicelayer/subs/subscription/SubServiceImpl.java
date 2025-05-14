package com.testex.subservice.servicelayer.subs.subscription;

import com.testex.subservice.DTO.SubscriptionDTO;
import com.testex.subservice.domain.model.User;
import com.testex.subservice.exception.SubscriptionNotFoundException;
import com.testex.subservice.repository.mapper.MapperSubData;
import com.testex.subservice.repository.model.SubscriptionEntity;
import com.testex.subservice.repository.service.subs.subscription.DataSubService;
import com.testex.subservice.web.mapper.MapperSubWeb;
import com.testex.subservice.web.model.sub.CreateSubRequest;
import com.testex.subservice.web.model.sub.SubscriptionWeb;
import com.testex.subservice.web.model.user.UserWeb;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class SubServiceImpl implements SubService {
    private final DataSubService dataSubService;
    private final MapperSubData mapperSubData;
    private final MapperSubWeb mapperSubWeb;


    @Override
    public List<SubscriptionWeb> getAllSubscriptions() {
        log.info("Fetching all subscriptions");
        List<SubscriptionEntity> subscriptionEntities = dataSubService.findAllSub();
        log.info("Found {} subscriptions", subscriptionEntities.size());
        List<SubscriptionDTO> subscriptionsDTO = mapperSubData.toDTO(subscriptionEntities);
        List<SubscriptionWeb> subscriptionsWeb = mapperSubWeb.toWeb(subscriptionsDTO);
        log.info("Returning {} subscriptions to web layer", subscriptionsWeb.size());
        return subscriptionsWeb;
    }

    @Transactional
    @Override
    public SubscriptionWeb createSubscription(CreateSubRequest createSubRequest) {
        log.info("Creating subscription with name: {}", createSubRequest.name());
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setSubName(createSubRequest.name());
        SubscriptionEntity createdSubEntity = dataSubService.save(subscriptionEntity);
        log.info("Subscription saved to DB with ID: {}", createdSubEntity.getSubID());
        SubscriptionDTO createdSubDTO = mapperSubData.toDTO(createdSubEntity);
        SubscriptionWeb createdSubWeb = mapperSubWeb.toWeb(createdSubDTO);
        log.info("Returning created subscription to web layer: {}", createdSubWeb.getSubID());
        return createdSubWeb;
    }

    @Transactional
    @Override
    public void deleteSubscription(UUID subID) {
        log.info("Deleting subscription with ID: {}", subID);
        if(!dataSubService.isExistsBySubID(subID)) throw new SubscriptionNotFoundException(subID);
        log.info("Subscription with ID {} deleted successfully", subID);
        dataSubService.deleteBySubID(subID);
    }
}
