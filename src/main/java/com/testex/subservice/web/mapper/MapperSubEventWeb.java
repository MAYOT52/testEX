package com.testex.subservice.web.mapper;


import com.testex.subservice.DTO.SubEventDTO;
import com.testex.subservice.web.model.sub.SubEventWeb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperSubEventWeb {
    SubEventDTO toDTO(SubEventWeb subEventWeb);
    SubEventWeb toWeb(SubEventDTO subEventDTO);
}
