package assignments.hangman.canvas;/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.util.ErrorException;

import java.awt.*;
import java.util.Objects;

import static acm.util.JTFTools.pause;

public class ShotaShootingGugaCanvas extends GCanvas {

    private GLabel text;
    private GLabel usedLetters;

    /**
     * Resets the display so that only the scaffold appears
     */
    public void reset() {
        removeAll();
        text = null;
        usedLetters = null;
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
    private GImage bullet = null;
    private void hungProcess(int lifeLeft) {

        switch (lifeLeft) {
            case 7 -> drawGuGaHead();
            case 6 -> drawGuGaBody();
            case 5 -> drawHat();
            case 4 -> drawHead();
            case 3 -> drawBody();
            case 2 -> drawGun();
            case 1 -> drawBullet();
            case 0 -> {
                shootBullet(Objects.requireNonNull(bullet));
                drawBlood();
            }
            default -> throw new IllegalStateException("Unexpected value: " + lifeLeft);
        }
    }

    /* method for rendering specific image on specific location */
    private GImage loadImage(String imagePath, double height, double x, double y) {
        GImage image;
        try {
            image = new GImage(imagePath);
        } catch (ErrorException e) {
            System.out.println("Wrong path to '" + imagePath + "'");
            e.printStackTrace();
            return null;
        }

        double width = height * image.getWidth() / image.getHeight();
        image.setSize(width, height);
        add(image, x, y);

        return image;
    }


    private void drawGuGaHead() {
        loadImage("hangman/assets/gugaHead.png", 110, 79, 80);
    }

    private void drawGuGaBody() {
        loadImage("hangman/assets/gugaBody.png", 193.01876, 30, 180);
    }

    private void drawHat() {
        loadImage("hangman/assets/hat.png", 75, 452, 75);
    }

    private void drawHead() {
        loadImage("hangman/assets/head.png", 85.253905714, 470, 97);
    }

    private void drawBody() {
        loadImage("hangman/assets/body.png", 132.12890542, 450, 175);
    }

    private void drawGun() {
        loadImage("hangman/assets/gun.png", 75, 440, 170);
    }

    private void drawBullet() {
        bullet = loadImage("hangman/assets/bullet.png", 70, 350, 151);
    }

    private void shootBullet(GImage bullet) {
        while (bullet.getX() >= 120) {
            bullet.move(-1, 0);
            pause(3);
        }
        remove(bullet);
    }

    private void drawBlood() {
        loadImage("hangman/assets/blood.png", 100, 90, 170);
    }

}
