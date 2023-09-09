package com.othex.reserveme.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.othex.reserveme.dto.UserDTO;
import com.othex.reserveme.entities.User;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    
    User toDao(UserDTO user);
}
