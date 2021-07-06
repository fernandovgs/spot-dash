package com.spot.dash.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyAveragesListDto {

    List<DailyAveragesDto> dailyAveragesDtos;
}
