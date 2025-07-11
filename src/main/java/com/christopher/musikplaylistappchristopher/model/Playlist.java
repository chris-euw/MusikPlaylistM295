package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity //@Entity macht die Klasse Playlist als Datenbank-Tabelle
public class Playlist {


    //Playlist Attribute
    @Id //@ID markiert das Feld als Primärschlüssel
    @GeneratedValue(strategy = GenerationType.UUID) //Erstellt eine automatische ID (UUID beispiel: 'b8fa00a8-2cb4-4f1e-bb5f-5c4557ff7e8d'
    private String playlistID;
    private String playlistName;
    private LocalDate playlistCreation;
    private String playlistDescription;
    private boolean privacyState; //true = privat, false = öffentlich

    // Zeigt auf den Benutzer dem diese Playlist gehört **
    // @ManyToOne bedeutet viele Playlists können zu einem Benutzer gehören **
    @ManyToMany
    private List<Song> songs;
    @ManyToOne
    private User user;

    //Default Playlist Konstruktor (parameterloser Konstruktor
    public Playlist() {}

    //Konstruktoren für die direkte erzeugung mit allen Playlist Attribute
    public Playlist(String playlistID, String playlistName, LocalDate playlistCreation, String playlistDescription, boolean privacyState) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.playlistCreation = LocalDate.now(); //setzt automatisch das heutige Datum
        this.playlistDescription = playlistDescription;
        this.privacyState = privacyState;
    }

    //Getter & Setter für Playlist Attribute
    public String getPlaylistID() {
        return playlistID;}
    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;}


    public String getPlaylistName() {
        return playlistName;}
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;}


    public LocalDate getPlaylistCreation() {
        return playlistCreation;}
    public void setPlaylistCreation(LocalDate playlistCreation) {
        this.playlistCreation = playlistCreation;}


    public String getPlaylistDescription() {
        return playlistDescription;}
    public void setPlaylistDescription(String playlistDescription) {
        this.playlistDescription = playlistDescription;}


    public boolean isPrivacyState() {
        return privacyState;}
    public void setPrivacyState(boolean privacyState) {
        this.privacyState = privacyState;}

    public List<Song> getSongs() {
        return songs;}
    public void setSongs(List<Song> songs) {
        this.songs = songs;}

}
