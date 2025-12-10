/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.musicplayer;

/**
 *
 * @author saurabh
 * This class will handle all the User Interactions. Will use a console based menu
 */
public class Main {
     public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        Scanner scanner = new Scanner(System.in);

        
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
                    System.out.println("Search feature has not been implemented.");
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
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

    }
    
    private static void deleteSong(Scanner scanner, MusicLibrary library) {
        System.out.println("\n--- Delete a Song ---");
        System.out.print("Enter the exact title of the song to delete: ");
        String titleToDelete = scanner.nextLine();

        // TODO: Call the library's delete method with the title provided by the user.
    }
}
    
