package com.spot.dash.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "track-infos")
public class TrackInfos {

    @Transient
    public static final String SEQUENCE_NAME = "track-infos_sequence";

    @Id
    private String id;

    private String name;
    private String artistName;
    private Float liveness;
}
