package com.spot.dash.mappers;

import com.spot.dash.model.dto.DailyAveragesDto;
import com.spot.dash.model.entity.DailyAverages;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DailyAveragesMapper {
    DailyAveragesMapper INSTANCE = Mappers.getMapper(DailyAveragesMapper.class);

    DailyAverages toModel(DailyAveragesDto dailyAveragesDto);

    DailyAveragesDto toDTO(DailyAverages dailyAverages);
}
