package com.christopher.musikplaylistappchristopher.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

/**
 * Repräsentiert eine Musik-Playlist als JPA-Entität.
 * Wird von Hibernate für die Datenbankpersistenz verwendet.
 */
@Entity // Kennzeichnet diese Klasse als JPA-Entität (Tabelle in der Datenbank)
public class Playlist {

    @Id // Primärschlüssel der Entität
    @GeneratedValue(generator = "UUID") // UUID-Generator für eindeutige IDs
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String playlistID; // Eindeutige ID der Playlist (z. B. für Zuordnung, URL)

    private String playlistName;        // Name der Playlist (Benutzereingabe)

    @JsonFormat(pattern = "yyyy-MM-dd") // Format für JSON-Datumsausgabe
    private LocalDate playlistCreation; // Erstellungsdatum der Playlist

    private String playlistDescription; // Beschreibung der Playlist

    private boolean privacyState;       // Gibt an, ob die Playlist öffentlich oder privat ist

    /**
     * Beziehung zu Songs – Many-to-Many.
     * Eine Playlist kann mehrere Songs enthalten, und ein Song kann in mehreren Playlists sein.
     * In der Zwischentabelle "playlist_songs" werden die Zuordnungen gespeichert.
     */
    @ManyToMany
    @JoinTable(
            name = "playlist_songs", // Name der Zwischentabelle
            joinColumns = @JoinColumn(name = "playlist_id"), // Verweist auf Playlist
            inverseJoinColumns = @JoinColumn(name = "song_id") // Verweist auf Song
    )
    private List<Song> songs; // Liste der enthaltenen Songs

    // Getter & Setter
    public String getPlaylistID() { return playlistID; }
    public void setPlaylistID(String playlistID) { this.playlistID = playlistID; }

    public String getPlaylistName() { return playlistName; }
    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }

    public LocalDate getPlaylistCreation() { return playlistCreation; }
    public void setPlaylistCreation(LocalDate playlistCreation) { this.playlistCreation = playlistCreation; }

    public String getPlaylistDescription() { return playlistDescription; }
    public void setPlaylistDescription(String playlistDescription) { this.playlistDescription = playlistDescription; }

    public boolean isPrivacyState() { return privacyState; }
    public void setPrivacyState(boolean privacyState) { this.privacyState = privacyState; }

    public List<Song> getSongs() { return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
