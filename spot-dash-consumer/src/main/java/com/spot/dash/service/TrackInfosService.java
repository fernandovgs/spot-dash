package com.spot.dash.service;

import com.spot.dash.mappers.DailyAveragesMapper;
import com.spot.dash.mappers.TrackInfosMapper;
import com.spot.dash.model.dto.DailyAveragesDto;
import com.spot.dash.model.dto.DailyAveragesListDto;
import com.spot.dash.model.dto.TrackInfosDto;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.spot.dash.model.repository.DailyAveragesRepository;
import com.spot.dash.model.repository.TrackInfosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TrackInfosService {

    @Autowired
    private static final TrackInfosMapper trackInfosMapper = TrackInfosMapper.INSTANCE;

    @Autowired
    private TrackInfosRepository trackInfosRepository;

    @Autowired
    private DailyAveragesRepository dailyAveragesRepository;

    public TrackInfosListDto getAllTrackInfosFromADay(Optional<String> receivedDate) {
        var tracksInfos = trackInfosRepository
                .findByReceivedDate(receivedDate.orElseGet(() -> LocalDate.now().toString()));

        List<TrackInfosDto> trackInfosDtos = new ArrayList<>();

        tracksInfos.forEach((trackInfos -> trackInfosDtos.add(trackInfosMapper.toDTO(trackInfos))));

        return TrackInfosListDto.builder()
                .trackInfosDtos(trackInfosDtos)
                .build();
    }
}
