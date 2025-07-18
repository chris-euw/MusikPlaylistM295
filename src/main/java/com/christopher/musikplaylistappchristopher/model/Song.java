package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.swing.text.TabExpander;
import java.util.List;
import java.util.UUID;

@Entity
@Data // Lombok generiert Getter, Setter etc.
@Table(name = "SONG")
public class Song {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "songid", updatable = false, nullable = false)
    private String songID;

    private String title;    // Feld für JSON-Serialisierung und Suchmethoden
    private String artist;   // Feld für Suchmethoden
    private String album;    // Feld für Suchmethoden
    private int duration;    // Spieldauer in Sekunden

    @ManyToMany(mappedBy = "songs") // Inverse Seite der Relation
    private List<Playlist> playlists; // Befüllt automatisch bei Fetch

    public Song() { }

    public Song(String title, String artist, String album, int duration) {
        // Konstruktor genutzt in Service oder Tests
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}

