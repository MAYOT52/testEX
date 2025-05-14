package com.testex.subservice.repository.mapper;

import com.testex.subservice.DTO.SubEventDTO;
import com.testex.subservice.repository.model.SubEventEntity;
import com.testex.subservice.web.model.sub.SubEventWeb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperSubEventData {
    SubEventDTO toDTO(SubEventEntity subEventEntity);

    SubEventWeb toEntity(SubEventDTO subEventDTO);
}
