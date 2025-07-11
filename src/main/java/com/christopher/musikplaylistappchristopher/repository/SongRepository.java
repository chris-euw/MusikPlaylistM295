package com.christopher.musikplaylistappchristopher.repository;

import com.christopher.musikplaylistappchristopher.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, String> {

    // Suche nach Song-Titel
    List<Song> findByTitleContainingIgnoreCase(String title);

    // Suche nach Artist
    List<Song> findByArtistContainingIgnoreCase(String artist);

    // Suche nach Album
    List<Song> findByAlbumContainingIgnoreCase(String album);
}