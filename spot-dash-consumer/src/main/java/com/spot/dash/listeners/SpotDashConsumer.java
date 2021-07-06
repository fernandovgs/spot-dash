package com.spot.dash.listeners;

import com.spot.dash.mappers.TrackInfosMapper;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.spot.dash.model.entity.DailyAverages;
import com.spot.dash.model.entity.TrackInfos;
import com.spot.dash.model.repository.DailyAveragesRepository;
import com.spot.dash.model.repository.TrackInfosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.spot.dash.constants.Constants.TOPIC_TRACKS;
import static com.spot.dash.constants.Constants.GROUP_ID;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class SpotDashConsumer {

    @Autowired
    private static final TrackInfosMapper trackInfosMapper = TrackInfosMapper.INSTANCE;

    @Autowired
    private TrackInfosRepository trackInfosRepository;

    @Autowired
    private DailyAveragesRepository dailyAveragesRepository;

    @KafkaListener(topics = TOPIC_TRACKS, groupId = GROUP_ID)
    public void getSpotDashDailyTrackInfos(TrackInfosListDto trackInfosListDto) {
        List<TrackInfos> trackInfosList = new ArrayList<>();
        trackInfosListDto.getTrackInfosDtos()
                .forEach(trackInfosDto -> trackInfosList
                        .add(trackInfosMapper.toModel(trackInfosDto)));

        // Saving all track lists in repository
        trackInfosRepository.saveAll(trackInfosList);

        // Saving average values in repository
        var dailyAverages = DailyAverages.builder()
                .id(UUID.randomUUID().toString())
                .analysisDate(LocalDate.now().toString())
                .avgEnergy((float) trackInfosList.stream()
                        .mapToDouble(TrackInfos::getEnergy)
                        .average()
                        .getAsDouble())
                .avgValence((float) trackInfosList.stream()
                        .mapToDouble(TrackInfos::getValence)
                        .average()
                        .getAsDouble())
                .build();

        dailyAveragesRepository.save(dailyAverages);
    }
}
