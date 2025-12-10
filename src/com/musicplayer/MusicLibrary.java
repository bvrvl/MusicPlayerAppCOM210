/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.musicplayer;

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
    
}
