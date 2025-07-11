package com.christopher.musikplaylistappchristopher.controller;

import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import java.util.List;

import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import com.christopher.musikplaylistappchristopher.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongService playlistSongService;

    @PostMapping("/{playlistId}/songs")
    public Playlist addSong(@PathVariable String playlistId, @RequestBody String songId) {
        return playlistSongService.addSongToPlaylist(playlistId, songId);
    }

    @GetMapping
    public List<Playlist> getAllPlaylists(){
        return playlistRepository.findAll();
    }

    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist){
        return playlistRepository.save(playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable String id) {
        playlistRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist) {
        playlist.setPlaylistID(id);
        return playlistRepository.save(playlist);
    }
    @PostMapping("/{playlistId}/songs") //******
    public Playlist addSongToPlaylist(
            @PathVariable String playlistId,
            @RequestBody String songId
    ) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        // Doppelte Einträge verhindern
        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
        }

        return playlistRepository.save(playlist);
    }

}

