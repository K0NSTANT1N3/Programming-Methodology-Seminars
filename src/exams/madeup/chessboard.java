package exams.madeup;

import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

import static java.awt.Color.*;

public class chessboard extends GraphicsProgram {

    private final static int CANVASWIDTH = 500;
    private final static int CANVASHEIGHT = 500;
    private GRect r1;
    private GRect r2;

    public void init() {
        setSize(CANVASWIDTH, CANVASHEIGHT);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                double curX = j * getWidth()/8.0;
                double curY = i * getHeight()/8.0;
                GRect rect = new GRect(getWidth()/8.0, getHeight()/8.0);
                add(rect, curX, curY);
            }
        }
    }
    public void run(){
        addMouseListeners();
    }

    public void mouseClicked(MouseEvent e){
        int curX = e.getX();
        int curY = e.getY();
        GObject obj = getElementAt(curX, curY);
        if(obj instanceof GRect){
            GRect rect = (GRect) obj;
            if(rect.isFilled()){
                rect.setFilled(true);
                rect.setColor(black);
            }
            else if(rect.getColor() == WHITE){
                rect.setFilled(false);
                if(r1 != null && r1.isFilled()){
                    r1.setFilled(false);
                    r1.setColor(BLACK);
                    r1 = r2;
                    r2 = rect;
                }
            }
        }
    }
}

