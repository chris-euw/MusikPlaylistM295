package com.christopher.musikplaylistappchristopher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PlaylistRepository extends JpaRepository<Playlist, String> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM playlist_song WHERE playlist_id = ?1", nativeQuery = true)
    void deletePlaylistSongRelations(String playlistId);
}