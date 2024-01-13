package assignments.breakout.classes.game_entities;
/*
 * 1->paddle gets bigger
 * 2->paddle gets smaller
 * 3->ball gets bigger
 * 4->ball gets smaller
 * 5->new ball
 * 6->fast ball
 * 7->slow ball
 */

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import assignments.breakout.variables.Var;

import java.awt.*;

public class SuperBrick extends GRect {
    private static final RandomGenerator rgen = RandomGenerator.getInstance();
    private int delay;
    private int superPower;
    private boolean falling;

    public SuperBrick(double v, double v1) {
        super(v, v1);
    }

    public SuperBrick(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    /* gives super random SuperBrick with every component being random*/
    public static SuperBrick randBrick() {
        int x, y, width, height, superPower, delay;

        x = rgen.nextInt(Var.BRICK_WIDTH / 2, Var.WIDTH - Var.BRICK_WIDTH);
        y = rgen.nextInt(Var.BRICK_Y_OFFSET, Var.BRICK_Y_OFFSET + (Var.BRICK_SEP + Var.BRICK_HEIGHT) * (Var.NBRICK_ROWS - 1));

        width = Var.BRICK_WIDTH / 2;
        height = Var.BRICK_HEIGHT / 2;

        superPower = rgen.nextInt(1, 8);
        delay = rgen.nextInt(5, 50);

        Color color = new Color(rgen.nextInt(256), rgen.nextInt(256), rgen.nextInt(256));

        SuperBrick brick = new SuperBrick(x, y, width, height);
        brick.setFilled(true);
        brick.setColor(color);
        brick.setDelay(delay);
        brick.setFalling(false);
        brick.setSuperPower(superPower);
        return brick;
    }

    public boolean getFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getSuperPower() {
        return superPower;
    }

    public void setSuperPower(int superPower) {
        this.superPower = superPower;
    }

}
