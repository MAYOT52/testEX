package com.testex.subservice.web.mapper;

import com.testex.subservice.DTO.SubscriptionDTO;
import com.testex.subservice.web.model.sub.SubscriptionWeb;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperSubWeb {
    SubscriptionDTO toDTO(SubscriptionWeb subscriptionWeb);

    SubscriptionWeb toWeb(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> toDTO(List<SubscriptionWeb> subscriptionWebList);
    List<SubscriptionWeb> toWeb(List<SubscriptionDTO> subscriptionDTOList);
}
