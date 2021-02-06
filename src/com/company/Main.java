package com.company;

import java.util.Scanner;

public class Main {
    // Launch a new game
    static Game newGame = new Game();
    static boolean isFinish = false;
    static String pattern = "[a-z,A-Z]";

    public static void main(String[] args) {
        // Say Hello
        System.out.println("Welcome in this Hangman game.");

        // Print the length of the word to guess
        System.out.println("Word to find contains " + newGame.getWordToGuess().length() + " letters.");

        // Display the secret word
        newGame.displayBoard();

        // Play the game until the word is found OR player has made 10 attempts
        while (!isFinish) {
            // Ask a letter to the user
            launchInput();
            // user has played 10 times
            tooMuchAttempts();
            // Check if the word is discovered
            wordIsFound();
        }
    }

    /**
     * Method to launch the input to ask a letter to the player
     */
    public static void launchInput() {
        System.out.println("You still have " + newGame.getAttemptsLeft() + " attempts.");
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Please choose and write a number.");
        // Check if the input is a single letter !
        while (!inputUser.hasNext(pattern)) {
            System.out.println("Oops, it's not a letter !");
            inputUser.next();
        }
        // Input is a single letter
        String userLetter = inputUser.next();
        // Check in the array if the letter is present or not
        if (newGame.getListCharTestedByUser().contains(userLetter.charAt(0))) {
            System.out.println("You have already tried this letter.");
        } else {
            newGame.updateListChars(userLetter.charAt(0));
            newGame.checkAndApplyCharInWord(userLetter);
            System.out.println(" "); // Line break
            // Display the state of the word to guess
            System.out.println(newGame.getGuessingWordState());
        }

    }
    /**
     * Method to stop the game if the player has played more than 10 times.
     */
    public static void tooMuchAttempts() {
        if (newGame.getAttemptsLeft() == 0) {
            System.out.println("Sorry, you have no more attempts !");
            isFinish = true;
        }
    }
    /**
     * Merthod to stop the game when the player has found the word
     */
    public static void wordIsFound() {
        if (newGame.checkVictory()) {
            System.out.println("Congratulations, you found the word in " + (10 - newGame.getAttemptsLeft()) + " moves.");
            isFinish = true;
        }
    }
}
