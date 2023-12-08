import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

public class RandomCircles extends GraphicsProgram {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;

    private RandomGenerator rgen;

    public void init() {
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        rgen = RandomGenerator.getInstance();
    }

    public void run() {
        int maxCircles = 1000;
        for (int i = 0; i < maxCircles; i++) {
            randomCircle();
            pause(0.3);
        }
    }

    private void randomCircle() {
        int circleX = rgen.nextInt(5, CANVAS_WIDTH - 5);
        int circleY = rgen.nextInt(5, CANVAS_HEIGHT - 5);
        int maxRadius = CANVAS_WIDTH - 5 - circleX > CANVAS_HEIGHT - 5 - circleY ? (CANVAS_HEIGHT - 5 - circleY) / 2 : (CANVAS_WIDTH - 5 - circleX);
        int circleRadius = rgen.nextInt(1, maxRadius);
        GOval circle = new GOval(circleX, circleY, circleRadius, circleRadius);
        circle.setFilled(true);
        Color circleColor = new Color(rgen.nextInt(256), rgen.nextInt(256), rgen.nextInt(256), rgen.nextInt(100, 200));
        circle.setColor(circleColor);
        add(circle);

    }
}