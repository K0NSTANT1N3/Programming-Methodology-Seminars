package assignments.hangman;/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;
import assignments.hangman.canvas.*;

import java.io.IOException;

public class Hangman extends ConsoleProgram {

    private HangmanLexicon lexicon;
    private ShotaShootingGugaCanvas canvas1;
    private HangmanCanvas canvas;

    private RandomGenerator rand;

    @Override
    public void init() {
        rand = RandomGenerator.getInstance();
        setSize(1500, 700); // 1500 x 700 for shotaShootingGuga and 800 x 700 for assignments.hangman
        try {
            lexicon = new HangmanLexicon();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        canvas = new
                HangmanCanvas();
        add(canvas);
    }

    @Override
    public void run() {
        String decision = readLine("Wanna play? Type y to continue: ");
        while (decision.equals("y")) {
            canvas.reset();
            String word = lexicon.getWord(rand.nextInt(lexicon.getWordCount()));
            gameplay(word);
            decision = readLine("Wanna play? Type y to continue: ");
        }
        println("Come back when prepared!");
    }

    /* process of gameplay */
    private void gameplay(String word) {
        println("Welcome to Hangman!");

        String dashedWord = createDashedString(word);
        canvas.displayWord(dashedWord);

        int lifeLeft = 8;
        while (lifeLeft >= 0) {
            char guessing = Character.toUpperCase(offerGuessing(dashedWord, lifeLeft));

            if (word.contains(guessing + "")) {
                dashedWord = correctGuess(word, dashedWord, guessing);
                if (!dashedWord.contains("_")) {
                    displayGameWon(word);
                    return;
                }
            } else {
                lifeLeft--;
                println("There are no " + guessing + " in the word");
                canvas.noteIncorrectGuess(guessing, lifeLeft);
            }
            if (lifeLeft == 0) {
                displayGameLose(word);
                return;
            }
        }
    }

    /* ask user to enter information */
    private char offerGuessing(String guessedPart, int lifeLeft) {
        println("The word now looks like this: " + guessedPart);
        println("You Have " + lifeLeft + " guesses left.");

        String input = readLine("Your guess: ");
        while (!validLetter(input)) input = readLine("Enter correct letter!!! : ");

        return input.charAt(0);
    }

    /* check if string is one sized character from alphabet */
    private boolean validLetter(String input) {
        if (input.length() != 1) return false;
        char c = input.charAt(0);

        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /* hashing string with dashes */
    private String createDashedString(String word) {
        return "_".repeat(word.length());
    }

    /* handle logic of properly guessing letter in the word */
    private String correctGuess(String word, String dashedWord, char guessing) {
        println("That guess is correct. ");
        for (int i = word.indexOf(guessing); i != -1; i = word.indexOf(guessing, i + 1)) {
            dashedWord = replaceChar(dashedWord, guessing, i);
        }
        canvas.displayWord(dashedWord);

        return dashedWord;
    }

    /* replaces charachter on specific index in a string */
    private String replaceChar(String originalWord, char replacement, int index) {
        if (index < 0 || index >= originalWord.length()) {
            return originalWord;
        }

        String part1 = index == 0 ? "" : originalWord.substring(0, index);
        String part2 = String.valueOf(replacement);
        String part3 = ((index + 1) < originalWord.length()) ? originalWord.substring(index + 1) : "";

        return part1 + part2 + part3;
    }

    /* after game is won */
    private void displayGameWon(String word) {
        println("You guessed the word: " + word);
        println("You win!");
    }

    /* after game is lost */
    private void displayGameLose(String word) {
        println("You are completely hung");
        println("The word was: " + word);
        println("You lose!");
    }

}
