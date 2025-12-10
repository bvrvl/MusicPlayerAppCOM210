package com.musicplayer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saurabh
 *  Song class is for one single song. It will have all attributes of a song like title, artist, album, genre, etc.
 */
public class Song {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int durationInSeconds;
    
    public Song(String title, String artist, String album, String genre, int durationInSeconds) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
    }
    
    /**
     *The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     *The artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     *The album of the song.
     */
    public String getAlbum() {
        return album;
    }
    
    /**
     *The genre of the song.
     */
    public String getGenre() {
        return genre;
    }

    /**
     *The duration of the song in seconds.
     */
    public int getDurationInSeconds() {
        return durationInSeconds;
    }
   

    /**

     * We need to over ride the toString method to give a more human-readable representation of the Song object in the User interface later
     * We return a formatted string with the song's title, artist, and album.
     */
    @Override
    public String toString() {
        return String.format("\"%s\" by %s (Album: %s)", title, artist, album);
    }
}
