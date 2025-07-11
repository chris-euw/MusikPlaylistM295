package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.swing.text.TabExpander;
import java.util.List;
import java.util.UUID;

@Entity//@Entity macht die Klasse Song als Datenbank-Tabelle
@Data
@Table(name ="SONG")
public class Song {

    //Song Attribute
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")//@Id markiert das Feld songID als Primärschlüssel
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String songID;
    @Column(name = "TiTLE")
    private String title;
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "ALBUM")
    private String album;
    @Column(name = "DURATION")
    private int duration; //dauer in sekunden

    // Die Zuordnung wird über die Playlist-Klasse verwaltet ("mappedBy"). **
    // Diese Seite ist die inverse Seite der Many-to-Many-Beziehung. **
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;

    //Default Song Konstruktor (parameterloser Konstruktor)
    public Song() {}

    //Konstruktoren für die direkte erzeugung mit allen Song Attribute
    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }
}
