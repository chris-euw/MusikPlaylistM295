package com.christopher.musikplaylistappchristopher.service;

import com.christopher.musikplaylistappchristopher.dto.FilterDTO;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse für die Verwaltung der Beziehung zwischen Playlists und Songs.
 * Enthält die Geschäftslogik für das Hinzufügen von Songs zu Playlists
 * sowie das Abrufen der Songs einer bestimmten Playlist.
 */
@Service // Kennzeichnet diese Klasse als Service-Komponente (für Dependency Injection)
public class PlaylistSongService {

    @Autowired
    private PlaylistRepository playlistRepository; // Zugriff auf Playlist-Daten

    @Autowired
    private SongRepository songRepository; // Zugriff auf Song-Daten

    /**
     * Fügt einen bestimmten Song zu einer Playlist hinzu.
     *
     * @param playlistId Die ID der Ziel-Playlist
     * @param songId     Die ID des hinzuzufügenden Songs
     * @param filterDTO  Optionales Filterobjekt (aktuell nicht verwendet)
     * @return Die aktualisierte Playlist mit dem neuen Song
     */
    @Transactional // Sichert, dass alle DB-Operationen in einer Transaktion ausgeführt werden
    public Playlist addSongToPlaylist(String playlistId, String songId, FilterDTO filterDTO) {
        // Hole die Ziel-Playlist oder wirf eine Exception
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        // Die Exception wird zentral durch GlobalExceptionHandler behandelt

        // Hole den Song anhand der ID oder wirf eine Exception
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        // Füge den Song nur hinzu, wenn er noch nicht enthalten ist
        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song); // Beziehung hinzufügen
        }

        // Speichere die aktualisierte Playlist
        return playlistRepository.save(playlist);
    }

    /**
     * Gibt alle Songs zurück, die einer bestimmten Playlist zugeordnet sind.
     *
     * @param playlistId Die ID der Playlist
     * @return Liste der Songs, die in dieser Playlist enthalten sind
     */
    public List<Song> getSongsForPlaylist(String playlistId) {
        return playlistRepository.findById(playlistId)
                .map(Playlist::getSongs) // Hole Songs aus der Playlist
                .orElseThrow(() -> new IllegalArgumentException("Playlist nicht gefunden"));
    }
}
