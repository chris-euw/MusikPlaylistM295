package com.christopher.musikplaylistappchristopher.controller;

import java.util.List;

import com.christopher.musikplaylistappchristopher.model.Song;
import com.christopher.musikplaylistappchristopher.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    // 1. Alle Songs anzeigen
    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // 2. Song erstellen
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    // 3. Song nach Titel suchen
    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String artist,
                                  @RequestParam(required = false) String album) {
        if (title != null) return songRepository.findByTitleContainingIgnoreCase(title);
        if (artist != null) return songRepository.findByArtistContainingIgnoreCase(artist);
        if (album != null) return songRepository.findByAlbumContainingIgnoreCase(album);
        return songRepository.findAll(); // fallback
    }

    // 4. Song löschen
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable String id) {
        songRepository.deleteById(id);
    }

    // 5. Song bearbeiten (optional)
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable String id, @RequestBody Song song) {
        song.setSongID(id);
        return songRepository.save(song);
    }
}
