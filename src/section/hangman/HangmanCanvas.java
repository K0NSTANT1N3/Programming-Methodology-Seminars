package section.hangman;/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		label.setLabel("");
		drawScaffold();
	}
	
	// draws the scaffold 
	private void drawScaffold() {
		double y1 = (getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_MARGIN_BOTTOM) / 2.0;
		double x1 = (getWidth() - BEAM_LENGTH - UPPER_ARM_LENGTH) / 2.0;
		
		addLine(x1,y1,x1,y1 + SCAFFOLD_HEIGHT);
		addLine(x1,y1,x1 + BEAM_LENGTH,y1);
		addLine(x1 + BEAM_LENGTH,y1,x1 + BEAM_LENGTH,y1 + ROPE_LENGTH);
	}
	
	
	// function that creates and adds line between (x1 y1 x2 y2) coordinates
	private void addLine(double x1, double y1, double x2, double y2) {
		GLine line = new GLine(x1,y1,x2,y2);
		add(line);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		wordDisplay.setLabel(word);
		wordDisplay.setFont("Arial-30");
		double cordX = (getWidth() - wordDisplay.getWidth()) / 2.0;
		double cordY = getHeight() - label.getHeight() - 5;
		add(wordDisplay,cordX,cordY);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int lives) {
		addLetterToList(letter);
		drawHangman(lives);
	}
	
	// for each lost life draws body parts using the scaffold coordinate(x1,y1)
	private void drawHangman(int lives) {
		double y1 = (getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_MARGIN_BOTTOM) / 2.0;
		double x1 = (getWidth() - BEAM_LENGTH - UPPER_ARM_LENGTH) / 2.0;
		
		if(lives == 7)drawHead(x1,y1);
		else if(lives == 6)drawBody(x1,y1);	
		else if(lives == 5) drawLeftHand(x1,y1);
		else if(lives == 4)drawRightHand(x1,y1);
		else if(lives == 3)drawLeftLeg(x1,y1);
		else if(lives == 2)drawRightLeg(x1,y1);
		else if(lives == 1)drawLeftFoot(x1,y1);
		else if(lives == 0)drawRightFoot(x1,y1);
	
	}
	
	private void drawHead(double x1, double y1) {
		GOval head = new GOval(HEAD_RADIUS * 2,HEAD_RADIUS * 2);
		add(head, x1 + BEAM_LENGTH - HEAD_RADIUS, y1+ROPE_LENGTH);
	}

	private void drawBody(double x1, double y1) {
		double bodyX = x1 + BEAM_LENGTH;
		double bodyY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS;
		
		addLine(bodyX,bodyY,bodyX,bodyY + BODY_LENGTH);
	}

	private void drawLeftHand(double x1, double y1) {
		double handX = x1 + BEAM_LENGTH;
		double handY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		
		addLine(handX,handY,handX - UPPER_ARM_LENGTH,handY);
		addLine(handX - UPPER_ARM_LENGTH,handY,
				handX - UPPER_ARM_LENGTH,handY + LOWER_ARM_LENGTH);
	}

	private void drawRightHand(double x1, double y1) {
		double handX = x1 + BEAM_LENGTH;
		double handY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		addLine(handX,handY,handX + UPPER_ARM_LENGTH,handY);
		addLine(handX + UPPER_ARM_LENGTH,handY,
				handX + UPPER_ARM_LENGTH,handY + LOWER_ARM_LENGTH);
	}

	private void drawLeftLeg(double x1, double y1) {
		double legX = x1 + BEAM_LENGTH;
		double legY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		addLine(legX,legY,legX - HIP_WIDTH,legY);
		addLine(legX - HIP_WIDTH,legY,legX - HIP_WIDTH,legY + LEG_LENGTH);
	}

	private void drawRightLeg(double x1, double y1) {
		double legX = x1 + BEAM_LENGTH;
		double legY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		addLine(legX,legY,legX + HIP_WIDTH,legY);
		addLine(legX + HIP_WIDTH,legY,legX + HIP_WIDTH,legY + LEG_LENGTH);
	}

	private void drawLeftFoot(double x1, double y1) {
		double FootX = x1 + BEAM_LENGTH - HIP_WIDTH;
		double FootY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		addLine(FootX,FootY,FootX - FOOT_LENGTH,FootY);
	}

	private void drawRightFoot(double x1, double y1) {
		double FootX = x1 + BEAM_LENGTH + HIP_WIDTH;
		double FootY = y1+ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		addLine(FootX,FootY,FootX + FOOT_LENGTH,FootY);
	}

	// Displays the incorrect letters
	private void addLetterToList(char letter) {
		label.setLabel(label.getLabel() + letter);
		label.setFont("Arial-30");
		double cordX = (getWidth() - label.getWidth()) / 2.0;
		double cordY = getHeight() - label.getDescent();
		add(label,cordX,cordY);
	}

/* instance variables */
	
	/** label that displays the guessed letters  */
	private GLabel wordDisplay = new GLabel("");
	
	/** label that displays the incorrect letters */
	private GLabel label = new GLabel("");
		
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_MARGIN_BOTTOM = 50;
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
}
