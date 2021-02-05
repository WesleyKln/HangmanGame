package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<String> listWords;
    private final String wordToGuess;
    private final char[] guessingWordState;
    private final List<Character> listCharTestedByUser;
    private int attemptsLeft;

    public Game() {
        this.listWords = Arrays.asList(
                "Simplon",
                "Programming",
                "devDucks"
        );
        this.wordToGuess = randomWord().toUpperCase();
        this.guessingWordState = this.wordToGuess.toCharArray().clone();
        this.attemptsLeft = 10;
        this.listCharTestedByUser = new ArrayList<Character>();
    }

    public List<String> getListWords() {
        return listWords;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public char[] getGuessingWordState() {
        return guessingWordState;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Character> getListCharTestedByUser() {
        return listCharTestedByUser;
    }

    /**
     * Method to get a random word from the entire word's list
     * @return only one word
     */
    public String randomWord() {
        return getListWords().get((int) (Math.random() * getListWords().size()));
    }

    /**
     * Method to display for the first time the word with this char '_' in place of letter
     */
    public void displayBoard() {
        Arrays.fill(this.guessingWordState, '_');
        System.out.print(this.guessingWordState);
        // Line break
        System.out.println("");
    }

    /**
     * Method to check the input then replace the empty space in the word by the valid letter
     * @param userLetter letter chosen by the player
     */
    public void checkAndApplyCharInWord(String userLetter) {
        char userChar = userLetter.charAt(0);
        int indexOfCharInWordToGuess = this.wordToGuess.indexOf(userChar);
        char userCharInLowerCase = Character.toLowerCase(userChar);
        char UserCharInUpperCase = Character.toUpperCase(userChar);

        int idxCharLowerCaseInWord = this.wordToGuess.indexOf(userCharInLowerCase);
        int idxCharUpperCaseInWord = this.wordToGuess.indexOf(UserCharInUpperCase);

        if (idxCharLowerCaseInWord == -1 && idxCharUpperCaseInWord == -1) {
            System.out.println("Dommage, la lettre " + "\'" + userLetter + "\'"+ " n'est pas dans le mot !");
            // Update the counter with 1 attempts
            this.attemptsLeft--;
        } else {
            System.out.println("Bravo, la lettre " + "\'" + userLetter + "\'"+ " est dans le mot.");
            // Replace empty space by user's letter
            //1- User's letter is in lowercase while in the word to guess, it's in uppercase -> Add the letter in uppercase !
            if (Character.isLowerCase(userChar) && idxCharUpperCaseInWord >= 0) {
                for (int i = idxCharUpperCaseInWord ; i >= 0; i = this.wordToGuess.indexOf(UserCharInUpperCase, i + 1)) {
                    this.guessingWordState[i] = UserCharInUpperCase;
                }
            }
            //2- User's letter is in uppercase while in the word to guess, it's in lowercase -> Add the letter in lowercase !
            if (Character.isUpperCase(userChar) && idxCharLowerCaseInWord >= 0) {
                for (int i = idxCharLowerCaseInWord ; i >= 0; i = this.wordToGuess.indexOf(userCharInLowerCase, i + 1)) {
                    this.guessingWordState[i] = UserCharInUpperCase;
                }
            }
            //3- User's letter is equal to the one / those in the word -> Add it / them !
            for (int i = indexOfCharInWordToGuess ; i >= 0; i = this.wordToGuess.indexOf(userLetter, i + 1)) {
                this.guessingWordState[i] = UserCharInUpperCase;
            }
        }
    }

    /**
     * Method to check victory statement
     * @return boolean
     */
    public boolean checkVictory() {
        return Arrays.equals(this.guessingWordState, this.wordToGuess.toCharArray());
    }

    /**
     * Method which updates the array that contains all the player's attempts
     * @param userChar player's letter
     */
    public void updateListChars(char userChar) {
        if (!this.listCharTestedByUser.contains(userChar)) {
            listCharTestedByUser.add(Character.toLowerCase(userChar));
            listCharTestedByUser.add(Character.toUpperCase(userChar));
        }
    }
}