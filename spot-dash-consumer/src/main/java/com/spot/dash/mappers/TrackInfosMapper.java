package com.spot.dash.mappers;

import com.spot.dash.model.entity.TrackInfos;
import com.spot.dash.model.dto.TrackInfosDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackInfosMapper {
    TrackInfosMapper INSTANCE = Mappers.getMapper(TrackInfosMapper.class);

    TrackInfos toModel(TrackInfosDto trackInfosDto);

    TrackInfosDto toDTO(TrackInfos trackInfos);
}