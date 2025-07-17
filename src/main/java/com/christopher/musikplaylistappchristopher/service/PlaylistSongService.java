package com.christopher.musikplaylistappchristopher.service;

import com.christopher.musikplaylistappchristopher.dto.FilterDTO;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service // Business-Logik
public class PlaylistSongService {

    @Autowired
    private PlaylistRepository playlistRepository; // Aufgerufen in addSongToPlaylist

    @Autowired
    private SongRepository songRepository; // Aufgerufen in addSongToPlaylist

    /**
     * Fügt einen Song einer Playlist hinzu.
     * Aufgerufen von: PlaylistController.addSongToPlaylist
     */
    @Transactional
    public Playlist addSongToPlaylist(String playlistId, String songId, FilterDTO filterDTO) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        // Exception von GlobalExceptionHandler in HTTP 404 übersetzt

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song); // Verhältnis aktualisieren
        }

        return playlistRepository.save(playlist); // Persistenz durch Repository
    }
}

