package assignments.assignment2;/*
 * File: ProgramHierarchy.java
 * Name: Konstantine Endeladze
 * Section Leader:
 * ---------------------------
 * This file solves ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {

    private static final int SQUARE_WIDTH = 150;
    private static final int SQUARE_HEIGHT = 60;
    private static final int GAP = 40;
    private static final String TITLE_0 = "Program";
    private static final String TITLE_1 = "GraphicsProgram";
    private static final String TITLE_2 = "ConsoleProgram";
    private static final String TITLE_3 = "DialogProgram";
    private static final int TEXT_FONT = 14;

    public void run() {
        drawBoxes();
        drawLines();
    }

    //responsible for connecting all boxes
    private void drawLines(){
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        double topY = centerY - GAP / 2;
        double bottomY = centerY + GAP / 2;

        double leftX = centerX - GAP - SQUARE_WIDTH;
        double rightX = centerX + GAP + SQUARE_WIDTH;

        GLine line1 = new GLine(centerX, topY, leftX, bottomY);
        GLine line2 = new GLine(centerX, topY, centerX, bottomY);
        GLine line3 = new GLine(centerX, topY, rightX, bottomY);

        add(line1);
        add(line2);
        add(line3);
    }

    //responsible for drawing all box in hierarchy
    private void drawBoxes(){
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        double middleX = centerX - SQUARE_WIDTH / 2;
        double leftX = middleX - GAP - SQUARE_WIDTH;
        double rightX = middleX + SQUARE_WIDTH + GAP;

        double topY = centerY - SQUARE_HEIGHT - GAP / 2;
        double bottomY = centerY + GAP / 2;

        drawBox(middleX, topY, TITLE_0);
        drawBox(leftX, bottomY, TITLE_1);
        drawBox(middleX, bottomY, TITLE_2);
        drawBox(rightX, bottomY, TITLE_3);
    }

    //draws one box
    private void drawBox(double x, double y, String title) {
        drawRectangle(x, y);
        drawText(x, y, title);
    }

    //draws square
    private void drawRectangle(double x, double y) {
        GRect rect = new GRect(SQUARE_WIDTH, SQUARE_HEIGHT);
        add(rect, x, y);
    }

    //draws text
    private void drawText(double x, double y, String title) {
        GLabel label = new GLabel(title);
        label.setFont(new Font("SansSerif", Font.PLAIN, TEXT_FONT));
        double labelX = x + (SQUARE_WIDTH - label.getWidth()) / 2;
        double labelY = y + (SQUARE_HEIGHT + label.getAscent()) / 2;
        add(label, labelX, labelY);
    }
}

