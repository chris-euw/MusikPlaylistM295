package com.christopher.musikplaylistappchristopher.controller;

import com.christopher.musikplaylistappchristopher.dto.FilterDTO;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.PlaylistRepository;
import com.christopher.musikplaylistappchristopher.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller zur Verwaltung von Playlists und deren Songs.
 * Behandelt alle Anfragen an /api/playlists.
 */
@CrossOrigin(origins = "http://localhost:5173") // Erlaubt CORS-Anfragen vom Frontend (Port 5173)
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistSongService playlistSongService; // Service-Klasse zur Verwaltung der Song-Zuordnung

    @Autowired
    private PlaylistRepository playlistRepository; // Repository für CRUD-Operationen auf Playlists

    /**
     * GET /api/playlists
     * Gibt eine Liste aller Playlists zurück.
     *
     * @return Liste von Playlist-Objekten
     */
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    /**
     * POST /api/playlists
     * Erstellt eine neue Playlist basierend auf dem im Request-Body gesendeten Objekt.
     *
     * @param playlist Die zu speichernde Playlist
     * @return Die gespeicherte Playlist mit generierter ID
     */
    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    /**
     * POST /api/playlists/{playlistId}/songs/{songId}
     * Fügt einen Song einer Playlist hinzu. Optional kann ein FilterDTO mitgegeben werden.
     *
     * @param playlistId ID der Ziel-Playlist
     * @param songId     ID des hinzuzufügenden Songs
     * @param filterDTO  Optionaler Filter für die Logik im Service
     * @return Die aktualisierte Playlist mit dem neu hinzugefügten Song
     */
    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(
            @PathVariable String playlistId,
            @PathVariable String songId,
            @RequestBody(required = false) FilterDTO filterDTO) {
        Playlist updated = playlistSongService.addSongToPlaylist(playlistId, songId, filterDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/playlists/{id}
     * Löscht eine Playlist anhand der ID. Dabei werden auch die Beziehungen in der Zwischentabelle entfernt.
     *
     * @param id ID der zu löschenden Playlist
     */
    @DeleteMapping("/{id}")
    @Transactional // Transaktional, damit beide Löschvorgänge zusammen behandelt werden
    public void deletePlaylist(@PathVariable String id) {
        playlistRepository.deletePlaylistSongRelations(id); // Entfernt Einträge aus playlist_songs
        playlistRepository.deleteById(id);                  // Entfernt die Playlist selbst
    }

    /**
     * PUT /api/playlists/{id}
     * Aktualisiert eine bestehende Playlist anhand der ID mit neuen Daten aus dem Request-Body.
     *
     * @param id       ID der zu aktualisierenden Playlist
     * @param playlist Playlist-Objekt mit neuen Werten
     * @return Die aktualisierte Playlist
     */
    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist) {
        playlist.setPlaylistID(id); // Sicherstellen, dass die ID korrekt gesetzt ist
        return playlistRepository.save(playlist);
    }

    /**
     * GET /api/playlists/{playlistId}/songs
     * Gibt alle Songs zurück, die einer bestimmten Playlist zugeordnet sind.
     *
     * @param playlistId ID der Playlist
     * @return Liste von Songs in der Playlist
     */
    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<Song>> getSongsForPlaylist(@PathVariable String playlistId) {
        List<Song> songs = playlistSongService.getSongsForPlaylist(playlistId);
        return ResponseEntity.ok(songs);
    }
}
