package com.spot.dash.controller;

import com.spot.dash.model.dto.DailyAveragesListDto;
import com.spot.dash.service.DailyAveragesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.spot.dash.constants.Constants.ALL_AVG_URI;
import static com.spot.dash.constants.Constants.BASE_URI;
import static com.spot.dash.constants.Constants.DAILY_AVG_URI;

@RestController
@RequestMapping(BASE_URI + DAILY_AVG_URI)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DailyAveragesController {

    private final DailyAveragesService dailyAveragesService;

    @GetMapping
    public DailyAveragesListDto getDailyAverages(
            @RequestParam Optional<String> analysisDate
    ) {
        return dailyAveragesService.getDailyAverages(analysisDate);
    }

    @GetMapping(ALL_AVG_URI)
    public DailyAveragesListDto getDailyAverages() {
        return dailyAveragesService.getAllAverages();
    }
}
