package com.testex.subservice.infrasrtucture.init;

import com.testex.subservice.repository.model.SubscriptionEntity;
import com.testex.subservice.repository.service.subs.subscription.DataSubService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriptionSeeder {
    private final DataSubService dataSubService;

    @PostConstruct
    public void seedSubscriptions() {
            List<String> defaultSubscriptions = List.of("Yandex Plus", "YouTube Premium", "Netflix", "VK Music");
            for (String subscriptionName : defaultSubscriptions) {
                SubscriptionEntity subscription = new SubscriptionEntity();
                subscription.setSubName(subscriptionName);
                if (!dataSubService.isExistsByName(subscriptionName)) {
                    dataSubService.save(subscription);
                }
            }
    }
}
