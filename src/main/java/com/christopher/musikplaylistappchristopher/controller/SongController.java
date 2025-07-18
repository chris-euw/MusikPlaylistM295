package com.christopher.musikplaylistappchristopher.controller;

import java.util.List;

import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller für Song-bezogene Endpunkte.
 * Bietet CRUD-Funktionalitäten und Batch-Operationen für Songs.
 */
@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository; // Repository für Song-Datenzugriffe

    /**
     * GET /api/songs
     * Gibt eine Liste aller gespeicherten Songs zurück.
     *
     * @return Liste von Song-Objekten
     */
    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll(); // Holt alle Songs aus der Datenbank
    }

    /**
     * GET /api/songs/{id}
     * Gibt einen einzelnen Song anhand seiner ID zurück.
     *
     * @param id Die ID des gesuchten Songs
     * @return Song-Objekt, falls vorhanden
     * @throws RuntimeException falls der Song nicht gefunden wird
     */
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }

    /**
     * POST /api/songs
     * Speichert einen neuen Song basierend auf den übergebenen Daten.
     *
     * @param song Song-Objekt aus dem Request-Body
     * @return Der gespeicherte Song mit generierter ID
     */
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song); // Persistiert den Song
    }

    /**
     * DELETE /api/songs
     * Löscht eine Liste von Songs anhand übergebener IDs.
     *
     * @param ids Liste von Song-IDs, die gelöscht werden sollen
     * @return HTTP 204 (No Content) bei Erfolg
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSongs(@RequestBody List<String> ids) {
        songRepository.deleteAllById(ids); // Löscht alle Songs mit angegebenen IDs
        return ResponseEntity.noContent().build(); // Rückgabe ohne Body
    }

    /**
     * PUT /api/songs/{id}
     * Aktualisiert einen bestehenden Song anhand seiner ID.
     *
     * @param id   ID des zu aktualisierenden Songs
     * @param song Song-Objekt mit aktualisierten Feldern
     * @return Der aktualisierte Song
     */
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable String id, @RequestBody Song song) {
        song.setSongID(id); // Setzt sicher, dass der Song mit der gegebenen ID aktualisiert wird
        return songRepository.save(song); // Speichert das aktualisierte Objekt
    }

    /**
     * POST /api/songs/batch
     * Speichert mehrere Songs gleichzeitig in der Datenbank.
     *
     * @param songs Liste von Songs im Request-Body
     * @return Liste der erfolgreich gespeicherten Songs
     */
    @PostMapping("/batch")
    public List<Song> createMultipleSongs(@RequestBody List<Song> songs) {
        return songRepository.saveAll(songs); // Speichert alle Songs in einem Vorgang
    }

}
