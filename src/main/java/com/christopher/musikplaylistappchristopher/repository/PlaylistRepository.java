package com.christopher.musikplaylistappchristopher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import java.util.List;


public interface PlaylistRepository extends JpaRepository<Playlist, String> {

    List<Playlist> findByPlaylistNameContainingIgnoreCase(String name);

}