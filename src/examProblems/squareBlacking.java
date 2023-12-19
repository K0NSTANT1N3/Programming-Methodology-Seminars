package examProblems;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class squareBlacking extends GraphicsProgram {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    private GRect square1;
    private GRect square2;

    @Override
    public void init() {
        setSize(WIDTH + 20, HEIGHT + 20);
        drawGreed(8, 8);
    }

    @Override
    public void run() {
        addMouseListeners();
    }

    private void drawGreed(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GRect square = new GRect(WIDTH / x, HEIGHT / y);
                add(square, i * WIDTH / x, j * HEIGHT / y);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (getElementAt(e.getX(), e.getY()) != null) {
            GRect currentSquare = (GRect) getElementAt(e.getX(), e.getY());
            if (currentSquare == square1) {
                square1.setFilled(false);
                square1 = square2;
                square2 = null;
            } else if (currentSquare == square2) {
                square2.setFilled(false);
                square2 = null;
            } else {
                currentSquare.setFilled(true);
                if (square1 != null) {
                    if (square2 != null) {
                        square1.setFilled(false);
                        square1 = square2;
                    }
                    square2 = currentSquare;
                } else {
                    square1 = currentSquare;
                }
            }
        }
    }
}