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
    private PlaylistSongService playlistSongService; // Service für Song-Hinzufügen

    @Autowired
    private PlaylistRepository playlistRepository; // CRUD für Playlists

    // GET /api/playlists
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        // repository.findAll aufgerufen
        return playlistRepository.findAll();
    }

    // POST /api/playlists
    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        // repository.save aufgerufen
        return playlistRepository.save(playlist);
    }

    // POST /api/playlists/{playlistId}/songs/{songId}
    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(
            @PathVariable String playlistId,
            @PathVariable String songId,
            @RequestBody(required = false) FilterDTO filterDTO) {
        // Service wird aufgerufen
        Playlist updated = playlistSongService.addSongToPlaylist(playlistId, songId, filterDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/playlists/{id}
    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable String id) {
        // repository.deleteById aufgerufen
        playlistRepository.deleteById(id);
    }

    // PUT /api/playlists/{id}
    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist) {
        // ID gesetzt und repository.save aufgerufen
        playlist.setPlaylistID(id);
        return playlistRepository.save(playlist);
    }
}
