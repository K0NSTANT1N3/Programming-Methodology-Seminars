package seminarProblems;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class Problem41 extends GraphicsProgram {
    public void run() {

        addMouseListeners();
    }

    public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        GOval oval = new GOval(x, y, 10, 10);
        add(oval);
    }
}
