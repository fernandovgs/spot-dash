package com.spot.dash.listeners;

import com.spot.dash.mappers.TrackInfosMapper;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.spot.dash.model.entity.TrackInfos;
import com.spot.dash.model.repository.TrackInfosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.spot.dash.constants.Constants.TOPIC_TRACKS;
import static com.spot.dash.constants.Constants.GROUP_ID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SpotDashListener {

    @Autowired
    private static final TrackInfosMapper trackInfosMapper = TrackInfosMapper.INSTANCE;

    @Autowired
    private TrackInfosRepository trackInfosRepository;

    @KafkaListener(topics = TOPIC_TRACKS, groupId = GROUP_ID)
    public void getSpotDashDailyTrackInfos(TrackInfosListDto trackInfosListDto) {
        trackInfosListDto.getTrackInfosDtos().forEach(trackInfosDto -> log.info(trackInfosDto.getName()));

        List<TrackInfos> trackInfosList = new ArrayList<>();
        trackInfosListDto.getTrackInfosDtos()
                .forEach(trackInfosDto -> trackInfosList
                        .add(trackInfosMapper.toModel(trackInfosDto)));

        trackInfosRepository.saveAll(trackInfosList);
    }
}
