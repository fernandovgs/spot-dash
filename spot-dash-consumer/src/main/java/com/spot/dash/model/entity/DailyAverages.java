package com.spot.dash.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "dailyAverages")
public class DailyAverages {

    @Id
    private String id;

    private String analysisDate;
    private Float avgEnergy;
    private Float avgValence;
}
