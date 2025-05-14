package com.testex.subservice.repository.mapper;

import com.testex.subservice.DTO.SubscriptionDTO;
import com.testex.subservice.repository.model.SubscriptionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperSubData {
    SubscriptionDTO toDTO(SubscriptionEntity subscriptionEntity);

    SubscriptionEntity toData(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> toDTO(List<SubscriptionEntity> subscriptionEntityList);

    List<SubscriptionEntity> toEntity(List<SubscriptionDTO> subscriptionDTOList);
}
