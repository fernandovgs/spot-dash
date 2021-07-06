package com.spot.dash.controller;

import com.spot.dash.model.dto.DailyAveragesDto;
import com.spot.dash.model.dto.DailyAveragesListDto;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.spot.dash.service.SpotDashConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/spot-dash")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpotDashConsumerController {

    private final SpotDashConsumerService spotDashConsumerService;

    @GetMapping("/get-track-infos")
    public TrackInfosListDto getTrendingTracksInfos(
            @RequestParam Optional<String> receivedDate
    ) {
        return spotDashConsumerService.getAllTrackInfosFromADay(receivedDate);
    }

    @GetMapping("/get-daily-averages")
    public DailyAveragesListDto getDailyAverages(
            @RequestParam Optional<String> analysisDate
    ) {
        return spotDashConsumerService.getDailyAverages(analysisDate);
    }

    @GetMapping("/get-all-averages")
    public DailyAveragesListDto getDailyAverages() {
        return spotDashConsumerService.getAllAverages();
    }
}
