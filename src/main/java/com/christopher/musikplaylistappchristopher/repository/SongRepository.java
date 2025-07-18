package com.christopher.musikplaylistappchristopher.repository;

import com.christopher.musikplaylistappchristopher.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository-Interface für die Song-Entität.
 * Erweitert JpaRepository, was grundlegende CRUD-Operationen bereitstellt.
 * Enthält zusätzlich benutzerdefinierte Methoden zur Suche nach Songs und zur Pflege der Join-Tabelle.
 */
public interface SongRepository extends JpaRepository<Song, String> {

    /**
     * Findet alle Songs, deren Titel den angegebenen String (teilweise, unabhängig von Groß-/Kleinschreibung) enthalten.
     *
     * @param title Der Suchbegriff für den Titel.
     * @return Liste der passenden Songs.
     */
    List<Song> findByTitleContainingIgnoreCase(String title);

    /**
     * Findet alle Songs, deren Interpret den angegebenen String (teilweise, unabhängig von Groß-/Kleinschreibung) enthält.
     *
     * @param artist Der Suchbegriff für den Interpreten.
     * @return Liste der passenden Songs.
     */
    List<Song> findByArtistContainingIgnoreCase(String artist);

    /**
     * Findet alle Songs, deren Album den angegebenen String (teilweise, unabhängig von Groß-/Kleinschreibung) enthält.
     *
     * @param album Der Suchbegriff für das Album.
     * @return Liste der passenden Songs.
     */
    List<Song> findByAlbumContainingIgnoreCase(String album);

    /**
     * Löscht die Zuordnungen eines Songs aus der Join-Tabelle "playlist_songs".
     * Diese Methode wird benötigt, um vor dem Löschen eines Songs die Foreign-Key-Bindung aufzulösen.
     *
     * @param songId Die ID des Songs, dessen Beziehungen gelöscht werden sollen.
     */
    @Modifying // Kennzeichnet diese Query als schreibende Operation
    @Transactional // Führt die Operation in einer Transaktion aus
    @Query(value = "DELETE FROM playlist_songs WHERE song_id = ?1", nativeQuery = true)
    void deleteSongRelations(String songId);
}
