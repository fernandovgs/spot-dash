package com.spot.dash.spotdashproducer.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SpotDashProducerService {

    @Value("${spotify.user}")
    private String spotifyUser;

    @Value("${spotify.secret}")
    private String spotifySecret;

    @Value("${spotify.playlist-trending-50}")
    private String spotifyTrending50PlaylistListId;

    public List<PlaylistTrack> getTrackInfos() throws IOException, ParseException, SpotifyWebApiException {
        var spotifyApi = getAuthenticatedSpotifyApi();
        var getPlaylistsItemsRequest = spotifyApi
                .getPlaylistsItems(spotifyTrending50PlaylistListId)
                .build();

        final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();

        return Arrays.asList(playlistTrackPaging.getItems());
    }

    private SpotifyApi getAuthenticatedSpotifyApi() throws IOException, ParseException, SpotifyWebApiException {
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
