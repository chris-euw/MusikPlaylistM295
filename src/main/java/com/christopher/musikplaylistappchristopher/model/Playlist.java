package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, length = 36)
    private String playlistID;

    @Column(nullable = false)
    private String playlistName;

    @Column(updatable = false)
    private LocalDate playlistCreation;

    private String playlistDescription;

    private boolean privacyState;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

    public Playlist() {
    }

    // Vereinfachter Konstruktor ohne ID
    public Playlist(String playlistName, String playlistDescription, boolean privacyState) {
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.privacyState = privacyState;
    }

    @PrePersist
    protected void onCreate() {
        this.playlistCreation = LocalDate.now();
    }

    // Getter & Setter (Behalten Sie diese bei oder verwenden Sie Lombok)
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