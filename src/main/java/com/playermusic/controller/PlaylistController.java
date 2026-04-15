package com.playermusic.controller;

import com.playermusic.model.Song;
import com.playermusic.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/playlist")
@CrossOrigin(origins = "http://localhost:5173") // Para que React pueda conectar
public class PlaylistController {

    @Autowired
    private SpotifyService spotifyService;

    // Simulación de Lista Doblemente Enlazada
    private LinkedList<Song> playlist = new LinkedList<>();
    private int currentIndex = -1;

    @GetMapping("/search/{query}")
    public List<Song> search(@PathVariable String query) {
        return spotifyService.searchSongs(query);
    }

    @PostMapping("/add-spotify/{query}")
    public void addByTitle(@PathVariable String query) {
        List<Song> found = spotifyService.searchSongs(query);
        if (!found.isEmpty()) {
            playlist.add(found.get(0));
        }
    }

    @GetMapping("/all")
    public List<Song> getAll() {
        return playlist;
    }

    @PostMapping("/sync/{id}")
    public void sync(@PathVariable String id) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getId().equals(id)) {
                currentIndex = i;
                break;
            }
        }
    }

    @GetMapping("/next")
    public Song next() {
        if (playlist.isEmpty()) return null;
        currentIndex = (currentIndex + 1) % playlist.size();
        return playlist.get(currentIndex);
    }

    @GetMapping("/previous")
    public Song previous() {
        if (playlist.isEmpty()) return null;
        currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
        return playlist.get(currentIndex);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        playlist.removeIf(s -> s.getId().equals(id));
        if (playlist.isEmpty()) currentIndex = -1;
    }
}