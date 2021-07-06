package com.spot.dash.spotdashproducer.mappers;

import com.spot.dash.spotdashproducer.model.dto.UserDto;
import com.spot.dash.spotdashproducer.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDto userDto);

    UserDto toDTO(User user);
}
