package com.spot.dash.controller;

import com.spot.dash.model.dto.DailyAveragesListDto;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.spot.dash.service.TrackInfosService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.spot.dash.constants.Constants.BASE_URI;
import static com.spot.dash.constants.Constants.TRACK_INFOS_URI;


@RestController
@RequestMapping(BASE_URI + TRACK_INFOS_URI)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TrackInfosController {

    private final TrackInfosService trackInfosService;

    @GetMapping
    public TrackInfosListDto getTrendingTracksInfos(
            @RequestParam Optional<String> receivedDate
    ) {
        return trackInfosService.getAllTrackInfosFromADay(receivedDate);
    }
}
