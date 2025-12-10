/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.musicplayer;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Comparator;
/**
 *
 * @author saurabh
 * This class will handle all the User Interactions. Will use a console based menu
 */
public class Main {
     public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        Scanner scanner = new Scanner(System.in);
        
        Queue<Song> nowPlayingQueue = new LinkedList<>();
        
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");

            // Make sure user is entering an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard the non-integer input
                System.out.print("Enter your choice: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // use the newline character left by nextInt()

            switch (choice) {
                case 1:
                    library.viewAll();
                    break;
                case 2:
                    addSong(scanner, library);
                    break;
                case 3:
                    deleteSong(scanner, library);
                    break;
                case 4:
                    System.out.println("View Sorted Search Results");
                    viewSortedList(scanner, library); 
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                case 6: 
                playSong(scanner, library);
                break;
                case 7:
                addSongToQueue(scanner, library, nowPlayingQueue);
                break;
                case 8: 
                viewQueue(nowPlayingQueue);
                break;
                case 9:
                running = false;
                System.out.println("Exiting the application. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                break;

            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu options to the console.
     */
    public static void displayMenu() {
        System.out.println("\n===== Music Library Database System =====");
        System.out.println("1. View All Songs");
        System.out.println("2. Add a New Song");
        System.out.println("3. Delete a Song by Title");
        System.out.println("4. Search");
        System.out.println("5. Exit");
        System.out.println("6. Play a song");
        System.out.println("\n Playlist ---");
        System.out.println("7. Add song to Now Playing Queue");
        System.out.println("8. View Now Playing queue");
        System.out.println("9. Exit");
    }
    
    /**
     * Handles the user prompts for adding a new song and then instructs the library to insert it.
     */
    private static void addSong(Scanner scanner, MusicLibrary library) {
        System.out.println("\n--- Add a New Song ---");
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Artist: ");
        String artist = scanner.nextLine();
        System.out.print("Enter Album: ");
        String album = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Use newline left by above
        
        
        Song newSong = new Song(title, artist, album, genre, duration);
        library.insert(newSong);
        System.out.println("Successfully added: " + newSong.getTitle());
        
        
    }
    
    private static void deleteSong(Scanner scanner, MusicLibrary library) {
        System.out.println("\n--- Delete a Song ---");
        System.out.print("Enter the exact title of the song to delete: ");
        String titleToDelete = scanner.nextLine();
        if (library.delete(titleToDelete)) {
            System.out.println("Successfully deleted \"" + titleToDelete + "\".");
        } else {
            System.out.println("Could not find a song with that title.");
        }
    }
    
    /* Asks the user for a search term and calls the library's search method.*/
    
    private static void searchForSong(Scanner scanner, MusicLibrary library) {
    System.out.println("\n--- Search for a Song ---");
    System.out.print("Enter a title or artist to search for: ");
    String searchTerm = scanner.nextLine();
    library.searchByTitleOrArtist(searchTerm);
}
    /**
     * Finds a song in the library and adds it to the now playing queue.
     */
    private static void addSongToQueue(Scanner scanner, MusicLibrary library, Queue<Song> queue) {
        System.out.println("\n--- Add to Queue ---");
        System.out.print("Enter the exact title of the song to add to the queue: ");
        String songTitle = scanner.nextLine();
        
        // We need to find the song object first.
        Song songToAdd = library.findSongByTitle(songTitle);

        if (songToAdd != null) {
            queue.offer(songToAdd);
            System.out.println("Added \"" + songToAdd.getTitle() + "\" to the Now Playing queue.");
        } else {
            System.out.println("Could not find a song with that title in the library.");
        }
    }

    /**
     * Displays the songs currently in the Now Playing queue.
     */
    private static void viewQueue(Queue<Song> queue) {
        System.out.println("\n--- Now Playing Queue ---");
        if (queue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            int count = 1;
            for (Song song : queue) {
                System.out.println(count + ". " + song.toString());
                count++;
            }
        }
        System.out.println("-------------------------");
    }
    
    /**
     * Displays a sub-menu for sorting options and prints a sorted list of songs.
     * @param scanner The Scanner for user input.
     * @param library The music library to be sorted and displayed.
     */
    private static void viewSortedList(Scanner scanner, MusicLibrary library) {
        System.out.println("\n--- Sort Library By ---");
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Artist");
        System.out.println("3. View Top 3 Most Played Songs");
        System.out.print("Enter your choice: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // use newline from before

        Comparator<Song> comparator = null;
        String sortBy = "";
        boolean isTopK = false;

        switch (sortChoice) {
            case 1:
                
                comparator = Comparator.comparing(Song::getTitle);
                sortBy = "Title";
                break;
            case 2:
                comparator = Comparator.comparing(Song::getArtist);
                sortBy = "Artist";
                break;
            case 3: //For the top K case
                // To get the most played, we sort by playCount in DESCENDING order.
                comparator = Comparator.comparingInt(Song::getPlayCount).reversed();
                sortBy = "Most Played";
                isTopK = true;
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        ArrayList<Song> sortedList = library.getSortedSongs(comparator);
        
        if (isTopK) {
            // Special handling for the Top K feature
            System.out.println("\n--- Top 3 Most Played Songs ---");
            // Determine how many songs to show: either 3, or the total size if the library is smaller.
            int limit = Math.min(3, sortedList.size()); 
            if (limit == 0) {
                 System.out.println("No songs in the library to display.");
            } else {
                for (int i = 0; i < limit; i++) {
                    System.out.println((i + 1) + ". " + sortedList.get(i).toString());
                }
            }
        } else {
            // The regular sorting display
            System.out.println("\n--- Library Sorted by " + sortBy + " ---");
            for (int i = 0; i < sortedList.size(); i++) {
                System.out.println((i + 1) + ". " + sortedList.get(i).toString());
            }
        }
        System.out.println("------------------------------------");
    }
    
    /* Prompts the user for a song title, finds the song, and increments its play count.
     */
    private static void playSong(Scanner scanner, MusicLibrary library) {
        System.out.println("\n--- Play a Song ---");
        System.out.print("Enter the exact title of the song to play: ");
        String songTitle = scanner.nextLine();
        
        // Use the helper method to find the song object.
        Song songToPlay = library.findSongByTitle(songTitle);

        if (songToPlay != null) {
            songToPlay.incrementPlayCount();
            System.out.println("Now playing: " + songToPlay.getTitle() + ". Its play count is now " + songToPlay.getPlayCount() + ".");
        } else {
            System.out.println("Could not find a song with that title in the library.");
        }
    }
}
    
