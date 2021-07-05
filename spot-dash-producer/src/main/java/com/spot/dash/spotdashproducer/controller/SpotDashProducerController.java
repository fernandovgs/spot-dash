package com.spot.dash.spotdashproducer.controller;

import com.spot.dash.spotdashproducer.service.SpotDashProducerService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.AllArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/spot-dash")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpotDashProducerController {

    private final SpotDashProducerService spotDashProducerService;

    @PostMapping("/auth")
    public String authenticate() throws IOException, ParseException, SpotifyWebApiException {
        return spotDashProducerService.authenticationTest().getAccessToken();
    }
}
