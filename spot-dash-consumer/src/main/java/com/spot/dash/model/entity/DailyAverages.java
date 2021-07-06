package com.spot.dash.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "daily-averages")
public class DailyAverages {

    @Id
    private String id;

    private LocalDate analysisDate;
    private Float avgEnergy;
    private Float avgValence;
}
