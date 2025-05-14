package com.testex.subservice.domain.mapper;

import com.testex.subservice.DTO.UserDTO;
import com.testex.subservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUserDomain {
    UserDTO toDTO(User user);
    User toDomain(UserDTO userDTO);
}
