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
public class DailyAveragesService {

    @Autowired
    private static final DailyAveragesMapper dailyAveragesMapper = DailyAveragesMapper.INSTANCE;

    @Autowired
    private DailyAveragesRepository dailyAveragesRepository;

    public DailyAveragesListDto getDailyAverages(Optional<String> analysisDate) {
        var dailyAverages = dailyAveragesRepository
                .findByAnalysisDate(analysisDate.orElseGet(() -> LocalDate.now().toString()));

        List<DailyAveragesDto> dailyAveragesDtos = new ArrayList<>();

        dailyAverages.forEach((trackInfos -> dailyAveragesDtos.add(dailyAveragesMapper.toDTO(trackInfos))));

        return DailyAveragesListDto.builder()
                .dailyAveragesDtos(dailyAveragesDtos)
                .build();
    }

    public DailyAveragesListDto getAllAverages() {
        var dailyAverages = dailyAveragesRepository.findAll();

        List<DailyAveragesDto> dailyAveragesDtos = new ArrayList<>();

        dailyAverages.forEach((trackInfos -> dailyAveragesDtos.add(dailyAveragesMapper.toDTO(trackInfos))));

        return DailyAveragesListDto.builder()
                .dailyAveragesDtos(dailyAveragesDtos)
                .build();
    }
}
