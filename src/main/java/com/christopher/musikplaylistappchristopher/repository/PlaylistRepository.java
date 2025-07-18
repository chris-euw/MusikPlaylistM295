package com.christopher.musikplaylistappchristopher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.christopher.musikplaylistappchristopher.model.Playlist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository-Interface für die Playlist-Entität.
 * Erweitert JpaRepository und bietet somit Standard-CRUD-Methoden (z. B. save, findById, deleteById).
 * Weitere benutzerdefinierte Queries können ergänzt werden.
 */
public interface PlaylistRepository extends JpaRepository<Playlist, String> {

    /**
     * Löscht explizit alle Verknüpfungen einer Playlist zu Songs
     * aus der Join-Tabelle "playlist_songs".
     *
     * Diese native Query ist erforderlich, um bei der Löschung einer Playlist
     * auch deren Zuordnungen zu Songs zu entfernen und Foreign Key Constraints zu vermeiden.
     *
     * @param playlistId Die ID der Playlist, deren Song-Zuordnungen gelöscht werden sollen.
     */
    @Modifying // Kennzeichnet diese Query als schreibende Operation (INSERT, UPDATE, DELETE)
    @Transactional // Stellt sicher, dass diese Operation in einer Transaktion ausgeführt wird
    @Query(value = "DELETE FROM playlist_songs WHERE playlist_id = ?1", nativeQuery = true)
    void deletePlaylistSongRelations(String playlistId);
}
