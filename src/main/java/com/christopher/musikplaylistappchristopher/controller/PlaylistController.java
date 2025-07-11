package com.christopher.musikplaylistappchristopher.controller;

import com.christopher.musikplaylistappchristopher.dto.FilterDTO;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import com.christopher.musikplaylistappchristopher.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistSongService playlistSongService;

    @Autowired
    private PlaylistRepository playlistRepository;

    // 1. Alle Playlists abrufen
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // 2. Neue Playlist erstellen
    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    // 3. Song zur Playlist hinzufügen (vereinfacht)
    @PostMapping("/playlists/{playlistId}/songs/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(
            @PathVariable String playlistId,
            @PathVariable String songId,
            @RequestBody(required = false) FilterDTO filterDTO // optional
    ) {
        Playlist updated = playlistSongService.addSongToPlaylist(playlistId, songId, filterDTO);
        return ResponseEntity.ok(updated);
    }

    // 4. Playlist löschen
    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable String id) {
        playlistRepository.deleteById(id);
    }

    // 5. Playlist bearbeiten (einfaches Update)
    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist) {
        playlist.setPlaylistID(id); // ID setzen
        return playlistRepository.save(playlist);
    }
}