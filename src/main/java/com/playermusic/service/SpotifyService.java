package com.playermusic.service;

import com.playermusic.model.Song;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyService {

    private static final String CLIENT_ID = "0347b7744f0e4b12b4caa43399fb3ad0";
    private static final String CLIENT_SECRET = "2d95436afd164177ae5909c05cc98ea9";

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build();

    private void clientCredentials_Sync() {
        try {
            var clientCredentialsRequest = spotifyApi.clientCredentials().build();
            var clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List<Song> searchSongs(String query) {
        clientCredentials_Sync();
        List<Song> results = new ArrayList<>();
        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query).limit(10).build();
            Paging<Track> trackPaging = searchTracksRequest.execute();

            for (Track track : trackPaging.getItems()) {
                Song song = new Song();
                song.setId(track.getId());
                song.setTitle(track.getName());
                song.setArtist(track.getArtists()[0].getName());
                song.setCoverUrl(track.getAlbum().getImages().length > 0 ? track.getAlbum().getImages()[0].getUrl() : "https://via.placeholder.com/300");

                if (track.getPreviewUrl() != null) {
                    song.setPreviewUrl(track.getPreviewUrl());
                } else {
                    song.setPreviewUrl("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
                }

                results.add(song);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return results;
    }
}