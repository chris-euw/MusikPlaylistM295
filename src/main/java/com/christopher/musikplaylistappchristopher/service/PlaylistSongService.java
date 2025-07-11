package com.christopher.musikplaylistappchristopher.service;

import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistSongService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    public Playlist addSongToPlaylist(String playlistId, String songId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
        }

        return playlistRepository.save(playlist);
    }
}

