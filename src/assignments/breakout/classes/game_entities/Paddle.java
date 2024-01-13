package assignments.breakout.classes.game_entities;

import acm.graphics.GRect;

public class Paddle extends GRect {

    public Paddle(double v, double v1) {
        super(v, v1);
    }

    public Paddle(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    /* useful coordinates */
    public double leftX() {
        return this.getX();
    }

    public double rightX() {
        return this.getX() + getWidth();
    }

    public double midX() {
        return this.getX() + getWidth() / 2;
    }

}
