package com.spot.dash.spotdashproducer.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SpotDashProducerService {

    @Value("${spotify.user}")
    private String spotifyUser;

    @Value("${spotify.secret}")
    private String spotifySecret;

    public SpotifyApi authenticationTest() throws IOException, ParseException, SpotifyWebApiException {
        var spotifyApi = new SpotifyApi.Builder()
                .setClientId(spotifyUser)
                .setClientSecret(spotifySecret)
                .build();
        var clientCredentialsRequest = spotifyApi.clientCredentials().build();

        final var clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());

        return spotifyApi;
    }
}
