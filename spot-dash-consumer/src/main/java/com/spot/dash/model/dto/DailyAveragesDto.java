package com.spot.dash.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyAveragesDto {

    private String id;
    private String analysisDate;
    private Float avgEnergy;
    private Float avgValence;
}
