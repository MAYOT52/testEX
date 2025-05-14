package com.testex.subservice.repository.service.subs.subscription;

import com.testex.subservice.infrasrtucture.repository.SubscriptionRepository;
import com.testex.subservice.repository.model.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DataSubServiceImpl implements DataSubService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        return subscriptionRepository.save(subscriptionEntity);
    }

    @Override
    public boolean isExistsBySubID(UUID subID){
        return subscriptionRepository.existsBySubID(subID);
    }

    @Override
    public boolean isExistsByName(String subName){
        return subscriptionRepository.existsBySubName(subName);
    }


    @Override
    public Optional<SubscriptionEntity> findByID(UUID subID){
        return subscriptionRepository.findById(subID);
    }

    @Override
    public List<SubscriptionEntity> findAllSub(){
        return subscriptionRepository.findAll();
    }

    @Override
    public void deleteBySubID(UUID subID){
        subscriptionRepository.deleteBySubID(subID);
    }
}
