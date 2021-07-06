package com.spot.dash.service;

import com.spot.dash.model.dto.TrackInfosDto;
import com.spot.dash.model.dto.TrackInfosListDto;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private KafkaTemplate<String, TrackInfosListDto> kafkaTemplate;

    private static final String TOPIC_TRACKS = "tracks";

    @Scheduled(cron = "0 0 12 * * *")
    public List<TrackInfosDto> getTrackInfos() throws IOException, ParseException, SpotifyWebApiException {
        var tracks = getTracks();
        var audiosFeatures = getAudioFeatures(tracks);

        List<TrackInfosDto> trackInfosDtos = new ArrayList<>();


        tracks.forEach((s, track) -> {
            var audioFeatures = audiosFeatures.get(s);

            trackInfosDtos.add(
                    TrackInfosDto.builder()
                            .id(s)
                            .name(track.getName())
                            .artistName(Arrays
                                    .stream(track.getArtists())
                                    .collect(Collectors.toList()).get(0).getName()
                            )
                            .energy(audioFeatures.getEnergy())
                            .valence(audioFeatures.getValence())
                            .receivedDate(LocalDate.now())
                            .build()
            );
        });

        var trackInfosListDto = TrackInfosListDto.builder()
                .trackInfosDtos(trackInfosDtos)
                .build();

        kafkaTemplate.send(TOPIC_TRACKS, trackInfosListDto);

        return trackInfosDtos;
    }

    private Map<String, AudioFeatures> getAudioFeatures(Map<String, Track> playlistTracks)
            throws IOException, ParseException, SpotifyWebApiException {
        var spotifyApi = getAuthenticatedSpotifyApi();

        var tracksIdsConcatenated = String.join(",", playlistTracks.keySet());
        var getAudioFeaturesForSeveralTracksRequest = spotifyApi
                .getAudioFeaturesForSeveralTracks(tracksIdsConcatenated)
                .build();

        return Arrays
                .stream(getAudioFeaturesForSeveralTracksRequest.execute())
                .collect(Collectors.toMap(AudioFeatures::getId, Function.identity()));
    }

    private Map<String, Track> getTracks() throws IOException, ParseException, SpotifyWebApiException {
        var spotifyApi = getAuthenticatedSpotifyApi();
        var getPlaylistsItemsRequest = spotifyApi
                .getPlaylistsItems(spotifyTrending50PlaylistListId)
                .build();

        return Arrays
                .stream(getPlaylistsItemsRequest.execute().getItems())
                .map(playlistTrack -> (Track) playlistTrack.getTrack())
                .collect(Collectors.toMap(Track::getId, Function.identity()));
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
