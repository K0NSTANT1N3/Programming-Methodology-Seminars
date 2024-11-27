package section;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Random extends GraphicsProgram {
    private static final int NUM_BALLS = 30;
    private static final int MIN_RADIUS = 5;
    private static final int MAX_RADIUS = 10;
    private static final int VY = 2;
    private GOval lastBall = null;
    private boolean isFalling = false;

    private acm.util.RandomGenerator rand = RandomGenerator.getInstance();

    @Override
    public void init() {
        addMouseListeners();

        DrawBalls();
    }

    @Override
    public void run() {


        while (true) {
            if(lastBall != null && lastBall.getHeight() + lastBall.getY() >= getHeight()){
                isFalling = false;
            }
            if(isFalling && lastBall != null && lastBall.getY() + lastBall.getHeight() <= getHeight()){
                lastBall.move(0, VY);
                pause(5);
            }

            pause(1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (getElementAt(x, y) == null) {
            isFalling = true;
        } else {
            GOval curBall = (GOval) getElementAt(x, y);
            isFalling = false;
            if (curBall != lastBall) {
                curBall.setColor(rand.nextColor());
                lastBall = curBall;
            }
        }
    }

    private void DrawBalls() {
        for (int i = 0; i < NUM_BALLS; i++) {
            int radius = rand.nextInt(MIN_RADIUS, MAX_RADIUS);
            int x = rand.nextInt(0, getWidth() - 2 * radius);
            int y = rand.nextInt(0, getHeight() - 2 * radius);
            GOval ball = new GOval(2 * radius, 2 * radius);
            ball.setFilled(true);
            ball.setColor(Color.black);
            add(ball, x, y);
        }
    }
}
