package section.hangman.With;/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.RandomGenerator;

public class Hangman extends ConsoleProgram {
    // RandomGenerator instance
    private static final RandomGenerator rgen = RandomGenerator.getInstance();

    // Lexicon instance
    HangmanLexicon lexicon = new HangmanLexicon();

    // Canvas Instance
    private HangmanCanvas canvas;

    // Pick a random secret word
    private final String SecretWord = lexicon.getWord(rgen.nextInt(lexicon.getWordCount()));

    // Player's correct guesses
    private final char[] correctGuesses = new char [SecretWord.length()];

    public void init() {
        // Initialize canvas
        canvas = new HangmanCanvas();
        add(canvas);
    }

    public void run() {
        // Start the game
        println("Welcome to Hangman!");
        canvas.reset();

        // Continue game until lost or won
        while(canvas.guessesLeft > 0 && !gameWon()){
            // Display current word progress and attempts left
            updateGameState();

            // Ask for and check player's guess
            askForGuess();
        }

        // Win scenario
        if (gameWon()){
            println("You guesses the word: " + SecretWord);
            println("You win.");
        }
        // Lose scenario
        else {
            println("You're completely hung.");
            println("The word was: " + SecretWord);
            println("You lose.");
        }
	}

    private boolean gameWon() {
        // Check if the player has guessed every letter
        for (char chr : correctGuesses) {
            if (chr == '\u0000') {
                return false;
            }
        }
        return true;
    }

    private void updateGameState(){
        // Update the current state of the game
        print("The word now looks like this: ");

        // Display guessed letters of the word
        for (char chr : correctGuesses) {
            if(chr == '\u0000'){
                print("-");
            }else{
                print(chr);
            }
        }

        // Display N of attempts left
        println("\nYou have " + canvas.guessesLeft + " guesses left.");
    }

    private void askForGuess(){
        String guess = "";

        // Ask until a valid character is entered
        while (guess.length() != 1 || (guess.charAt(0) < 'A' || (guess.charAt(0) > 'Z' && guess.charAt(0) < 'a') || guess.charAt(0) > 'z')) {
            guess = readLine("Your guess: ");

            // Warn if the input is a single character
            if (guess.length() != 1) {
                println("Enter one character only.");
            }
            // Warn if the input isn't in the alphabet
            else if (guess.charAt(0) < 'A' || (guess.charAt(0) > 'Z' && guess.charAt(0) < 'a') || guess.charAt(0) > 'z') {
                println("Wrong symbol entered, try again.");
            }
        }

        // Check the guessed character
        checkGuessed(guess.charAt(0));
    }

    private void checkGuessed(char guess){
        // The secret word contains the guessed character
        if (SecretWord.indexOf(Character.toUpperCase(guess)) != -1){
            // Print the message
            println("That guess is correct.");

            // Update correctGuesses array
            updateGuessedCorrect(Character.toUpperCase(guess));

            // Update the canvas
            canvas.displayWord(arrayToSpecialString(correctGuesses));
        }
        else {
            // Update N of attempts left
            canvas.guessesLeft--;

            // Update the canvas
            canvas.noteIncorrectGuess(guess);

            // Print the message
            println("There are no " + Character.toUpperCase(guess) + "'s in the word.");
        }
    }

    private void updateGuessedCorrect(char guess){
        // Add a guessed character to the correctGuesses array in the right places
        for (int i = 0; i < SecretWord.length(); i++) {
            if (SecretWord.charAt(i) == guess){
                correctGuesses[i] = guess;
            }
        }
    }

    private String arrayToSpecialString(char[] array){
        // Turn an array into a string with hyphens
        String result = "";
        for (char chr : array) {
            if(chr == '\u0000'){
                result += "-";
             }else{
                result += chr;
            }
        }
        return result;
    }
}
