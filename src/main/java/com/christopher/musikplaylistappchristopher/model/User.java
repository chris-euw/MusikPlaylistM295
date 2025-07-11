package com.christopher.musikplaylistappchristopher.model;

import jakarta.persistence.*;

import java.util.List;

@Entity // @Entitiy macht die Klasse als Datenbank-Tabelle
public class User {

    //User Attribute
    @Id // @Id markiert das Feld userID als Primärschlüssel
    @GeneratedValue(strategy = GenerationType.UUID) ////Erstellt eine automatische ID (UUID beispiel: 'b8fa00a8-2cb4-4f1e-bb5f-5c4557ff7e8d'
    private String userID;
    private String userName;
    private String email;
    private String password;

    // macht eine Liste von Playlists die diesem Benutzer gehören **
    // @OneToMany bedeutet ein Benutzer kann mehrere Playlists haben **
    @OneToMany(mappedBy = "user") //
    private List<Playlist> playlists;

    //Default User Konstruktor (parameterloser Konstruktor)
    public User() {}

    //Konstruktoren für die direkte erzeugung mit allen User Attribute
    public User(String userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //Getter & Setter für User Attribute
    public String getUserID() {
        return userID;}
    public void setUserID(String userID) {
        this.userID = userID;}


    public String getUserName() {
        return userName;}
    public void setUserName(String userName) {
        this.userName = userName;}


    public String getEmail() {
        return email;}
    public void setEmail(String email) {
        this.email = email;}


    public String getPassword() {
        return password;}
    public void setPassword(String password) {
        this.password = password;}


}
