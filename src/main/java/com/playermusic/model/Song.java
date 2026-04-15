package com.playermusic.model;

public class Song {
    private String id;
    private String title;
    private String artist;
    private String coverUrl; // <--- NUEVO: URL de la imagen
    private String previewUrl;

    // Constructor vacío
    public Song() {}

    // Getters y Setters actualizados
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getCoverUrl() { return coverUrl; } // Getter para la imagen
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; } // Setter para la imagen
    public String getPreviewUrl() { return previewUrl; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }
}