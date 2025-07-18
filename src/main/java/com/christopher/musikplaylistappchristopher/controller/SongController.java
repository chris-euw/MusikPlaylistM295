package com.christopher.musikplaylistappchristopher.controller;

import java.util.List;

import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository; // CRUD für Songs

    // GET /api/songs
    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll(); // repository.findAll
    }

    // GET /api/songs/{id}
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }

    // POST /api/songs
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song); // repository.save
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSongs(@RequestBody List<String> ids) {
        songRepository.deleteAllById(ids);
        return ResponseEntity.noContent().build();
    }


    // PUT /api/songs/{id}
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable String id, @RequestBody Song song) {
        song.setSongID(id); // ID beibehalten
        return songRepository.save(song); // repository.save
    }
    // POST /api/songs/batch
    @PostMapping("/batch")
    public List<Song> createMultipleSongs(@RequestBody List<Song> songs) {
        return songRepository.saveAll(songs); // Mehrere Songs speichern
    }
}
