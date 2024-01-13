package assignments.hangman.canvas;/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.awt.*;

public class HangmanCanvas extends GCanvas {

    private GLabel text;
    private GLabel usedLetters;

    /**
     * Resets the display so that only the scaffold appears
     */
    public void reset() {
        removeAll();
        text = null;
        usedLetters = null;

        drawHung();
    }

    /**
     * Updates the word on the screen to correspond to the current
     * state of the game.  The argument string shows what letters have
     * been guessed so far; unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        if (text != null) remove(text);
        text = new GLabel(word);
        Font font = new Font("SansSerif", Font.BOLD, 24);
        text.setFont(font);
        add(text, 20, getHeight() - 80);
    }


    /**
     * Updates the display to correspond to an incorrect guess by the
     * user.  Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess(char letter, int lifeLeft) {
        hungProcess(lifeLeft);

        if (usedLetters == null) {
            usedLetters = new GLabel("");
            Font font = new Font("SansSerif", Font.ITALIC, 12);
            usedLetters.setFont(font);
            add(usedLetters, 20, getHeight() - 40);
        }
        if (!usedLetters.getLabel().contains(letter + "")) {
            usedLetters.setLabel(usedLetters.getLabel() + letter);
            repaint();
        }
    }

    /**
     * Updates the display based on the remaining life.
     *
     * @param lifeLeft Remaining life count.
     */
    private void hungProcess(int lifeLeft) {
        switch (lifeLeft) {
            case 7 -> drawHead();
            case 6 -> drawBody();
            case 5, 4 -> drawArm((lifeLeft == 5) ? -1 : 1);
            case 3, 2 -> drawLeg((lifeLeft == 3) ? -1 : 1);
            case 1, 0 -> drawFoot((lifeLeft == 1) ? -1 : 1);
        }
    }


    /**
     * drawing every part individually
     */
    private void drawHead() {
        double headX = getWidth() / 2.0 - HEAD_RADIUS;
        double headY = getHeight() / 2.0 - BODY_LENGTH - 2 * HEAD_RADIUS;

        GOval head = new GOval(2 * HEAD_RADIUS - 1, 2 * HEAD_RADIUS);
        add(head, headX, headY);
    }

    private void drawBody() {
        double bodyX = getWidth() / 2.0;
        double bodyY1 = getHeight() / 2.0;
        double bodyY2 = bodyY1 - BODY_LENGTH;

        GLine body = new GLine(bodyX, bodyY1, bodyX, bodyY2);
        add(body);
    }

    /**
     * Draws the arms based on the direction.
     *
     * @param right Direction of the arms (-1 for left, 1 for right).
     */
    private void drawArm(int right) {
        double armX1 = getWidth() / 2.0;
        double armX2 = armX1 + right * UPPER_ARM_LENGTH;

        double armY1 = getHeight() / 2.0 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
        double armY2 = armY1 + LOWER_ARM_LENGTH;

        // Draw arm and hand
        add(new GLine(armX1, armY1, armX2, armY1));
        add(new GLine(armX2, armY1, armX2, armY2));
    }

    /**
     * Draws the leg based on the direction.
     *
     * @param right Direction of the leg (-1 for left, 1 for right).
     */
    private void drawLeg(int right) {
        double hipX = getWidth() / 2.0;
        double hipY = getHeight() / 2.0;

        double hipEndX = hipX + right * HIP_WIDTH;
        double hipEndY = hipY + LEG_LENGTH;

        // Draw hip and leg
        add(new GLine(hipX, hipY, hipEndX, hipY));
        add(new GLine(hipEndX, hipY, hipEndX, hipEndY));
    }

    /**
     * Draws the foot based on the direction.
     *
     * @param right Direction of the foot (-1 for left, 1 for right).
     */
    private void drawFoot(int right) {
        double footX = getWidth() / 2.0 + right * HIP_WIDTH;
        double footY = getHeight() / 2.0 + LEG_LENGTH;

        double footEndX = footX + right * FOOT_LENGTH;
        double footEndY = footY;

        // Draw foot
        add(new GLine(footX, footY, footEndX, footEndY));
    }


    /* draws essential initial lines */
    private void drawHung() {
        double beamX = getWidth() / 2.0;
        double scafoldX = beamX - BEAM_LENGTH;

        double scafoldY1 = getHeight() / 2.0 - BODY_LENGTH - 2 * HEAD_RADIUS - ROPE_LENGTH;
        double scafoldY2 = scafoldY1 + SCAFFOLD_HEIGHT;
        double ropeY = scafoldY1 + ROPE_LENGTH;

        GLine scafold = new GLine(scafoldX, scafoldY1, scafoldX, scafoldY2);
        GLine beam = new GLine(scafoldX, scafoldY1, beamX, scafoldY1);
        GLine rope = new GLine(beamX, scafoldY1, beamX, ropeY);

        add(scafold);
        add(beam);
        add(rope);
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
