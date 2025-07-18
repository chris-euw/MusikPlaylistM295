package com.christopher.musikplaylistappchristopher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/**
 * Repräsentiert einen Song in der Datenbank.
 * Diese Entität wird von Hibernate für die Persistenz verwendet.
 */
@Entity // JPA-Entität (Datenbank-Tabelle)
@Data   // Lombok-Annotation: generiert automatisch Getter, Setter, toString, equals, hashCode
@Table(name = "SONG") // Optional: Setzt explizit den Tabellennamen
public class Song {

    @Id // Primärschlüssel der Tabelle
    @GeneratedValue(generator = "UUID") // UUID-Generator für eindeutige ID
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "songid", updatable = false, nullable = false) // Datenbankspaltenkonfiguration
    private String songID; // Eindeutige ID des Songs

    private String title;    // Songtitel (z. B. für Anzeige oder Suche)
    private String artist;   // Interpret/Künstler
    private String album;    // Album, aus dem der Song stammt
    private int duration;    // Dauer in Sekunden

    /**
     * Many-to-Many-Beziehung zu Playlist.
     * Diese Seite ist die inverse Seite, daher "mappedBy".
     * Die Zuordnung erfolgt über das Feld "songs" in der Playlist-Entität.
     */
    @ManyToMany(mappedBy = "songs")
    @JsonIgnore // Verhindert rekursive Serialisierung bei JSON-Ausgabe
    private List<Playlist> playlists; // Liste der Playlists, in denen der Song vorkommt

    /**
     * Standard-Konstruktor (wird von JPA benötigt).
     */
    public Song() { }

    /**
     * Konstruktor zur Initialisierung eines Songs mit allen relevanten Feldern.
     * Wird typischerweise in Services, Tests oder beim Erstellen neuer Songs verwendet.
     */
    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // Getter & Setter – notwendig wegen Lombok-Kompatibilität und bei direkter Verwendung
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
