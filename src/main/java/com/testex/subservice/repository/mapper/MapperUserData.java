package com.testex.subservice.repository.mapper;

import com.testex.subservice.DTO.UserDTO;
import com.testex.subservice.repository.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUserData {
    UserDTO toDTO(UserEntity userEntity);
    UserEntity toData(UserDTO userDTO);
}
