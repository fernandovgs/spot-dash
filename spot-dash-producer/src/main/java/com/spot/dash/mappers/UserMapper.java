package com.spot.dash.mappers;

import com.spot.dash.model.entity.User;
import com.spot.dash.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDto userDto);

    UserDto toDTO(User user);
}
