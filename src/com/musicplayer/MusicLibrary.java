/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.musicplayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 *
 * @author saurabh
 */
public class MusicLibrary {
    
  /** This class will represent an element within the Doubly Linked List.
 * It is a private class because no external classes need to interact with it directly
 */
   
  private class Node {
        Song data;
        Node next;
        Node prev; // Doubly linked list allows the 'prev' feature to exist.

        Node(Song data) {
            this.data = data;
        }
    }
  
    private Node head; // First node in the list.
    private Node tail; // Last node for O(1) append operations.
    private int size;  // Caches the size of the list

    /**
     * Constructs an empty MusicLibrary.
     */
    public MusicLibrary() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Appends a new song to the end of the list. This operation has a time
     *
     */
    public void insert(Song song) {
        // TODO: Implement the insertion logic.
        
    }

    /**
     * Traverses the list from head to tail and prints the details of each song.
     */
    public void viewAll() {
        System.out.println("Displaying All Songs in Library");
        
        System.out.printf("Total Songs: %d\n", this.size);
        System.out.println("------------------------------------");
    }
    
    /**
     * Searches for and removes a song from the list based on its title.
     */
    public boolean delete(String songTitle) {
        if (head == null) {
            return false; // List is empty, nothing to delete.
        }

        // Find the node to delete
        Node current = head;
        while (current != null && !current.data.getTitle().equalsIgnoreCase(songTitle)) {
            current = current.next;
        }

        // If current is null, the song was not found in the list.
        if (current == null) {
            return false;
        }


        // the node to delete is the head.
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                // The list is now empty, so tail must also be null.
                tail = null;
            }
        } 
        // When the node to delete is the tail.
        else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } 
        // When the node is in the middle.
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return true;
    }
    
    /**
     * Searches for songs by title or artist, performing a case-insensitive partial match.
     * */
    public void searchByTitleOrArtist(String searchTerm) {
        System.out.println("\n--- Search Results for \"" + searchTerm + "\" ---");
        boolean found = false;
        Node current = head;
        while (current != null) {
            // convert both to lower case for a case-insensitive match.
            // .contains() allows for partial matches.
            if (current.data.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                current.data.getArtist().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("- " + current.data.toString());
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No songs found matching your search term.");
        }
        System.out.println("------------------------------------");
    }
    
    /**
     * Finds and returns a Song object by its title.
     */
    public Song findSongByTitle(String songTitle) {
        Node current = head;
        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(songTitle)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
    /**
     * Returns a sorted list of songs from the library.
     */
    public ArrayList<Song> getSortedSongs(Comparator<Song> comparator) {
        // Copy all songs from the linked list into an ArrayList.
        ArrayList<Song> songList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            songList.add(current.data);
            current = current.next;
        }

        // use the sort method with the provided comparator feature.
        songList.sort(comparator);
        
        return songList;
    }
}
   
