package com.testex.subservice.servicelayer.subs.event;

import com.testex.subservice.DTO.SubEventDTO;
import com.testex.subservice.DTO.SubscriptionDTO;
import com.testex.subservice.exception.SubscriptionNotFoundException;
import com.testex.subservice.exception.UserNotFoundException;
import com.testex.subservice.repository.mapper.MapperSubData;
import com.testex.subservice.repository.mapper.MapperSubEventData;
import com.testex.subservice.repository.model.SubEventEntity;
import com.testex.subservice.repository.model.SubscriptionEntity;
import com.testex.subservice.repository.model.UserEntity;
import com.testex.subservice.repository.service.subs.subscription.DataSubService;
import com.testex.subservice.repository.service.subs.event.DataSubEventService;
import com.testex.subservice.repository.service.users.DataUserService;
import com.testex.subservice.web.mapper.MapperSubEventWeb;
import com.testex.subservice.web.mapper.MapperSubWeb;
import com.testex.subservice.web.model.sub.SubEventWeb;
import com.testex.subservice.web.model.sub.SubscriptionWeb;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubEventServiceImpl implements SubEventService {
    private final DataUserService dataUserService;
    private final DataSubEventService dataSubEventService;
    private final DataSubService dataSubService;
    private final MapperSubEventData mapperSubEventData;
    private final MapperSubEventWeb mapperSubEventWeb;
    private final MapperSubData mapperSubData;
    private final MapperSubWeb mapperSubWeb;


    @Transactional
    @Override
    public SubEventWeb addSubToUser(UUID userID, UUID subscriptionID) {
        log.info("Adding subscription {} to user {}", subscriptionID, userID);
        UserEntity userToAddSub = dataUserService.findByUserID(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        SubscriptionEntity subscriptionToAdd = dataSubService.findByID(subscriptionID)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionID));
        SubEventEntity subEventToAdd = new SubEventEntity();
        subEventToAdd.setUser(userToAddSub);
        subEventToAdd.setSubscription(subscriptionToAdd);
        SubEventEntity addedEventEntity = dataSubEventService.save(subEventToAdd);
        log.info("Subscription {} successfully added to user {}", subscriptionID, userID);
        SubEventDTO addedEventDTO = mapperSubEventData.toDTO(addedEventEntity);
        return mapperSubEventWeb.toWeb(addedEventDTO);
    }

    @Override
    public List<SubscriptionWeb> getTop3Popular(){
        log.info("Fetching top 3 popular subscriptions");
        Pageable top3 = PageRequest.of(0, 3);
        List<SubscriptionEntity> top3Sub =  dataSubEventService.topSubscriptions(top3).getContent();
        List<SubscriptionDTO> top3SubDTO = mapperSubData.toDTO(top3Sub);
        List<String> topNames = top3SubDTO.stream()
                .map(SubscriptionDTO::getSubName)
                .toList();
        log.info("Top 3 subscriptions fetched: {}", String.join(", ", topNames));
        return mapperSubWeb.toWeb(top3SubDTO);
    }

    @Override
    public List<SubscriptionWeb> getSubscriptions(UUID userID) {
        log.info("Fetching subscriptions for user {}", userID);
        if (!dataUserService.isExistsByID(userID))
            throw new UserNotFoundException(userID);
        List<SubscriptionEntity> subscriptionsEntity = dataSubEventService.findAllSubscriptionsByUserID(userID);
        List<SubscriptionDTO> subscriptionsDTO = mapperSubData.toDTO(subscriptionsEntity);
        log.info("User {} has {} subscriptions", userID, subscriptionsDTO.size());
        return mapperSubWeb.toWeb(subscriptionsDTO);
    }

    @Transactional
    @Override
    public void deleteSubFromUser(UUID userID, UUID subscriptionID) {
        log.info("Deleting subscription {} from user {}", subscriptionID, userID);
        if(!dataUserService.isExistsByID(userID))throw new UserNotFoundException(userID);
        if(!dataSubService.isExistsBySubID(subscriptionID))throw new SubscriptionNotFoundException(subscriptionID);
        dataSubEventService.deleteByUserIDAndSubID(userID, subscriptionID);
        log.info("Subscription {} successfully removed from user {}", subscriptionID, userID);
    }




}
