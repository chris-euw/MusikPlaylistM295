package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity // JPA-Entity für Playlists
public class Playlist {
    @Id // Primärschlüssel
    @GeneratedValue(generator = "UUID") // Generiert UUIDs
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String playlistID; // Wird gesetzt in createPlaylist und in updatePlaylist

    private String playlistName;        // Eingabe im createPlaylist (PlaylistController)
    private LocalDate playlistCreation; // Setzung im Controller oder DTO
    private String playlistDescription; // Eingabe im create/Update
    private boolean privacyState;       // Eingabe im create/Update

    @ManyToMany // Relation zu Songs
    @JoinTable(
            name = "playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )

    // Getter/Setter: genutzt in PlaylistController, PlaylistSongService und Repository

    private List<Song> songs; // Befüllt durch addSongToPlaylist (PlaylistSongService)
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