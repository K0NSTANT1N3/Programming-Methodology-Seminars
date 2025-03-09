package section.hangman;/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.StringTokenizer;

public class Hangman extends ConsoleProgram {
	
	/** amount of lives player has*/
	private final int AMOUNTOFLIVES = 8;
	
	//Instance variables
	
	/** RandomGenerator instance variable*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** HangmanLexicon instance variable*/
	private HangmanLexicon hangmanlexicon = new HangmanLexicon();
	
	private HangmanCanvas canvas;
	
	/** current player's lives*/
	private int currentLives = AMOUNTOFLIVES;
	
	/** the text that will be displayed of the guessed letters */
	private String displayedLetters;
	
	/** the word player has to guess*/
	private String secretWord;
	
	/** the current letter the player is guessing (is one letter)*/
	private char currentGuess;
	
	/** every guess the player made in a string*/
	private String guessedLetters = null;
	
	public void init(){ 
		canvas = new HangmanCanvas(); 
		add(canvas); 
	} 

    public void run(){
    	while(true){
        	hangmanGame();
        	String playAgain = readLine("press anything to play again ");
        	resetGame();
    	}
	}
    
    // clears the canvas and resets the values for a new game
    private void resetGame(){
    	canvas.reset();
    	resetValues();
	}

    // resets values
	private void resetValues(){
		currentLives = AMOUNTOFLIVES;
		guessedLetters = null;
	}

	//runs code resposible for running the game
    private void hangmanGame(){
    	generateLevel();
    	oneIterationOfHangman();
	}

	//creates the background of the level (creates secret word and makes current guess empty)
    private void generateLevel(){
    	generatesecretWord();
    	displayedLetters = setUnderscores();
	}

	//generates the word the player has to guess
	private void generatesecretWord(){
    	secretWord = hangmanlexicon.getWord(rgen.nextInt(0, hangmanlexicon.getWordCount() - 1));
	}
	
	// sets the current guess as underscores(so empty)
    private String setUnderscores(){
    	String resultString = "";
    	for(int i = 0; i < secretWord.length(); i++){
    		resultString += "_ ";
    	}
    	
    	return resultString;
	}
    
    //one iteration of Hangman (the player guessing and check if it is correct guess)
    private void oneIterationOfHangman(){
		while(true){
			canvas.displayWord(displayedLetters);
			println(displayedLetters);
			if(playerWon()){
				println("YOU WON!!");
				break;
			}
			inputGuess();
			revealPlayerGuesse();
			if(currentLives == 0)break;

		}
	}
    
    //checks if every letter has been guessed(no underscores left)
    private boolean playerWon(){
		return displayedLetters.indexOf("_") == -1;
	}

	//method responsible for player inputing the guess
	private void inputGuess(){
		String playerGuess;
		while(true){
			playerGuess = null;
			while(enterTheCharacterAgain(playerGuess)){
				playerGuess = readLine("Enter a single letter: ");
			}
			
			if(guessedLetters == null || uniqueCharacter(playerGuess))break;
			println("You have already guessed that letter");
		}
		currentGuess = Character.toUpperCase(playerGuess.charAt(0));
		guessedLetters += currentGuess;
	}

	/*will be false the first time any other time checks
	 * if the letter is bigger than one and  if it is not a letter*/
	private boolean enterTheCharacterAgain(String playerGuess){
		return playerGuess == null || 
				isLargerThanOneLetter(playerGuess) || 
				inputIsNotALetter(playerGuess);
	}
	
	//checks if it is the first time player has tried guessing that letter
	private boolean uniqueCharacter(String playerGuess){
		char guess = Character.toUpperCase(playerGuess.charAt(0));
		return guessedLetters.indexOf(guess) == -1;
	}

	//checks if the input is a letter and makes if false if it is
	private boolean inputIsNotALetter(String playerGuess){
		return !(Character.toUpperCase(playerGuess.charAt(0)) >= 'A' &&
				 Character.toUpperCase(playerGuess.charAt(0)) <= 'Z');
	}

	//makes sure the string the player inputed is only one letter
	private boolean isLargerThanOneLetter(String playerGuess) {
		return playerGuess.length() != 1;
	}

	// method that displays what letters the player has guessed in the word
	private void revealPlayerGuesse(){
		if(guessIsCorrect()){
			println("correctGuess");
			displayedLetters = updateDisplay();
		}else{
			currentLives--;
			canvas.noteIncorrectGuess(currentGuess, currentLives);
			println("incorrect Guess");
		}
	}

	// checks if the player's guessed letter can be found in the secret word
	private boolean guessIsCorrect(){
		return secretWord.indexOf(currentGuess) != -1;
	}

	// updates the instance variable displayedLetters to the new letters the player guessed
	private String updateDisplay(){
		StringTokenizer tokenizer = new StringTokenizer(displayedLetters, " ");
		String updateDisplayedLetters = "";
		
		while(tokenizer.hasMoreTokens()){
			for(int i = 0; i < secretWord.length(); i++){
				if(secretWord.charAt(i) == currentGuess){
					updateDisplayedLetters += currentGuess + " ";
					tokenizer.nextToken(); //to skip one
				}else{
					updateDisplayedLetters += tokenizer.nextToken() + " ";
				}
			}
		}
		return updateDisplayedLetters;
	}
}
