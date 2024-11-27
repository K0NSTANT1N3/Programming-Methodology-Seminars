/*
 * File: ProgramHierarchy.java
 * Name:
 * Section Leader:
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class ProgramHierarchy extends GraphicsProgram {



    /** Width of each brick in pixels */
    private static final int WINDOW_WIDTH = 174;
    /** Height of each brick in pixels */
    private static final int WINDOW_HEIGHT = 88;




    public void run() {
        drawUpClass();
        drawLowerClass();
        connectLines();
        addProgram();
        addGraphicsProgram();
        addConsoleProgram();
        addDialogProgram();
    }



    // adds text in the last box of Lowerclass.
    private void addDialogProgram() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;  //x coordinate of UpClass box.
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;   //y coordinate of UpClass box.
        int dif = WINDOW_HEIGHT / 2 + WINDOW_WIDTH;   // difference between LowerClass boxes.
        GLabel dialogProgram = new GLabel("DialogProgram");
        double midY = (WINDOW_HEIGHT - dialogProgram.getAscent())/2;
        double midX = (WINDOW_WIDTH - dialogProgram.getWidth())/2;
        add(dialogProgram,startX + dif + midX,startY + 2*WINDOW_HEIGHT + 1.5*midY);

    }



    //adds text in the second box of Lowerclass.
    private void addConsoleProgram() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        GLabel consoleProgram = new GLabel("ConsoleProgram");
        double midY = (WINDOW_HEIGHT - consoleProgram.getAscent())/2;
        double midX = (WINDOW_WIDTH - consoleProgram.getWidth())/2;
        add(consoleProgram,startX + midX,startY + 2*WINDOW_HEIGHT + 1.5*midY);

    }



    //adds text in the first box of Lowerclass.
    private void addGraphicsProgram() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        int dif = WINDOW_HEIGHT / 2 + WINDOW_WIDTH;
        GLabel graphicsProgram = new GLabel("GraphicsProgram");
        double midY = (WINDOW_HEIGHT - graphicsProgram.getAscent())/2;
        double midX = (WINDOW_WIDTH - graphicsProgram.getWidth())/2;
        add(graphicsProgram,startX - dif + midX,startY + 2*WINDOW_HEIGHT + 1.5*midY);

    }



    //adds text in the Upclass box.
    private void addProgram() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        GLabel program = new GLabel("Program");
        double midY = (WINDOW_HEIGHT - program.getAscent())/2;
        double midX = (WINDOW_WIDTH - program.getWidth())/2;
        add(program,startX + midX, startY + 1.5*midY);

    }



    // connects boxes with lines.
    private void connectLines() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        int dif = WINDOW_HEIGHT / 2 + WINDOW_WIDTH;
        for(int i = 0; i < 3; i++){
            GLine connect = new GLine(startX + WINDOW_WIDTH/2,startY + WINDOW_HEIGHT, startX + (i-1)*dif + WINDOW_WIDTH/2 ,startY + 2*WINDOW_HEIGHT);
            add(connect);
        }
    }



    // draws 3 boxes in LowerClass.
    private void drawLowerClass() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        int dif = WINDOW_HEIGHT / 2 + WINDOW_WIDTH;
        for(int i = 0; i < 3; i++){
            GRect rect = new GRect(WINDOW_WIDTH,WINDOW_HEIGHT);
            int x = startX + (i-1)*dif;
            add(rect, x ,startY + 2*WINDOW_HEIGHT);
        }

    }



    //draws Up class box.
    private void drawUpClass() {
        int startX = (getWidth()- WINDOW_WIDTH)/2;
        int startY = (getHeight()- 3*WINDOW_HEIGHT)/2;
        GRect head= new GRect (WINDOW_WIDTH,WINDOW_HEIGHT);
        add(head,startX,startY);
    }


}
