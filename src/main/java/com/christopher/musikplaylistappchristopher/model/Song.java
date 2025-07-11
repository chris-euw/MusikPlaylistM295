package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;

import java.util.List;

@Entity //@Entity macht die Klasse Song als Datenbank-Tabelle
public class Song {

    //Song Attribute
    @Id //@Id markiert das Feld songID als Primärschlüssel
    @GeneratedValue(strategy = GenerationType.UUID) //Erstellt eine automatische ID (UUID beispiel: 'b8fa00a8-2cb4-4f1e-bb5f-5c4557ff7e8d'
    private String songID;
    private String title;
    private String artist;
    private String album;
    private int duration; //dauer in sekunden

    // Die Zuordnung wird über die Playlist-Klasse verwaltet ("mappedBy"). **
    // Diese Seite ist die inverse Seite der Many-to-Many-Beziehung. **
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;

    //Default Song Konstruktor (parameterloser Konstruktor)
    public Song() {}

    //Konstruktoren für die direkte erzeugung mit allen Song Attribute
    public Song(String songID, String title, String artist, String album, Integer duration) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    //Getter & Setter für Playlist Attribute
    public Integer getDuration() {
        return duration;}
    public void setDuration(Integer duration) {
        this.duration = duration;}


    public String getAlbum() {
        return album;}
    public void setAlbum(String album) {
        this.album = album;}


    public String getArtist() {
        return artist;}
    public void setArtist(String artist) {
        this.artist = artist;}


    public String getTitle() {
        return title;}
    public void setTitle(String title) {
        this.title = title;}


    public String getSongID() {
        return songID;}
    public void setSongID(String songID) {
        this.songID = songID;}

}
