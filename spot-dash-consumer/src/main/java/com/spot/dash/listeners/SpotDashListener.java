package com.spot.dash.listeners;

import com.spot.dash.model.dto.TrackInfosListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.spot.dash.constants.Constants.TOPIC_TRACKS;
import static com.spot.dash.constants.Constants.GROUP_ID;

@Component
@Slf4j
public class SpotDashListener {
    @KafkaListener(topics = TOPIC_TRACKS, groupId = GROUP_ID)
    public void getSpotDashDailyTrackInfos(TrackInfosListDto trackInfosMapDtos) {
        trackInfosMapDtos.getTrackInfosDtos().forEach(trackInfosDto -> log.info(trackInfosDto.getName()));
    }
}
