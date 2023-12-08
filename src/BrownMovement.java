import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;
import acm.util.RandomGenerator;

public class BrownMovement extends GraphicsProgram {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final int STEP_LENGTH = 10;
    private static final int PAUSE_TIME_MS = 10;
    private static final int NUM_DIRECTIONS = 8;
    private static final int NUM_BROWNS = 31;
    private RandomGenerator rgen;

    public void init() {
        rgen = RandomGenerator.getInstance();
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        pause(PAUSE_TIME_MS * 40);
    }

    public void run() {
        for (int i = 0; i < NUM_BROWNS; i++) new Thread(this::brownLine).start();
    }

    private void brownLine () {
        int lineX = CANVAS_WIDTH / 2;
        int lineY = CANVAS_HEIGHT / 2;

        Color lineColor = new Color(rgen.nextInt(255), rgen.nextInt(100), rgen.nextInt(100));
        Color tracerColor = new Color(rgen.nextInt(100), rgen.nextInt(255), rgen.nextInt(255));

        GOval tracer = tracerBuilder(tracerColor);
        add(tracer);

        while(true){
            int dx = 0, dy = 0;
            int direction = rgen.nextInt(1,NUM_DIRECTIONS);

            switch (direction) {
                case 1 -> dx = -STEP_LENGTH;
                case 2 -> dx = STEP_LENGTH;
                case 3 -> dy = -STEP_LENGTH;
                case 4 -> dy = STEP_LENGTH;
                case 5 -> {
                    dx = -STEP_LENGTH;
                    dy = -STEP_LENGTH;
                }
                case 6 -> {
                    dx = -STEP_LENGTH;
                    dy = STEP_LENGTH;
                }
                case 7 -> {
                    dx = STEP_LENGTH;
                    dy = -STEP_LENGTH;
                }
                case 8 -> {
                    dx = STEP_LENGTH;
                    dy = STEP_LENGTH;
                }
            }

            if(lineX < STEP_LENGTH) dx = STEP_LENGTH;
            else if (lineX > CANVAS_WIDTH - STEP_LENGTH) dx = -STEP_LENGTH;

            if(lineY < STEP_LENGTH) dy = STEP_LENGTH;
            else if (lineY > CANVAS_HEIGHT - STEP_LENGTH) dy = -STEP_LENGTH;

            tracer.setLocation(tracer.getX() + dx, tracer.getY()  + dy);
            add(randomStep(lineX, lineY, lineX + dx, lineY  + dy, lineColor));
            lineX += dx;
            lineY += dy;
            pause(PAUSE_TIME_MS);
        }
    }
    private GOval tracerBuilder (Color tracerColor){
        GOval tracer = new GOval(CANVAS_WIDTH / 2 - STEP_LENGTH, CANVAS_HEIGHT / 2 - STEP_LENGTH, 2 * STEP_LENGTH, 2 * STEP_LENGTH);
        tracer.setFilled(true);
        tracer.setColor(tracerColor);
        return tracer;
    }

    private GLine randomStep (int currentX, int currentY, int destinationX, int destinationY, Color lineColor){
        GLine line = new GLine(currentX, currentY, destinationX, destinationY);
        line.setColor(lineColor);
        return line;
    }

}