package assignments.breakout.classes.game_entities;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class Button extends GCompound {

    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 40;
    private static final Color BUTTON_COLOR = Color.LIGHT_GRAY;
    private static final Color TEXT_COLOR = Color.BLACK;

    private final GRect buttonRect;
    private final GLabel buttonText;

    public Button(String text, double x, double y) {
        buttonRect = new GRect(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonRect.setFilled(true);
        buttonRect.setFillColor(BUTTON_COLOR);

        buttonText = new GLabel(text);
        buttonText.setColor(TEXT_COLOR);
        centerLabel();

        setLocation(x, y);

        // Add the rectangular background and text label to the compound object
        add(buttonRect);
        add(buttonText);
    }

    private void centerLabel() {
        double x = (BUTTON_WIDTH - buttonText.getWidth()) / 2;
        double y = (BUTTON_HEIGHT + buttonText.getAscent()) / 2;
        buttonText.setLocation(x, y);
    }
}
