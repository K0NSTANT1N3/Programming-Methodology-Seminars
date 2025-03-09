package section.hangman.With;/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.util.ArrayList;

public class HangmanCanvas extends GCanvas {
	// N of attempts allowed
	private static final int MAX_ATTEMPTS = 8;

	// N of attempts left
	public int guessesLeft = MAX_ATTEMPTS;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		// Remove existing label first
		if (wordLabel != null){
			remove(wordLabel);
		}

		// Create new label
		wordLabel = new GLabel(word);
		wordLabel.setFont(wordLabel.getFont().deriveFont(24f));

		// Display label on canvas
		add(wordLabel, WORD_LABEL_X_OFFSET, getHeight() - WORD_LABEL_Y_OFFSET);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		// Add the letter to the list
		if(!incorrectGuesses.contains(letter)){
			incorrectGuesses.add(letter);
		}

		// Draw a body part
		switch(guessesLeft) {
			case 7: drawHead(); break;
			case 6: drawBody(); break;
			case 5: drawLeftArm(); break;
			case 4: drawRightArm(); break;
			case 3: drawLeftLeg(); break;
			case 2: drawRightLeg(); break;
			case 1: drawLeftFoot(); break;
			case 0: drawRightFoot(); break;
		}

		// Display the incorrect letters
		displayIncorrectLetters();
	}

	private void displayIncorrectLetters(){
		// Remove existing label first
		if (incorrectGuessesLabel != null){
			remove(incorrectGuessesLabel);
		}

		// Turn the array into a string
		String list = "";

		for (Character chr : incorrectGuesses) {
			list += Character.toUpperCase(chr);
		}

		// Create new label
		incorrectGuessesLabel = new GLabel(list);

		// Display label on canvas
		add(incorrectGuessesLabel, LIST_LABEL_X_OFFSET, getHeight() - LIST_LABEL_Y_OFFSET);
	}

	private void drawScaffold(){
		// Draw Scaffold parts
		GLine scaffold = new GLine(getWidth() / 2.0 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET, getWidth() / 2.0 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET + SCAFFOLD_HEIGHT);
		add(scaffold);

		GLine beam = new GLine(getWidth() / 2.0 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET, getWidth() / 2.0, SCAFFOLD_Y_OFFSET);
		add(beam);

		GLine rope = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET, getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH);
		add(rope);
	}

	private void drawHead(){
		// Draw head
		GOval head = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head, getWidth() / 2.0 - HEAD_RADIUS, SCAFFOLD_Y_OFFSET + ROPE_LENGTH );
	}

	private void drawBody(){
		// Draw body
		GLine body = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2, getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(body);
	}

	private void drawLeftArm(){
		// Draw left arm
		GLine upperArm = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		add(upperArm);

		GLine lowerArm = new GLine(getWidth() / 2.0 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(lowerArm);
	}

	private void drawRightArm(){
		// Draw right arm
		GLine upperArm = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		add(upperArm);

		GLine lowerArm = new GLine(getWidth() / 2.0 + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2.0 + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(lowerArm);
	}

	private void drawLeftLeg(){
		// Draw left leg
		GLine hip = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2.0 - HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(hip);

		GLine leg = new GLine(getWidth() / 2.0 - HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2.0 - HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(leg);
	}

	private void drawRightLeg(){
		// Draw right leg
		GLine hip = new GLine(getWidth() / 2.0, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2.0 + HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(hip);

		GLine leg = new GLine(getWidth() / 2.0 + HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2.0 + HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(leg);
	}

	private void drawLeftFoot(){
		// Draw left foot
		GLine foot = new GLine(getWidth() / 2.0 - HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth() / 2.0 - HIP_WIDTH - FOOT_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}

	private void drawRightFoot(){
		// Draw right foot
		GLine foot = new GLine(getWidth() / 2.0 + HIP_WIDTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth() / 2.0 + HIP_WIDTH + FOOT_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int LIST_LABEL_Y_OFFSET = 15;
	private static final int LIST_LABEL_X_OFFSET = 40;
	private static final int WORD_LABEL_Y_OFFSET = 40;
	private static final int WORD_LABEL_X_OFFSET = 40;
	private static final int SCAFFOLD_Y_OFFSET = 20;

	// Incorrect guesses array
	ArrayList<Character> incorrectGuesses = new ArrayList<Character>();

	// Incorrect guesses list label instance
	GLabel incorrectGuessesLabel;

	// Word Label instance
	GLabel wordLabel;
}


