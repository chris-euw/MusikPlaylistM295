package com.christopher.musikplaylistappchristopher.dto;

import com.christopher.musikplaylistappchristopher.model.Song;

import java.util.List;

public class FilterDTO {
    private String title;  // Übergabe optionaler Suchfilter
    private String artist; // Derzeit ungenutzt in Service
    private String album;  // Derzeit ungenutzt

    // Getter/Setter: JSON-Mapping im Controller
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

}