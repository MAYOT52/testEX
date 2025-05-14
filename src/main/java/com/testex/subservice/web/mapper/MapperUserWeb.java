package com.testex.subservice.web.mapper;

import com.testex.subservice.DTO.UserDTO;
import com.testex.subservice.web.model.user.UserWeb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUserWeb {
    UserDTO toDTO(UserWeb userWeb);
    UserWeb toWeb(UserDTO userDTO);
}
