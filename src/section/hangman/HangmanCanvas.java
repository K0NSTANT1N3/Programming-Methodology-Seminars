package section.hangman;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	GLabel currentword = new GLabel(""); // Current word status
	GLabel incorrectLetters = new GLabel(""); // Displays letters entered
												// incorrectly by the player.
	String mistakes = "";

	// Restart the game.
	public void reset() {
		removeAll();
		mistakes = "";
		revalidate();
		draw(); // Draws the initial situation of the game.
	}

	// Coordinates of scaffold, beam and rope.
	private void draw() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50;
		double x2 = x1;
		double y2 = y1 + ROPE_LENGTH;

		GLine rope = new GLine(x1, y1, x2, y2);
		add(rope);

		double x3 = x1;
		double y3 = y1;
		double x4 = x1 - BEAM_LENGTH;
		double y4 = y3;

		GLine beam = new GLine(x3, y3, x4, y4);
		add(beam);

		double x5 = x4;
		double y5 = y4;
		double x6 = x5;
		double y6 = y5 + SCAFFOLD_HEIGHT;

		GLine scaffold = new GLine(x5, y5, x6, y6);
		add(scaffold);
	}

	// A word composed of the letters guessed by the player is written on the
	// canvas.
	public void displayWord(String word) {
		currentword.setLabel(word);
		currentword.setFont(new java.awt.Font("Arial", 4, 30));
		add(currentword, 50, getHeight() - 50);
	}

	// Writes the letters entered incorrectly by the player on the canvas.
	public void noteIncorrectGuess(String letter) {

		mistakes += letter;
		incorrectLetters.setLabel(mistakes);
		incorrectLetters.setFont(new java.awt.Font("Arial", 4, 20));
		add(incorrectLetters, 50, getHeight() - 20);
	}

	public void drawHead() {
		GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head, getWidth() / 2 - HEAD_RADIUS, getHeight() / 50 + ROPE_LENGTH);
	}

	public void drawBody() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS;
		double x2 = getWidth() / 2;
		double y2 = y1 + BODY_LENGTH;
		GLine body = new GLine(x1, y1, x2, y2);
		add(body);
	}

	public void drawLeftHand() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x2 = x1 - UPPER_ARM_LENGTH;
		double y2 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x3 = x2;
		double y3 = y2 + LOWER_ARM_LENGTH;

		GLine leftHand = new GLine(x1, y1, x2, y2);
		add(leftHand);

		GLine leftWrist = new GLine(x2, y2, x3, y3);
		add(leftWrist);
	}

	public void drawRightHand() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x2 = x1 + UPPER_ARM_LENGTH;
		double y2 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x3 = x2;
		double y3 = y2 + LOWER_ARM_LENGTH;

		GLine rightHand = new GLine(x1, y1, x2, y2);
		add(rightHand);

		GLine rightWrist = new GLine(x2, y2, x3, y3);
		add(rightWrist);
	}

	public void drawLeftLeg() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = x1 - HIP_WIDTH;
		double y2 = y1;
		double x3 = x2;
		double y3 = y2 + LEG_LENGTH;

		GLine leftHip = new GLine(x1, y1, x2, y2);
		add(leftHip);

		GLine leftLeg = new GLine(x2, y2, x3, y3);
		add(leftLeg);

	}

	public void drawRightLeg() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = x1 + HIP_WIDTH;
		double y2 = y1;
		double x3 = x2;
		double y3 = y2 + LEG_LENGTH;

		GLine rightHip = new GLine(x1, y1, x2, y2);
		add(rightHip);

		GLine rightLeg = new GLine(x2, y2, x3, y3);
		add(rightLeg);

	}

	public void drawLeftFoot() {
		double x1 = getWidth() / 2 - HIP_WIDTH;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = x1 - FOOT_LENGTH;
		double y2 = y1;

		GLine leftFoot = new GLine(x1, y1, x2, y2);
		add(leftFoot);
	}

	public void drawRightFoot() {
		double x1 = getWidth() / 2 + HIP_WIDTH;
		double y1 = getHeight() / 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = x1 + FOOT_LENGTH;
		double y2 = y1;

		GLine leftFoot = new GLine(x1, y1, x2, y2);
		add(leftFoot);
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

}
