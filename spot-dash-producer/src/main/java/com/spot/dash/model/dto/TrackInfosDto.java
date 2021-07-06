package com.spot.dash.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackInfosDto {

    private String id;
    private String name;
    private String artistName;
    private Float energy;
    private Float valence;
    private LocalDate receivedDate;
}
